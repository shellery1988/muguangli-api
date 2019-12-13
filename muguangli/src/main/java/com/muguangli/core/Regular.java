package com.muguangli.core;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description:正则
 * @Author:wuyou-bizcent
 * @Since:2016年10月21日
 * @Version:1.1.0
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Regular {
    public String fieldName();

    public String reg();

    public String[] type() default { "" };
}
