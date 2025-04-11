package com.swaglabs.utils;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class Validations {
    private Validations(){

    }
    @Step("Validate True : {condition} with message : {message}")
    public static void validateTrue(boolean condition, String message) {
        Assert.assertTrue(condition, message); // Condition first, message second

    }

    // Wrapper for Assert.assertFalse
    @Step("Validate False : {condition} with message : {message}")
    public static void validateFalse(boolean condition, String message) {
        Assert.assertFalse(condition, message); // Condition first, message second
    }

    // Wrapper for Assert.assertEquals
    @Step("Validate Equal : {actual} with expected : {expected} with message : {message}")
    public static void validateEqual(Object actual, Object expected, String message) {
        Assert.assertEquals(actual, expected, message); // Actual and expected first, message second
    }

    // Wrapper for Assert.assertNotEquals
    @Step("Validate Not Equal : {actual} with expected : {expected} with message : {message}")
    public static void validateNotEqual(Object actual, Object expected, String message) {
        Assert.assertNotEquals(actual, expected, message); // Actual and expected first, message second
    }

    // Wrapper for Assert.assertNull
    @Step("Validate Null : {object} with message : {message}")
    public static void validateNull(Object object, String message) {
        Assert.assertNull(object, message); // Object first, message second
    }

    // Wrapper for Assert.assertNotNull
    @Step("Validate Not Null : {object} with message : {message}")
    public static void validateNotNull(Object object, String message) {
        Assert.assertNotNull(object, message); // Object first, message second
    }

    // Wrapper for Assert.assertSame
    @Step("Validate Same : {actual} with expected : {expected} with message : {message}")
    public static void validateSame(Object actual, Object expected, String message) {
        Assert.assertSame(actual, expected, message); // Actual and expected first, message second
    }

    // Wrapper for Assert.assertNotSame


    public static void validateNotSame(Object actual, Object expected, String message) {
        Assert.assertNotSame(actual, expected, message); // Actual and expected first, message second
    }

    // Wrapper for Assert.fail
    @Step("Validate Fail : {message}")
    public static void validateFail(String message) {
        Assert.fail(message); // Only one parameter
    }
    // Wrapper for Assert page url
    @Step("Validate Page Url : {expectedUrl}")
    public static void validatePageUrl(WebDriver driver,String expectedUrl){
        Assert.assertEquals(BrowserActions.getCurrentURL(driver),expectedUrl)   ;
    }
    // Wrapper for Assert page url
    @Step("Validate Page Title : {expectedTitle} " )
    public static void validatePageTitle(WebDriver driver,String expectedTitle){
        Assert.assertEquals(BrowserActions.getWebPageTitle(driver),expectedTitle) ;
    }

}
