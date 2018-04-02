package com.changan.pageobjects.ShopClient;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class LoginPage {
    WebDriver webDriver;
    public LoginPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(name="usr")
    WebElement txtBoxUserName;

    @FindBy(name="pwd")
    WebElement txtBoxPassword;

    @FindBy(xpath = "//*[@id=\"loginForm\"]/button[2]")
    WebElement btnLogIn;

    public HomePage login(String username, String password, ExtentTest extentTest){
        txtBoxUserName.sendKeys(username);
        txtBoxPassword.sendKeys(password);
        btnLogIn.click();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        HomePage homePage = new HomePage(webDriver);
        Assert.assertEquals(true, homePage.btnVinSearch.isDisplayed());
        extentTest.log(Status.PASS, "Check Login by pass");
        return homePage;
    }
}
