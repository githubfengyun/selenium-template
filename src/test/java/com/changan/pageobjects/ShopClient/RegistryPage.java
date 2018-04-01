package com.changan.pageobjects.ShopClient;

import com.changan.util.BrowserFactory;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegistryPage {
    public RegistryPage(){
        PageFactory.initElements(BrowserFactory.getWebDriver(), this);
    }

}
