package com.muguangli.core;

import java.lang.reflect.Field;

/**
 * 校验框架注解解析器接口
 * 
 * 实现该接口构建解析器，理论上每一个注解都会对应一个解析器， 解析器需要在校验器中注册使用
 * 
 * @author wy
 *
 */
public interface IAnnotationParser {

    /**
     * 校验一个字段f的值是否符合基于value的某一个规则，并且把校验结果保存在ValidateResult中返回
     * 
     * @param f
     * @param value
     * @return
     */
    // public ValidateResult validate(Field f, Object value);

    public ValidateResult validate(Field f, Object value, String type);

}
