package com.muguangli.util;

import java.util.Properties;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

/**
 * 
 * @Description:读取配置文件工具类
 * @Version:1.1.0
 */
public final class PropertiesUtil {

    private static Properties businessProperties;

    private PropertiesUtil() {
    }

    static {
        try {
            Resource businessResource = new ClassPathResource("/properties/business.properties");
            businessProperties = PropertiesLoaderUtils.loadProperties(businessResource);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getBusinessProperties(String key) {
        return businessProperties.getProperty(key);
    }

}
