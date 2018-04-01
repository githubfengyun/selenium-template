package com.changan.util;

import com.changan.util.BrowserFactory;
import org.apache.log4j.PropertyConfigurator;
import org.testng.ITestResult;
import org.testng.annotations.*;
import com.aventstack.extentreports.reporter.*;
import com.aventstack.extentreports.*;
import com.aventstack.extentreports.Status;

public class BaseClass {
    static ConfigReader cr;
    static ExtentTest logger;
    static ExtentReports extentReports;

    public static void setLogger(ExtentTest logger) {
        BaseClass.logger = logger;
    }

    public static ConfigReader getCr(){
        return cr;
    }

    public static ExtentReports getExtentReports(){
        return extentReports;
    }

    @BeforeSuite
    public void init(){
        PropertyConfigurator
                .configure("./configuration/log4j.property");
        Log.info("Init logger, Reader, BrowserDriver...");
        cr = new ConfigReader();
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(cr.getEventReportPath() + "/extent.html");
        htmlReporter.config().setResourceCDN(ResourceCDN.EXTENTREPORTS);
//        KlovReporter klovReporter = new KlovReporter();
//        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("extent.html");
        extentReports = new ExtentReports();
        extentReports.attachReporter(htmlReporter);

    }

    @BeforeTest
    public void setupApplication() {
        BrowserFactory.initDriver();
        BrowserFactory.startBrowser("chrome", cr.getApplicationUrl());
    }

    @AfterMethod
    public void tearDown(ITestResult testResult) {
//        if(testResult.getStatus() == ITestResult.FAILURE)
        if (ITestResult.FAILURE == testResult.getStatus()) {
            try {
                String capturePath = Utility.captureScreenshot(BrowserFactory.getWebDriver(), testResult.getName());
                logger.log(Status.FAIL, testResult.getName());
                logger.fail("Screen Shot:" + logger.addScreenCaptureFromPath(capturePath));
                System.out.println("Screenshot taken");
            } catch (Exception e) {

                System.out.println("Exception while taking screenshot " + e.getMessage());
            }
        }
    }

    @AfterTest
    public void closeApplication() {
        BrowserFactory.closeBrowser();
    }

    @AfterSuite
    public void release(){
        extentReports.flush();
    }
}
