package com.changan.testcases.shopclient;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.changan.ddt.ExcelDriver;
import com.changan.pageobjects.ShopClient.IndexPage;
import com.changan.pageobjects.ShopClient.LoginPage;
import com.changan.util.BaseClass;
import com.changan.util.BrowserFactory;
import org.testng.annotations.Test;

import java.io.IOException;

public class TC002_ShopClient_VerifyRegPageTitle extends BaseClass{
    static ExtentTest extentTest;

    public static ExtentTest getExtentTest() {
        return extentTest;
    }

    @Test
    public void verifyRegistryPageTitle() {
//        super.getExtent().createTest("checkLoginUser");
        ExtentReports extentReports = super.getExtentReports();
        ExcelDriver xlsUtil = null;
        try {
            xlsUtil = new ExcelDriver("./src/test/resources/testdata/TC001_ShopClient_VerifyLogin.xlsx");
            ExcelDriver.columnDictionary("Example Test");
        } catch (IOException e) {
            e.printStackTrace();
        }

        extentTest = extentReports.createTest("TC002_VerifyRegPageTitle");
        BaseClass.setLogger(extentTest);
        extentTest.log(Status.INFO, "Startup Browser.");
        IndexPage indexPage = new IndexPage(BrowserFactory.getWebDriver());
        indexPage.signUp(extentTest);
    }
}
