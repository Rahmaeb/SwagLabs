package com.swaglabs.utils;

import com.swaglabs.driver.DriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.io.File;

import static com.swaglabs.utils.TimeStampUtils.getTimestamp;

public class ScreenShotsUtils {
    public static final String screenshotPath = "test-outputs/screenshots/";

    public  ScreenShotsUtils(){
        super();
    }
    public static void  takeScreenShot(String screenshotName){
        try {
            File screenshot =((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.FILE);
            File screenshorFile = new File(screenshotPath + screenshotName +"_"+getTimestamp()+".png;");
            FileUtils.copyFile(screenshot,screenshorFile);
            AllureUtils.AttachScreenshotToAllureReport(screenshotName,screenshorFile.getPath());
        }
        catch (Exception e){
            LogsUtils.error("Failed to take a screenshot" + e.getMessage());
        }
    }

}
