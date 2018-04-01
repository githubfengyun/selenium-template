package com.changan.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.OutputType;
import org.apache.commons.io.FileUtils;
import java.io.File;

public class Utility {
    public static String captureScreenshot(WebDriver driver, String screenshotName) {
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;

            File source = ts.getScreenshotAs(OutputType.FILE);

//            String des =BaseClass.cr.getEventReportPath()+  "/Screenshots/" + screenshotName + ".png";
            String des =BaseClass.cr.getEventReportPath() + "/Screenshots/" + screenshotName + ".png";
            String htmlDesPath = "./Screenshots/" + screenshotName + ".png";
            FileUtils.copyFile(source, new File(des));

            System.out.println("Screenshot taken");
            return htmlDesPath;
        } catch (Exception e) {

            System.out.println("Exception while taking screenshot " + e.getMessage());
            return e.getMessage();
        }
    }
}
