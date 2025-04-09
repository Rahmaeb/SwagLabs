package com.swaglabs.pages;

import com.swaglabs.utils.CustomSoftAssertion;
import com.swaglabs.utils.ElementAction;
import com.swaglabs.utils.LogsUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {
    private final WebDriver driver;

    private final By checkoutButton = By.xpath("//button[@data-test=\"checkout\"]");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    // Improved locator methods
    private By getProductNameLocator(String productName) {
        // Find the product name div near the remove button for this product
        return By.xpath("//button[contains(@data-test,'remove-" + productName.toLowerCase().replace(" ", "-") + "')]" +
                "/ancestor::div[@class='cart_item']//div[@data-test='inventory-item-name']");
    }

    private By getPriceLocator(String productName) {
        // Find the price div near the remove button for this product
        return By.xpath("//button[contains(@data-test,'remove-" + productName.toLowerCase().replace(" ", "-") + "')]" +
                "/ancestor::div[@class='cart_item']//div[@data-test='inventory-item-price']");
    }

    @Step("Getting the product name from cart")
    private String getProductNameFromCart(String productName) {
        By productNameLocator = getProductNameLocator(productName);
        return ElementAction.getElementText(driver, productNameLocator);
    }

    @Step("Getting the product price from cart")
    private String getProductPriceFromCart(String productName) {
        By productPriceLocator = getPriceLocator(productName);
        return ElementAction.getElementText(driver, productPriceLocator);
    }

    // Validations
    public CartPage assertProductDetailsFromCart(String productName, String productPrice) {
        String actualName = getProductNameFromCart(productName);
        String actualPrice = getProductPriceFromCart(productName);

        LogsUtils.info("Actual Name: " + actualName + " | Actual Price: " + actualPrice);

        if (!actualName.equals(productName)){
            LogsUtils.error("Name mismatch - Expected: " + productName + " | Actual: " + actualName);
        }
        if (!actualPrice.contains(productPrice)) { // Using contains in case there's a $ symbol
            LogsUtils.error("Price mismatch - Expected: " + productPrice + " | Actual: " + actualPrice);
        }

        CustomSoftAssertion.getInstance().assertEquals(actualName, productName, "Product name doesn't match");
        CustomSoftAssertion.getInstance().assertTrue(actualPrice.contains(productPrice), "Product price doesn't match");

        return this;
    }
    public CheckoutPage openCheckoutPage(){
        ElementAction.clickElement(driver,checkoutButton);
        return new CheckoutPage(driver);
    }


}