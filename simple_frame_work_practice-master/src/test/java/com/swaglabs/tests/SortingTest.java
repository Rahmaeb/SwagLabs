package com.swaglabs.tests;

import com.swaglabs.driver.DriverManager;
import com.swaglabs.pages.InventoryPage;
import com.swaglabs.pages.LoginPage;
import com.swaglabs.utils.CustomSoftAssertion;
import com.swaglabs.utils.propertiesUtils;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SortingTest extends BaseClass {
    private InventoryPage inventoryPage;

    @BeforeMethod
    public void loginAndSetup() {
        // Ensure clean state by navigating back to login page
        DriverManager.getDriver().navigate().to(propertiesUtils.getPropertiesValue("baseUrl"));

        // Login fresh for each test
        new LoginPage(DriverManager.getDriver())
                .enterUserName(testData.getJsonData("login-credentials.users.standardUser.username"))
                .enterPassword(testData.getJsonData("login-credentials.users.standardUser.password"))
                .clickLogin()
                .assertSuccessfulLoginSoft();

        // Create new page instance for each test
        inventoryPage = new InventoryPage(DriverManager.getDriver());

        // Reset soft assertions
        CustomSoftAssertion.getInstance(); // Creates fresh instance
    }

    @AfterMethod
    public void logoutAndReset() {
        // Optional: Logout after each test if needed
        inventoryPage.clickOnLogoutButton();
    }

    @Test
    public void testNameAToZSort() {
        inventoryPage.sortProducts(testData.getJsonData("sort-options.aToZ"))
                .verifyProductNamesSortedAToZ();
        CustomSoftAssertion.customSoftAssertAll();
    }
    @Test
    public void testNameZToASort() {
        inventoryPage.sortProducts(testData.getJsonData("sort-options.zToA"))
                .verifyProductNamesSortedZToA();
        CustomSoftAssertion.customSoftAssertAll();
    }

    @Test
    public void testPriceLowToHigh() {
        inventoryPage.sortProducts(testData.getJsonData("sort-options.lowToHigh"))
                .verifyProductPricesSortedLowToHigh();
        CustomSoftAssertion.customSoftAssertAll();
    }

    @Test
    public void testPriceHighToLow() {
        inventoryPage.sortProducts(testData.getJsonData("sort-options.highToLow"))
                .verifyProductPricesSortedHighToLow();
        CustomSoftAssertion.customSoftAssertAll();
    }


}