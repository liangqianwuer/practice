package com.xunc.common.annotations.section;

import com.xunc.common.annotations.Log;
import com.xunc.common.utils.CommUtil;
import com.xunc.system.entity.SysLog;
import com.xunc.system.service.SysLogService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @author zfc
 * @date 2022-10-24-15:24
 */

@Aspect
@Component
public class SysLogAspect {

    /**
     * 定义一个LogAspect类，使用@Aspect标注让其成为一个切面，切点为使用@Log注解标注的方法，使用@Around环绕通知：
     */

    @Autowired
    private SysLogService sysLogService;

    @Pointcut("@annotation(com.xunc.common.annotations.Log)")
    public void pointcut() {}

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point) {
        Object result = null;
        long beginTime = System.currentTimeMillis();
        try {
            result = point.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        long time = System.currentTimeMillis() - beginTime;
        saveLog(point, time);
        return result;
    }

    private void saveLog(ProceedingJoinPoint joinPoint, long time) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        SysLog sysLog = new SysLog();
        Log logAnnotation = method.getAnnotation(Log.class);
        if (logAnnotation != null) {
            // 注解上的描述
            sysLog.setOperation(logAnnotation.value());
        }
        // 请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        sysLog.setMethod(className + "." + methodName + "()");
        // 请求的方法参数值
        Object[] args = joinPoint.getArgs();
        // 请求的方法参数名称
        LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
        String[] paramNames = u.getParameterNames(method);
        if (args != null && paramNames != null) {
            String params = "";
            for (int i = 0; i < args.length; i++) {
                params += "  " + paramNames[i] + ": " + args[i];
            }
            sysLog.setParams(params);
        }
        // 获取request
        HttpServletRequest request = getHttpServletRequest();
        // 设置IP地址
        sysLog.setIp(CommUtil.getIpAddr(request));
        // 模拟一个用户名
        sysLog.setUserName("admin");
        sysLog.setTime((int) time);
        sysLog.setCreateTime(new Date());
        // 保存系统日志
        sysLogService.save(sysLog);
    }

    /**
     * 获取当前的request
     *
     */
    public HttpServletRequest getHttpServletRequest() {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        assert sra != null;
        return sra.getRequest();

    }
}
