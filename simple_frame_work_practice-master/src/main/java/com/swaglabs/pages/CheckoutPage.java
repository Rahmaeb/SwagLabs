package com.swaglabs.pages;

import com.swaglabs.utils.CustomSoftAssertion;
import com.swaglabs.utils.ElementAction;
import com.swaglabs.utils.LogsUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage {
    //Variables

    private static WebDriver driver ;

    private final By firstNameLocator = By.xpath("//input[@data-test=\"firstName\"]");
    private final By lastNameLocator = By.xpath("//input[@data-test=\"lastName\"]");
    private final By zipCodeLocator = By.xpath("//input[@data-test=\"postalCode\"]");
    private final By continueButton = By.id("continue");

    public CheckoutPage(WebDriver driver){
        this.driver= driver;
    }

    @Step("Enter customer details - First Name: {firstName}, Last Name: {lastName}, Zip Code: {zipCode}")
    public CheckoutPage sendCustomerDetails(String firstName, String lastName, String zipCode) {
        ElementAction.sendData(driver, firstNameLocator, firstName);
        LogsUtils.info("Typing first name: " + firstName + " in firstname field");

        ElementAction.sendData(driver, lastNameLocator, lastName);
        LogsUtils.info("Typing last name: " + lastName + " in lastname field");

        ElementAction.sendData(driver, zipCodeLocator, zipCode);
        LogsUtils.info("Typing zip code: " + zipCode + " in zip code field");

        return this;
    }
    @Step("Clicking continue button" )
    public CheckoutOverView clickContinue(){
        ElementAction.clickElement(driver,continueButton);
        LogsUtils.info("Clicking on continue button");
        return new CheckoutOverView(driver);
    }
    // Validation
    @Step("Validate checkout information")
    public CheckoutPage validateCheckoutInformation(String expectedFirstName, String expectedLastName, String expectedZipCode) {
        String actualFirstName = ElementAction.getElementValue(driver,firstNameLocator);
        String actualLastName = ElementAction.getElementValue(driver,lastNameLocator);
        String actualZipCode = ElementAction.getElementValue(driver,zipCodeLocator);

        CustomSoftAssertion.getInstance().assertEquals(
                actualFirstName, expectedFirstName,
                "First name doesn't match. Expected: " + expectedFirstName + " but found: " + actualFirstName
        );
        CustomSoftAssertion.getInstance().assertEquals(
                actualLastName, expectedLastName,
                "Last name doesn't match. Expected: " + expectedLastName + " but found: " + actualLastName
        );
        CustomSoftAssertion.getInstance().assertEquals(
                actualZipCode, expectedZipCode,
                "Zip code doesn't match. Expected: " + expectedZipCode + " but found: " + actualZipCode
        );

        return this;
    }

}
