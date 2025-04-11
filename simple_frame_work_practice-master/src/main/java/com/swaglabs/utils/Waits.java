package com.swaglabs.utils;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Waits {
    // This is a private constructor of the class
    private Waits(){
    }

// present -> visible  -> clickable


// Wait for an element to be present

    @Step("Waiting for element to be present : {locator}")

    public static WebElement waitForElementToPresent(WebDriver driver, By locator){
        LogsUtils.info("Waiting for element to be present : " , locator.toString());
        return new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver1 -> driver1.findElement(locator));
    }

//    wait for an element to be visible

    @Step("Waiting for element to be visible : {locator}")

    public static WebElement  waitForElementToBeVisible(WebDriver driver, By locator){
        return new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver1 -> {
            WebElement element =waitForElementToPresent(driver,locator);
            LogsUtils.info("Waiting for element to be visible : " , locator.toString());
            return element.isEnabled() ? element :null ;
        });
    }
//    wait for an element to be clickable

    @Step("Waiting element to be clickable : {locator}")

    public static WebElement waitForElementToBeClickable(WebDriver driver, By locator){
        return new WebDriverWait(driver,Duration.ofSeconds(10)).until(driver1 ->{
            WebElement element= waitForElementToPresent(driver,locator);
            LogsUtils.info("Waiting for element to be clickable : " , locator.toString());
            return element.isEnabled() ? element : null;
        } );
}
}
