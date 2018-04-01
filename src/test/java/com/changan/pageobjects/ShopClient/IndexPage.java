package com.changan.pageobjects.ShopClient;

import com.changan.util.BrowserFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class IndexPage {
    public IndexPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(linkText="Sign in")
    WebElement lnkSignIn;

    public LoginPage signIn(){
        lnkSignIn.click();
        return new LoginPage();
    }
}
