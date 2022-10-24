package com.xunc.common.generator.util;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.slf4j.Logger;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ExceptionLog {

    public static void error(Logger log, String msg, Exception e) {

        String message = e.getMessage();

        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));
        log.error(msg + "\n" + (StringUtils.isNotBlank(message) ? message : "") + sw);

    }
}
