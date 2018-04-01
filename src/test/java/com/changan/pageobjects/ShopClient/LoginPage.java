package com.changan.pageobjects.ShopClient;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.changan.util.BrowserFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class LoginPage {
    public LoginPage() {
        PageFactory.initElements(BrowserFactory.getWebDriver(), this);
    }

    @FindBy(name="login")
    WebElement txtBoxUserName;

    @FindBy(name="password")
    WebElement txtBoxPassword;

    @FindBy(name="commit")
    WebElement btnSignIn;

    public HomePage login(String username, String password, ExtentTest extentTest){
        txtBoxUserName.sendKeys(username);
        txtBoxPassword.sendKeys(password);
        btnSignIn.click();
        HomePage homePage = new HomePage();
        Assert.assertEquals(true, homePage.divYourRepos.isDisplayed());
        extentTest.log(Status.PASS, "Check Login by pass");
        return homePage;
    }
}
