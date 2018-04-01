package com.changan.util;

import io.github.bonigarcia.wdm.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class BrowserFactory {
    public static WebDriver getWebDriver() {
        return webDriver;
    }

    static WebDriver webDriver;

    public static void initDriver() {
        ChromeDriverManager.getInstance().setup();
        FirefoxDriverManager.getInstance().setup();
//        OperaDriverManager.getInstance().setup();
//        PhantomJsDriverManager.getInstance().setup();
//        EdgeDriverManager.getInstance().setup();
//        InternetExplorerDriverManager.getInstance().setup();
    }

    public static WebDriver startBrowser(String browserName, String url) {
        if(browserName.equals("firefox")) {
            webDriver = new FirefoxDriver();
        } else if(browserName.equals("chrome")) {
            webDriver = new ChromeDriver();
        } else if(browserName.equalsIgnoreCase("ie")) {
            webDriver = new InternetExplorerDriver();
        }

        webDriver.manage().window().maximize();
        webDriver.get(url);

        return webDriver;
    }

    public static void closeBrowser() {
        webDriver.close();
        webDriver.quit();
    }
}
