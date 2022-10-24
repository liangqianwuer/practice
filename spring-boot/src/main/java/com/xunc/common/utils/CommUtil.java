package com.xunc.common.utils;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicLong;

/** 
 * @author 颜泽贤 
 * Date:2017-10-11 下午
 */
public class CommUtil {
	private static AtomicLong timeMills = new AtomicLong(0);

	public static String createRandom(boolean numberFlag, int length) {
		String retStr = "";
		String strTable = numberFlag ? "1234567890": "1234567890abcdefghijkmnpqrstuvwxyz";
		int len = strTable.length();
		boolean bDone = true;
		do {
			retStr = "";
			int count = 0;
			for (int i = 0; i < length; i++) {
				double dblR = Math.random() * len;
				int intR = (int) Math.floor(dblR);
				char c = strTable.charAt(intR);
				if (('0' <= c) && (c <= '9')) {
					count++;
				}
				retStr += strTable.charAt(intR);
			}
			if (count >= 2) {
				bDone = false;
			}
		} while (bDone);

		return retStr;
	}
	
	/**
	 * 非空验证
	 * @str 参数列表
	 * @return
	 */
	public static boolean checkNull(Object...str){
		if (null == str) {
			return false;
		}
		for (Object o : str) {
			if (null == o || "".equals(o)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 获取唯一时间戳值
	 *
	 * @return
	 */
	public static Long getTimeId() {
		while (true) {
			long currentTimeMillis = System.nanoTime();
			long currentMill = timeMills.get();
			if (currentTimeMillis > currentMill && timeMills.compareAndSet(currentMill, currentTimeMillis)) {
				//返回唯一时间戳
				return currentTimeMillis;
			}
		}
	}

	/**
	 * 获取客户端真实IP
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = "";
		if (request != null) {
			ip = request.getHeader("x-forwarded-for");
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("Proxy-Client-IP");
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("WL-Proxy-Client-IP");
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getRemoteAddr();
			}
		}
		return ip;
	}



	/**
	 * 执行cmd命令
	 * @param cmd cmd命令字符串
	 * @return 成功或失败
	 * @throws InterruptedException
	 * @throws ExecutionException
	 * @throws IOException
	 */
	public static boolean runCmd(String cmd) throws InterruptedException, ExecutionException, IOException {
		// 执行cmd命令
		Process process = Runtime.getRuntime().exec(cmd);
		// 读取输入流与错误信息
		InputStream stream = process.getInputStream();
		InputStream error = process.getErrorStream();

		CompletableFuture<String> t1 = CompletableFuture.supplyAsync(() -> {
			BufferedReader br1 = new BufferedReader(new InputStreamReader(stream));
			String line1;
			StringBuilder sb = new StringBuilder();
			try {
				while ((line1 = br1.readLine()) != null) {
					sb.append(line1).append("\n");
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					stream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return sb.toString();
		});

		CompletableFuture<String> t2 = CompletableFuture.supplyAsync(() -> {
			BufferedReader br2 = new BufferedReader(new InputStreamReader(error));
			StringBuilder sb = new StringBuilder();
			try {
				String line2;
				while ((line2 = br2.readLine()) != null) {
					sb.append(line2).append("\n");
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					error.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return sb.toString();
		});
		process.waitFor();
		int status = process.exitValue();
		if (status != 0) {
			System.err.println(t2.get());
			return false;
		}
		return true;
	}

}

