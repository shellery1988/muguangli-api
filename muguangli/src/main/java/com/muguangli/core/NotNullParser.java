package com.muguangli.core;

import java.lang.reflect.Field;

/**
 * 为空校验器
 * 
 * @author wy
 *
 */
public class NotNullParser extends BaseParser implements IAnnotationParser {
    /**
     * 校验字段f的值不能为null，校验结果保存在result中
     */
    @Override
    public ValidateResult validate(Field f, Object value, String type) {
        ValidateResult result = new ValidateResult();
        if (f.isAnnotationPresent(NotNull.class)) {
            NotNull notNull = f.getAnnotation(NotNull.class);
            if (checkType(notNull.type(), type)) {
                if (value == null) {
                    result.setMessage(notNull.fieldName() + "不能为空");
                }
            }
        }
        return result;
    }
}
