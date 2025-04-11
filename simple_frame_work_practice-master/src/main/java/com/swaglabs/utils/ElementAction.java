package com.swaglabs.utils;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class ElementAction {
    //private constructor to forbid instantiation
    private ElementAction(){
    }

    // send data to an element
    @Step("Sending data to the element : {locator} with data : {data}")
    public static void sendData(WebDriver driver, By locator , String data){
        Waits.waitForElementToBeVisible(driver,locator);
        Scrolling.scrollToWebElement(driver,locator);
        findElement(driver,locator).sendKeys(data);
        LogsUtils.info("The data sent to the element is : " , data , " and the locator is : " , locator.toString());
    }
    // click on an element
    @Step("Clicking on the element : {locator}")
    public static void clickElement(WebDriver driver , By locator){
        Waits.waitForElementToBeClickable(driver,locator);
        Scrolling.scrollToWebElement(driver,locator);
        findElement(driver,locator).click();
        LogsUtils.info("The element is clicked and the locator is : " , locator.toString());
    }
    // get text of an element
    @Step("Getting the text of the element : {locator}")
    public static String getElementText(WebDriver driver , By locator){
        Waits.waitForElementToBeVisible(driver,locator);
        Scrolling.scrollToWebElement(driver,locator);
        LogsUtils.info("Getting element text :", locator.toString() , "Text : " , findElement(driver,locator).getText());
        return findElement(driver,locator).getText();

    }
    // get the value of an element
    @Step("Getting the value of the element : {locator}")
    public static String getElementValue(WebDriver driver , By locator){
        Waits.waitForElementToBeVisible(driver,locator);
        Scrolling.scrollToWebElement(driver,locator);
        LogsUtils.info("Getting element value :", locator.toString() , "Text : " , findElement(driver,locator).getDomProperty("value"));
        return  findElement(driver,locator).getDomProperty("value");
    }
    // find an element
    @Step("Finding the element : {locator}")
    public static WebElement findElement(WebDriver driver ,By locator){
         return driver.findElement(locator);
    }
    @Step("Selecting option by value from dropdown: {locator} with value: {value}")
    public static void selectByValue(WebDriver driver, By locator, String value) {
        Scrolling.scrollToWebElement(driver, locator);
        Waits.waitForElementToBeVisible(driver, locator);
        new Select(findElement(driver, locator)).selectByValue(value);
        LogsUtils.info("Selected option by value: ", value, " from dropdown: ", locator.toString());
    }

    @Step("Selecting option by visible text from dropdown: {locator} with text: {text}")
    public static void selectByVisibleText(WebDriver driver, By locator, String text) {
        Waits.waitForElementToBeVisible(driver, locator);
        Scrolling.scrollToWebElement(driver, locator);
        new Select(findElement(driver, locator)).selectByVisibleText(text);
        LogsUtils.info("Selected option by text: ", text, " from dropdown: ", locator.toString());
    }

    @Step("Selecting option by index from dropdown: {locator} with index: {index}")
    public static void selectByIndex(WebDriver driver, By locator, int index) {
        Waits.waitForElementToBeVisible(driver, locator);
        Scrolling.scrollToWebElement(driver, locator);
        new Select(findElement(driver, locator)).selectByIndex(index);
        LogsUtils.info("Selected option by index: ", String.valueOf(index), " from dropdown: ", locator.toString());
    }
    // Add to ElementAction.java
    @Step("Finding multiple elements: {locator}")
    public static List<WebElement> findElements(WebDriver driver, By locator) {
        Waits.waitForElementToBeVisible(driver, locator);
        Scrolling.scrollToWebElement(driver, locator);
        LogsUtils.info("Finding multiple elements with locator: ", locator.toString());
        return driver.findElements(locator);
    }




}
