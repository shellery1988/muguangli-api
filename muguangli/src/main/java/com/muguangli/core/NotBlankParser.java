package com.muguangli.core;

import java.lang.reflect.Field;

/**
 * 不能为空白校验器
 * 
 * @author wy
 *
 */
public class NotBlankParser extends BaseParser implements IAnnotationParser {

    /**
     * 校验字段f的值不能为null或者是空字符串，校验结果保存在result中
     */
    @Override
    public ValidateResult validate(Field f, Object value, String type) {
        ValidateResult result = new ValidateResult();
        if (f.isAnnotationPresent(NotBlank.class)) {
            NotBlank notBlank = f.getAnnotation(NotBlank.class);
            if (checkType(notBlank.type(), type)) {
                if (value == null || value.toString().length() == 0) {
                    result.setMessage(notBlank.fieldName() + "不能为空串");
                }
            }
        }
        return result;
    }

}
