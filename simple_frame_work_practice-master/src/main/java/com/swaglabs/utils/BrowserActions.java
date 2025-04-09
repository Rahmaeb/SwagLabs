package com.swaglabs.utils;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

import java.net.URL;

public class BrowserActions {
    //private constructor to forbid instantiation
    private void browserActions(){

    }
    @Step("Opening the website : {url}")
    public static void openWebsite(WebDriver driver , String url) {
        driver.navigate().to(url);
        LogsUtils.info("Opening the website : " , url);
    }
    @Step("Getting the current URL")
    public static String getCurrentURL(WebDriver driver){
        LogsUtils.info("Getting the current URL : ",driver.getCurrentUrl());
        return driver.getCurrentUrl();
    }
    //get page title
    @Step("Getting the page title")
    public static String getWebPageTitle(WebDriver driver){
        LogsUtils.info("Getting the page title : ",driver.getTitle());
        return driver.getTitle();
    }
    //refresh page
    @Step("Refreshing the page")
    public static void  refreshPage(WebDriver driver){
        LogsUtils.info("Refreshing the page");
        driver.navigate().refresh();
    }
    @Step("Closing the browser")
    public static void quitBrowser(WebDriver driver){
        LogsUtils.info("Closing the browser");
        driver.quit();
    }
}
