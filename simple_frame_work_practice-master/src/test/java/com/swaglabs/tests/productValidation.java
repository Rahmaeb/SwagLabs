package com.swaglabs.tests;

import com.swaglabs.driver.DriverManager;
import com.swaglabs.pages.InventoryPage;
import com.swaglabs.pages.LoginPage;
import com.swaglabs.pages.ProductDetailsPage;
import com.swaglabs.utils.CustomSoftAssertion;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class productValidation extends BaseClass{

    private InventoryPage inventoryPage;

    @BeforeClass
    public void loginAndSetup() {
        // Login fresh for test
        new LoginPage(DriverManager.getDriver())
                .enterUserName(testData.getJsonData("login-credentials.users.standardUser.username"))
                .enterPassword(testData.getJsonData("login-credentials.users.standardUser.password"))
                .clickLogin()
                .assertSuccessfulLoginSoft();

        // Create new page instance for each test
        inventoryPage = new InventoryPage(DriverManager.getDriver());
        ProductDetailsPage PDP = new ProductDetailsPage(DriverManager.getDriver());
        // Reset soft assertions
        CustomSoftAssertion.getInstance(); // Creates fresh instance
    }
    @Test
    public void validateProductInInventoryPageIsSameAsProductInPDP(){
        String productName = testData.getJsonData("product-names.item-2.name");

        InventoryPage.productdetails inventoryDetails = inventoryPage.getProductDetails(productName);

        inventoryPage.openPDPForProduct(productName)
                .verifyProductDetailsMatch(inventoryDetails);

    }

}
