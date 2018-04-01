package com.changan.pageobjects.ShopClient;

import com.changan.util.BrowserFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    public HomePage(){
        PageFactory.initElements(BrowserFactory.getWebDriver(), this);
    }

    @FindBy(id="your_repos")
    WebElement divYourRepos;
}
