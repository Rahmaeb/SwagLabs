package com.swaglabs.utils;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static com.swaglabs.utils.ElementAction.findElement;

public class Scrolling {
    //private constructor to forbid instantiation
    private Scrolling(){
    }
    @Step("Scrolling to the element : {locator}")
    public static void scrollToWebElement(WebDriver driver, By locator) {
        // Find the element using the locator
        WebElement element = findElement(driver,locator);
        // Scroll to the element
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        LogsUtils.info("Scrolling to the element : " , locator.toString());
    }

}
