package com.changan.testcases;


import com.changan.util.BaseClass;
import com.com.changan.pageobjects.LoginPage;
import helper.BrowserFactory;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

public class VerifyLogin extends BaseClass{
    @Test
    public void checkLoginUser() {
        System.out.println("in checkloginuser");
        LoginPage loginPage = PageFactory.initElements(BrowserFactory.getWebDriver(), LoginPage.class);
        loginPage.loginSiteBeam("admin", "admin");
    }
}