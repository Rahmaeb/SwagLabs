package com.swaglabs.pages;

import com.swaglabs.utils.CustomSoftAssertion;
import com.swaglabs.utils.ElementAction;
import com.swaglabs.utils.LogsUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.locators.RelativeLocator;

public class ProductDetailsPage {

    //Variables

    private static WebDriver driver ;

    // Locators
    private final By productName = By.xpath("//div[@data-test=\"inventory-item-name\"]");

    // constructor
    public ProductDetailsPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("getting the product Name in PDP")
    private String getProductName(){
        return ElementAction.getElementText(driver,productName);
    }
    @Step("getting the product Disc in PDP")
    private String getProductDisc(){
        By productDisc = RelativeLocator.with(By.xpath("//div[@data-test=\"inventory-item-desc\"]")).below(productName);
        return ElementAction.getElementText(driver,productDisc);
    }
    @Step("getting the product Price in PDP")
    private String getProductPrice(){
        By productPrice = RelativeLocator.with(By.xpath("//div[@data-test=\"inventory-item-price\"]")).below(productName);
        return ElementAction.getElementText(driver,productPrice);
    }
//    validation
@Step("Verify product details match between inventory and PDP")
public ProductDetailsPage verifyProductDetailsMatch(InventoryPage.productdetails inventoryDetails) {
    verifyProductNameIsEqual(inventoryDetails.name())
            .verifyProductDiscIsEqual(inventoryDetails.description())
            .verifyProductPriceIsEqual(inventoryDetails.price());
    return this;
}

    private ProductDetailsPage verifyProductNameIsEqual(String expectedName) {
        String actualName = getProductName();
        CustomSoftAssertion.getInstance().assertEquals(
                expectedName, actualName,
                "Product name mismatch between inventory and PDP");
        logComparisonResult("Name", expectedName, actualName);
        return this;
    }

    private ProductDetailsPage verifyProductDiscIsEqual(String expectedDesc) {
        String actualDesc = getProductDisc();
        CustomSoftAssertion.getInstance().assertEquals(
                expectedDesc, actualDesc,
                "Product description mismatch between inventory and PDP");
        logComparisonResult("Description", expectedDesc, actualDesc);
        return this;
    }

    private ProductDetailsPage verifyProductPriceIsEqual(String expectedPrice) {
        String actualPrice = getProductPrice().replaceAll("[^0-9.-]", "");
        CustomSoftAssertion.getInstance().assertEquals(
                expectedPrice, actualPrice,
                "Product price mismatch between inventory and PDP");
        logComparisonResult("Price", expectedPrice, actualPrice);
        return this;
    }

    private void logComparisonResult(String field, String expected, String actual) {
        if (expected.equals(actual)) {
            LogsUtils.info(field + " matches: " + expected);
        } else {
            LogsUtils.error(field + " mismatch. Expected: " + expected + ", Actual: " + actual);
        }
    }


}
