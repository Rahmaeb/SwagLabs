package com.swaglabs.pages;

import com.swaglabs.utils.*;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    //Variables

    private static WebDriver driver ;

    //Varables
    private static final String baseUrl =  propertiesUtils.getPropertiesValue("baseUrl");
    private static final String homeUrl =  propertiesUtils.getPropertiesValue("homeUrl");
    private static final String appTitle = propertiesUtils.getPropertiesValue("appTitle");
    private static final String errorMSG = propertiesUtils.getPropertiesValue("errorMSG");
    // Locators
    private final By usernameField = By.xpath("//input[@id='user-name']");
    private final By passwordField = By.xpath("//input[@id='password']");
    private final By loginButton   = By.xpath("//input[@id='login-button']");
    private final By errorMessage  = By.xpath("//h3[@data-test=\"error\"]");

    // constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    //Navigate to website url
    @Step("Navigate to login page")
    public static void navigateToLoginPage(){
        BrowserActions.openWebsite(driver,baseUrl);
    }
    //Activation
    @Step("Enter username : {username}")
    public LoginPage enterUserName(String username){
        ElementAction.sendData(driver,usernameField,username);
        return this;
    }
    @Step("Enter password : {password}.")
    public LoginPage enterPassword(String password){
       ElementAction.sendData(driver,passwordField,password);
        return this;
    }
    @Step("Click on login button.")
    public LoginPage clickLogin(){
        ElementAction.clickElement(driver,loginButton);
        return this;
    }
    @Step("Get error message.")
    public String getErrorMessage(){
        return ElementAction.getElementText(driver,errorMessage);
    }

    //Validation
    @Step("Assert login page url.")
    public LoginPage assertLoginPageUrl(){
        CustomSoftAssertion.getInstance().assertEquals(BrowserActions.getCurrentURL(driver),homeUrl,"The url doesn't match");
        return this;
    }
    @Step("Assert login page title.")
    public LoginPage assertLginPageTitle(){
        CustomSoftAssertion.getInstance().assertEquals(BrowserActions.getWebPageTitle(driver),appTitle,"The Page title doesn't match ");
        return this;
    }
    @Step("Assert successfull login soft.")
    public LoginPage assertSuccessfulLoginSoft(){
        assertLoginPageUrl().assertLginPageTitle() ;
        return this;
    }
    @Step("Assert successful login.")
    public LoginPage assertSuccssfulLogin(){
        CustomSoftAssertion.getInstance().assertEquals(BrowserActions.getCurrentURL(driver),homeUrl);
        return this;
    }
    @Step("Assert successful login .")
    public LoginPage assertunSuccessfulLogin(){
        CustomSoftAssertion.getInstance().assertEquals(getErrorMessage(),errorMSG,"Error message is not expected");
        return this;
    }
}
