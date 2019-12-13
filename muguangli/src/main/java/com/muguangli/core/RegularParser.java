package com.muguangli.core;

import java.lang.reflect.Field;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description:正则校验
 * @Author:wuyou-bizcent
 * @Since:2016年10月21日
 * @Version:1.1.0
 */
public class RegularParser extends BaseParser implements IAnnotationParser {

    @Override
    public ValidateResult validate(Field f, Object value, String type) {
        ValidateResult result = new ValidateResult();
        if (f.isAnnotationPresent(Regular.class)) {
            Regular reg = f.getAnnotation(Regular.class);
            if (checkType(reg.type(), type)) {

                Pattern p = Pattern.compile(reg.reg());
                Matcher m = p.matcher(value.toString());

                if (!m.matches()) {
                    result.setMessage(reg.fieldName() + "输入值不合法");
                }
            }
        }
        return result;
    }

}
