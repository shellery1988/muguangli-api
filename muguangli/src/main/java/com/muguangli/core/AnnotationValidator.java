package com.muguangli.core;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.lang.ArrayUtils;

/**
 * 注解校验器
 * 
 * @author wy
 *
 */
public class AnnotationValidator {
    private static final Logger log = Logger.getLogger(AnnotationValidator.class.getName());

    // 默认加载的注解
    private final static List<IAnnotationParser> vList = new ArrayList<IAnnotationParser>();
    static {
        vList.add(new NotNullParser());
        vList.add(new NotBlankParser());
        vList.add(new RegularParser());
    }

    /**
     * 遍历所有字段，用所有解析器进行校验，如果校验失败，则终止校验返回结果，如果校验成功，同样返回校验结果
     * 
     * @param t
     * @return
     */
    public static <T> ValidateResult validate(T t, String type) {
        ValidateResult result = null;

        Field[] fields = t.getClass().getDeclaredFields();
        // 解决vo继承自实体问题
        Field[] fafields = t.getClass().getSuperclass().getDeclaredFields();

        Field[] both = (Field[]) ArrayUtils.addAll(fields, fafields);

        for (Field f : both) {
            f.setAccessible(true);
            Object value = null;
            try {
                value = f.get(t);
            } catch (IllegalArgumentException e) {
                log.log(Level.SEVERE, "Exception", e);
            } catch (IllegalAccessException e) {
                log.log(Level.SEVERE, "Exception", e);
            }

            for (IAnnotationParser va : vList) {
                result = va.validate(f, value, type);
                if (!result.isValid()) {
                    return result;
                }
            }
        }
        return result;
    }

    public static <T> ValidateResult validate(T t) {
        return AnnotationValidator.validate(t, "");
    }

    /**
     * 注册解析器
     * 
     * @param parser
     */
    public static void register(IAnnotationParser parser) {
        vList.add(parser);
    }
}
