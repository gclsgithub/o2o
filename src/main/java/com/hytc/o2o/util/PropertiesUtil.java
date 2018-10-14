package com.hytc.o2o.util;

import org.springframework.util.ObjectUtils;

import java.util.Properties;

public class PropertiesUtil {

    private static Properties properties;

    private static void setProperties(){
        if (ObjectUtils.isEmpty(properties)){
            properties = new Properties();
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            try {
                properties.load(loader.getResourceAsStream("db.properties"));
            }catch (Exception e){
                System.out.println(e.toString());
            }
        }
    }

    public static String getPropertiesKey(String key) {
        if (properties == null) {
            setProperties();
        }
        return properties.getProperty(key, "default");
    }
}
