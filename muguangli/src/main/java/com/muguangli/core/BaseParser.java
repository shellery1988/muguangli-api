package com.muguangli.core;

/**
 * @Description:提供校验基础方法
 * @Author:wuyou-bizcent
 * @Since:2016年10月21日
 * @Version:1.1.0
 */
public class BaseParser {
    public boolean checkType(String[] types, String type) {
        boolean isCheck = false;
        // 是否启用type,注解和type入参任意一个为"",则不启用type,照常校验
        if (!"".equals(type)) {
            for (String s : types) {
                if ("".equals(s) || type.equals(s)) {
                    isCheck = true;
                }
            }
        } else {
            isCheck = true;
        }
        return isCheck;
    }
}
