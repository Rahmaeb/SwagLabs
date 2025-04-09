package com.swaglabs.utils;

import org.testng.asserts.SoftAssert;

public class CustomSoftAssertion extends SoftAssert {
    // Use ThreadLocal for thread safety in parallel execution
    private static final ThreadLocal<SoftAssert> softAssert = ThreadLocal.withInitial(SoftAssert::new);

    private CustomSoftAssertion() {
        // private constructor to prevent instantiation
    }

    public static SoftAssert getInstance() {
        return softAssert.get();
    }

    public static void customSoftAssertAll() {
        try {
            SoftAssert instance = softAssert.get();
            if (instance != null) {
                instance.assertAll("custom assertion");
            }
        } catch (Exception e) {
            LogsUtils.error("Soft assertion failed " + e);
            throw e; // Re-throw to ensure test fails
        } finally {
            // Clear the assertions after reporting
            softAssert.remove();
        }
    }
}
