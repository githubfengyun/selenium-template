package com.changan.util;

import helper.BrowserFactory;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class BaseClass {
    @BeforeTest
    public void setupApplication() {
        BrowserFactory.initDriver();
        BrowserFactory.startBrowser("chrome", "http://trial.sitebeam.net/");
    }

    @AfterTest
    public void closeApplication() {
        BrowserFactory.closeBrowser();
    }
}
