package com.swaglabs.pages;

import com.swaglabs.utils.ElementAction;
import com.swaglabs.utils.LogsUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutOverView {
    //variables
    private static WebDriver driver ;
    private final By finishButton = By.xpath("//button[@data-test=\"finish\"]");
    //constructor
    public CheckoutOverView(WebDriver driver){
        this.driver= driver;
    }
    // Actions
    @Step("clicking on the finish button ")
    public CheckoutCompletePage clickFinish(){
        ElementAction.clickElement(driver,finishButton);
        LogsUtils.info("Click finish button ");
        return new CheckoutCompletePage(driver);
    }


}
