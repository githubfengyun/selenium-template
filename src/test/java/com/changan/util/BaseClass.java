package com.changan.util;

import helper.BrowserFactory;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class BaseClass {
    @BeforeTest
    public void setupApplication() {
        ConfigReader cr = new ConfigReader();
        BrowserFactory.initDriver();
        BrowserFactory.startBrowser("firefox", cr.getApplicationUrl());
    }

    @AfterTest
    public void closeApplication() {
        BrowserFactory.closeBrowser();
    }
}
