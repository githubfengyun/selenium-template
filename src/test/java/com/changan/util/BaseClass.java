package com.changan.util;

import com.changan.util.BrowserFactory;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class BaseClass {
    @BeforeTest
    public void setupApplication() {
        PropertyConfigurator
                .configure("./configuration/log4j.property");
        Log.info("Init logger, Reader, BrowserDriver...");
        ConfigReader cr = new ConfigReader();
        BrowserFactory.initDriver();
        BrowserFactory.startBrowser("firefox", cr.getApplicationUrl());
    }

    @AfterTest
    public void closeApplication() {
        BrowserFactory.closeBrowser();
    }
}
