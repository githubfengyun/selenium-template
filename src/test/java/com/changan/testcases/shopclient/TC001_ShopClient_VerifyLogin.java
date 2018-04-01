package com.changan.testcases.shopclient;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.changan.ddt.ExcelDriver;
import com.changan.util.BaseClass;
import com.changan.util.BrowserFactory;
import com.changan.pageobjects.ShopClient.HomePage;
import com.changan.pageobjects.ShopClient.IndexPage;
import com.changan.pageobjects.ShopClient.LoginPage;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import java.io.IOException;

public class TC001_ShopClient_VerifyLogin extends BaseClass {
    static ExtentTest extentTest;

    public static ExtentTest getExtentTest() {
        return extentTest;
    }

    @Test
    public void checkLoginUser() {
//        super.getExtent().createTest("checkLoginUser");
        ExtentReports extentReports = super.getExtentReports();
        ExcelDriver xlsUtil = null;
        try {
            xlsUtil = new ExcelDriver("./src/test/resources/testdata/TC001_ShopClient_VerifyLogin.xlsx");
            ExcelDriver.columnDictionary("Example Test");
        } catch (IOException e) {
            e.printStackTrace();
        }
        String username = ExcelDriver.readCell("Username", 1);
        String password = ExcelDriver.readCell("Password", 1);
        System.out.println("uname ---" + username);

        extentTest = extentReports.createTest("CheckLogin");
        BaseClass.setLogger(extentTest);
        extentTest.log(Status.INFO, "Startup Browser.");
        IndexPage indexPage = new IndexPage(BrowserFactory.getWebDriver());
        LoginPage loginPage = indexPage.signIn();
        HomePage homePage = loginPage.login(username, password, extentTest);

    }
}
