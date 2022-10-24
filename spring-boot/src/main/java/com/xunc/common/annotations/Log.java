package com.xunc.common.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author zfc
 * @date 2022-10-24-15:22
 */

/**
 * 定义一个方法级别的@Log注解，用于标注需要监控的方法：
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Log {
    String value() default "";
}
