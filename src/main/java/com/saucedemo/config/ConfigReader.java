package com.saucedemo.config;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {

    private static final Properties prop = new Properties();

    static {
        try {
            FileInputStream file = new FileInputStream("src/test/java/com/saucedemo/resources/data/config.properties");
            prop.load(file);
        }catch(Exception e){
            throw new RuntimeException("config.properties is not loaded: " + e.getMessage());
        }
    }

    public static String get(String key){
        String sysValue = System.getProperty(key);
        return (sysValue!=null)?sysValue:prop.getProperty(key);
    }
}

