package com.swaglabs.tests;

import com.swaglabs.driver.DriverManager;
import com.swaglabs.pages.*;
import org.testng.annotations.Test;

import static com.swaglabs.utils.TimeStampUtils.getTimestamp;
// to generate the allure report in single file run this command in terminal "allure generate test-outputs/allure-results -o allure-report --single-file"

public class E2e extends BaseClass {

    @Test
    public void successfullLogin() {
        new LoginPage(DriverManager.getDriver())
                .enterUserName(testData.getJsonData("login-credentials.users.errorUser.username"))
                .enterPassword(testData.getJsonData("login-credentials.users.standardUser.password"))
                .clickLogin()
                .assertSuccessfulLoginSoft();
    }

    @Test(dependsOnMethods = "successfullLogin")
    public void addToCart() {
        new InventoryPage(DriverManager.getDriver())
                .addProductToCart(testData.getJsonData("product-names.item-1.name"))
                .addProductToCart(testData.getJsonData("product-names.item-2.name"))
                .assertProductsAdded(testData.getJsonData("product-names.item-2.name"));
    }

    @Test(dependsOnMethods = "addToCart")
    public void checkProductsAddedToTheCart() {
        new InventoryPage(DriverManager.getDriver())
                .openCartPage()
                .assertProductDetailsFromCart(
                        testData.getJsonData("product-names.item-1.name"),
                        testData.getJsonData("product-names.item-1.price"))
                .assertProductDetailsFromCart(
                        testData.getJsonData("product-names.item-2.name"),
                        testData.getJsonData("product-names.item-2.price"));
    }

    @Test(dependsOnMethods = "checkProductsAddedToTheCart")
    public void checkoutProcess() {
        final String firstName = testData.getJsonData("checkout-data.first-name")+"_"+getTimestamp();
        final String lastName =testData.getJsonData("checkout-data.last-name")+"_"+getTimestamp();
        new CartPage(DriverManager.getDriver())
                .openCheckoutPage()
                .sendCustomerDetails(
                        firstName,
                        lastName,
                        testData.getJsonData("checkout-data.zip-code"))
                .validateCheckoutInformation(
                        firstName,
                        lastName,
                        testData.getJsonData("checkout-data.zip-code"));
    }

    @Test(dependsOnMethods = "checkoutProcess")
    public void proceedCheckoutOverview() {
        new CheckoutPage(DriverManager.getDriver())
                .clickContinue()
                .clickFinish()
                .asserThankYouMsgAppeared(testData.getJsonData("checkout-final-data.thank-you-msg"));
    }
    @Test(dependsOnMethods = "proceedCheckoutOverview")
    public void logout(){
        new CheckoutCompletePage(DriverManager.getDriver())
                .clickBackToHomeButton()
                .clickOnLogoutButton();
    }
}
