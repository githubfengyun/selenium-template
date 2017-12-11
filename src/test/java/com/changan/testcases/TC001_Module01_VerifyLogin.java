package com.changan.testcases;


import com.changan.ddt.ExcelSheetDriver;
import com.changan.util.BaseClass;
import com.com.changan.pageobjects.LoginPage;
import com.changan.util.BrowserFactory;
import jxl.read.biff.BiffException;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import java.io.IOException;

public class TC001_Module01_VerifyLogin extends BaseClass{
    @Test
    public void checkLoginUser() {
        ExcelSheetDriver xlsUtil = null;
        try {
            xlsUtil = new ExcelSheetDriver("./src/test/resources/testdata/tests-example.xls");
            xlsUtil.columnDictionary();
        } catch (BiffException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String username = xlsUtil.readCell("correct", 6);
        System.out.println("uname ---" + username);
        LoginPage loginPage = PageFactory.initElements(BrowserFactory.getWebDriver(), LoginPage.class);
        loginPage.loginSiteBeam("admin", "admin");
    }
}