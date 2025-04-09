package com.swaglabs.pages;

import com.swaglabs.utils.CustomSoftAssertion;
import com.swaglabs.utils.ElementAction;
import com.swaglabs.utils.LogsUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutCompletePage {
    //variables
    private static WebDriver driver ;
    private final By backToHomeButton = By.xpath("//button[@data-test=\"back-to-products\"]");
    private final By thankYouMsg = By.xpath("//h2[@data-test=\"complete-header\"]");

    //constructor
    public CheckoutCompletePage(WebDriver driver){
        this.driver= driver;
    }
    // Actions
    @Step("clicking on the finish button ")
    public InventoryPage clickBackToHomeButton(){
        ElementAction.clickElement(driver, backToHomeButton);
        LogsUtils.info("Click finish button ");
        return new InventoryPage(driver);
    }
    //
    @Step("assert the theank you message")
    public CheckoutCompletePage asserThankYouMsgAppeared(String thankYouMssg){
        CustomSoftAssertion.getInstance().assertEquals(ElementAction.getElementText(driver,thankYouMsg),thankYouMssg,"no message appear to the user");
        LogsUtils.info("Assert finding the thank you message");
        return this ;
    }



}
