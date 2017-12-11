package com.changan.util;

import org.apache.log4j.PropertyConfigurator;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {

    Properties properties;

    public ConfigReader() {
        try {
            File configFile = new File("./configuration/config.property");
            FileInputStream configFileInputStream = new FileInputStream(configFile);
            properties = new Properties();
            properties.load(configFileInputStream);
        } catch (Exception e) {
                e.printStackTrace();
        }
    }

    public String getApplicationUrl() {
        return properties.getProperty("URL");
    }
}
