package com.changan.testcases;

import com.changan.ddt.ExcelDriver;
import com.changan.util.BaseClass;
import com.changan.pageobjects.LoginPage;
import com.changan.util.BrowserFactory;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import java.io.IOException;

public class TC001_Module01_VerifyLogin extends BaseClass{
    @Test
    public void checkLoginUser() {
        ExcelDriver xlsUtil = null;
        try {
            xlsUtil = new ExcelDriver("./src/test/resources/testdata/tests-example.xlsx");
            ExcelDriver.columnDictionary("sheet1");
        } catch (IOException e) {
            e.printStackTrace();
        }
        String username = ExcelDriver.readCell("correct", 6);
        System.out.println("uname ---" + username);
        LoginPage loginPage = PageFactory.initElements(BrowserFactory.getWebDriver(), LoginPage.class);
        loginPage.loginSiteBeam("admin", "admin");
    }
}