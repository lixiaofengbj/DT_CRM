package com.dt.common.annotation;

import com.dt.common.constant.LogTypeEnum;

import java.lang.annotation.*;


@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface LogOperator {

    /**
     * 日志类型
     *
     * @return
     */
    LogTypeEnum type();

    /**
     * 描述,支持占位符形式
     *
     * @return
     */
    String description() default "";

}
