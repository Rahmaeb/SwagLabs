package com.swaglabs.pages;

import com.swaglabs.utils.*;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.locators.RelativeLocator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class InventoryPage {
    private final WebDriver driver;

    private final By cartIcon    = By.className("shopping_cart_container");
    private final By burgerMenu  = By.id("react-burger-menu-btn");
    private final By logoutButton = By.xpath("//a[@data-test=\"logout-sidebar-link\"]");
    private final By SORT_DROPDOWN = By.className("product_sort_container");
    private final By itemsNameLocator = By.className("inventory_item_name");
    private final By itemsPriceLocator = By.className("inventory_item_price");

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Open the inventory page.")
    public InventoryPage navigateToInventoryPage() {
        BrowserActions.openWebsite(driver, propertiesUtils.getPropertiesValue("homeUrl"));
        return this;
    }

    @Step("Add the product '{productName}' to the cart")
    public InventoryPage addProductToCart(String productName) {
        By addToCartButton = RelativeLocator.with(By.tagName("button")).below(By.linkText(productName));
        ElementAction.clickElement(driver, addToCartButton);
        LogsUtils.info("The product:"+ productName+"is added to cart.");

        return this;
    }
    @Step("Open cart page.")
    public CartPage openCartPage(){
        ElementAction.clickElement(driver,cartIcon);
        return new CartPage(driver);
    }
    @Step ("Logout from the page")
    public LoginPage clickOnLogoutButton(){
        ElementAction.clickElement(driver,burgerMenu);
        ElementAction.clickElement(driver,logoutButton);
        return new LoginPage(driver);
    }
    @Step("sorting products")
    public InventoryPage sortProducts(String value) {
        ElementAction.selectByValue(driver, SORT_DROPDOWN, value);
        return this;
    }
    @Step("Getting all the products names")
    public List<String> getProductNames() {
        List<WebElement> items = ElementAction.findElements(driver, itemsNameLocator);
        return items.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    @Step("Getting all the products prices")
    public List<Double> getProductPrices() {
        List<WebElement> priceElements = ElementAction.findElements(driver, itemsPriceLocator);
        return priceElements.stream()
                .map(e -> Double.parseDouble(e.getText().replace("$", "")))
                .collect(Collectors.toList());
    }

    // validations
    @Step("Verify the product added to the cart")
    public void assertProductsAdded(String productName) {
        By removeButton = RelativeLocator.with(By.tagName("button")).below(By.linkText(productName));
        String actualValue = ElementAction.getElementText(driver,removeButton);
        LogsUtils.info("Actual :" + actualValue);
        CustomSoftAssertion.getInstance().assertEquals(actualValue,"Remove","product is added to the cart ");
        LogsUtils.info(productName +" product is added to the cart.");

    }
    @Step("Verify product names sorted A-Z")
    public void verifyProductNamesSortedAToZ() {
        List<String> actualNames = getProductNames();
        List<String> expectedNames = new ArrayList<>(actualNames);
        Collections.sort(expectedNames);
        LogsUtils.info("Verifying A-Z Sort:");
        LogsUtils.info("Expected order: " + String.join(", ", expectedNames));
        LogsUtils.info("Actual order: " + String.join(", ", actualNames));

        CustomSoftAssertion.getInstance().assertEquals(
                actualNames,
                expectedNames,
                "Products not sorted A-Z correctly"
        );
    }

    @Step("Verify product names sorted Z-A")
    public void verifyProductNamesSortedZToA() {
        List<String> actualNames = getProductNames();
        List<String> expectedNames = new ArrayList<>(actualNames);
        expectedNames.sort(Collections.reverseOrder());
        LogsUtils.info("Verifying Z-A Sort:");
        LogsUtils.info("Expected order: " + String.join(", ", expectedNames));
        LogsUtils.info("Actual order: " + String.join(", ", actualNames));
        CustomSoftAssertion.getInstance().assertEquals(
                actualNames,
                expectedNames,
                "Products not sorted Z-A correctly"
        );

    }

    @Step("Verify product prices sorted low-high")
    public void verifyProductPricesSortedLowToHigh() {
        List<Double> actualPrices = getProductPrices();
        List<Double> expectedPrices = new ArrayList<>(actualPrices);
        Collections.sort(expectedPrices);
        LogsUtils.info("Verifying Price Low-High Sort:");
        LogsUtils.info("Expected prices: " + expectedPrices.stream()
                .map(p -> "$" + p)
                .collect(Collectors.joining(", ")));
        LogsUtils.info("Actual prices: " + actualPrices.stream()
                .map(p -> "$" + p)
                .collect(Collectors.joining(", ")));
        CustomSoftAssertion.getInstance().assertEquals(
                actualPrices,
                expectedPrices,
                "Products not sorted by price low-high correctly"
        );
    }

    @Step("Verify product prices sorted high-low")
    public void verifyProductPricesSortedHighToLow() {
        List<Double> actualPrices = getProductPrices();
        List<Double> expectedPrices = new ArrayList<>(actualPrices);
        expectedPrices.sort(Collections.reverseOrder());
        LogsUtils.info("Verifying Price High-Low Sort:");
        LogsUtils.info("Expected prices: " + expectedPrices.stream()
                .map(p -> "$" + p)
                .collect(Collectors.joining(", ")));
        LogsUtils.info("Actual prices: " + actualPrices.stream()
                .map(p -> "$" + p)
                .collect(Collectors.joining(", ")));
        CustomSoftAssertion.getInstance().assertEquals(
                actualPrices,
                expectedPrices,
                "Products not sorted by price high-low correctly"
        );
    }

}