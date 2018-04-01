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

    public String getTestNgConfigPath() {
        return properties.getProperty("testng-config-path");
    }

    public String getTestNgXMLPath() {
        return properties.getProperty("testng-xml-path");
    }

    public String getProjectName() {
        return properties.getProperty("ProjectName");
    }

    public String getEventReportPath() {
        return properties.getProperty("ExtentReportPath");
    }
}
