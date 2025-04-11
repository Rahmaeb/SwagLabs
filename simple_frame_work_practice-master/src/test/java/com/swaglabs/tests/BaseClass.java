package com.swaglabs.tests;

import com.swaglabs.driver.DriverManager;
import com.swaglabs.listeners.TestNGListeners;
import com.swaglabs.pages.InventoryPage;
import com.swaglabs.pages.LoginPage;
import com.swaglabs.utils.AllureUtils;
import com.swaglabs.utils.LogsUtils;
import com.swaglabs.utils.jsonUtils;
import com.swaglabs.utils.propertiesUtils;
import org.testng.annotations.*;

import java.io.File;

@Listeners(TestNGListeners.class)
public class BaseClass {
    protected jsonUtils testData;
    protected File allureResultFile = new File("test-outputs/allure-results");

    @BeforeClass
    public void setupClass() {
        // Initialize test data
        testData = new jsonUtils("test-data");

        // Initialize driver
        String browserName = propertiesUtils.getPropertiesValue("browserType");
        DriverManager.createInstance(browserName);

        // Navigate to login page
        new LoginPage(DriverManager.getDriver()).navigateToLoginPage();
        InventoryPage inventoryPage = new InventoryPage(DriverManager.getDriver());
    }

    @AfterClass
    public void tearDownClass() {
        // Cleanup resources
        if (DriverManager.getDriver() != null) {
            DriverManager.quitDriver();
            LogsUtils.info("Driver is quited ");
        }
    }
    @AfterSuite
    public static void generateAndServeReport() throws Exception {
        AllureUtils.generateAndServeReport(
                "C:\\Users\\rahma\\Downloads\\allure-2.33.0\\allure-2.33.0\\bin\\allure.bat",
                "test-outputs/allure-results",
                "allure-report"
        );

    }
}