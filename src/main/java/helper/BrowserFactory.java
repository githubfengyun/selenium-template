package helper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class BrowserFactory {
    public static WebDriver getWebDriver() {
        return webDriver;
    }

    static WebDriver webDriver;

    public static void initDriver() {
        System.out.println("init set pro");
        System.setProperty("webdriver.chrome.driver","/Users/jackysun/Desktop/IdeaProjects/selenium-template/src/test/resources/lib/chromedriver");
        System.out.println("init set pro end com");
    }

    public static WebDriver startBrowser(String browserName, String url) {
        if(browserName.equals("firefox")) {
            webDriver = new FirefoxDriver();
        } else if(browserName.equals("chrome")) {
            webDriver = new ChromeDriver();
            System.out.println("init set pro ccchrome end com");
        } else if(browserName.equalsIgnoreCase("ie")) {
            webDriver = new InternetExplorerDriver();
        }

        webDriver.manage().window().maximize();
        webDriver.get(url);

        return webDriver;
    }

    public static void closeBrowser() {
        webDriver.close();
        webDriver.quit();
    }
}
