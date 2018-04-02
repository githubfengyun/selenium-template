package com.changan.pageobjects.ShopClient;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.changan.util.BrowserFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class IndexPage {
    WebDriver webDriver;
    public IndexPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(linkText="Sign in")
    WebElement lnkSignIn;

    @FindBy(linkText = "Sign up")
    WebElement lnkSignUp;

    public LoginPage signIn(){
        lnkSignIn.click();
        return new LoginPage(webDriver);
    }

    public RegistryPage signUp(ExtentTest extentTest){
        lnkSignUp.click();
        RegistryPage registryPage = new RegistryPage();
        System.out.println(">>>>>.." + BrowserFactory.getWebDriver().getTitle());
        Assert.assertEquals(BrowserFactory.getWebDriver().getTitle(), "Join GitHub · GitHub?");
        extentTest.log(Status.PASS, "Check registry page by pass");
        return registryPage;
    }
}
