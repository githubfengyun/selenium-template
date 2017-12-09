package com.com.changan.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginPage {
    WebDriver webDriver;

    public LoginPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    @FindBy(id="f1_username")
    WebElement username;

    @FindBy(how = How.ID, using="f1_password")
    WebElement password;

    @FindBy(how = How.XPATH, using=".//*[@name=\"f1_ok\"]")
    WebElement submitButton;

    public void loginSiteBeam(String uid, String pwd){
        username.sendKeys(uid);
        password.sendKeys(pwd);
        submitButton.click();
    }
}
