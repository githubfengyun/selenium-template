package com.changan.pageobjects.ShopClient;

import com.changan.util.BaseClass;
import com.changan.util.BrowserFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CarInfoPage extends BaseClass{
    public CarInfoPage(WebDriver webDriver){
        PageFactory.initElements(BrowserFactory.getWebDriver(), this);
    }
    @FindBy(className = "ant-card-head-title")
    WebElement lblCarInfo;
}
