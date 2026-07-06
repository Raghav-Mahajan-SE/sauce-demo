package com.saucedemo.config;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {

    private static Properties prop;

    public static String get(String key){

        try {
            FileInputStream file = new FileInputStream("src/test/java/com/saucedemo/resources/data/config.properties");
            prop = new Properties();
            prop.load(file);
        }catch(Exception e){
            throw new RuntimeException("config.properties is not loaded: " + e.getMessage());
        }
        String sysValue = System.getProperty(key);
        return (sysValue!=null)?sysValue:prop.getProperty(key);

    }
}

