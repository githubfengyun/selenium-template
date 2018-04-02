package com.changan.pageobjects.ShopClient;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.changan.util.BrowserFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class HomePage {
    WebDriver webDriver;
    public HomePage(WebDriver webDriver){
        this.webDriver = webDriver;
        PageFactory.initElements(BrowserFactory.getWebDriver(), this);
    }

    @FindBy(xpath = "//*[@id='root']/div/div/div[1]/div/div[2]/div/div/ul/li[1]/a/span")
    WebElement spanFirstPage;

    @FindBy(id="vin")
    WebElement txtVinSearch;

    @FindBy(how=How.CSS,using="button[class='ant-btn ant-input-search-button ant-btn-primary ant-btn-lg']")
    WebElement btnVinSearch;

    public void searchByVin(String vinCode, ExtentTest extentTest) throws InterruptedException {
        txtVinSearch.sendKeys(vinCode);
        btnVinSearch.click();
        Thread.sleep(5000);
        CarInfoPage carInfoPage = new CarInfoPage(webDriver);
        Assert.assertEquals(true, carInfoPage.lblCarInfo.isDisplayed());
        extentTest.log(Status.PASS, "Check searchByVin by pass");
    }
}
