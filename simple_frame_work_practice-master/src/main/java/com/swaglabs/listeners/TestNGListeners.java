package com.swaglabs.listeners;

import com.swaglabs.utils.*;
import org.testng.*;

import java.io.File;

import static com.swaglabs.utils.AllureUtils.AttachLogsToAllureReport;

public class TestNGListeners implements IInvokedMethodListener , ITestListener , IExecutionListener {
    File allureResultFile = new File("test-outputs/allure-results");
    File LogsFile = new File("test-outputs/Logs");
    File screenshotsFile = new File("test-outputs/screenshots");
    File allureReport = new File("allure-report");
    @Override
    public void onExecutionStart() {
        LogsUtils.info("Test Execution start ");
        propertiesUtils.loadProperties();
        FilesUtils.deleteFiles(allureResultFile);
        FilesUtils.cleanDirectory(LogsFile);
        FilesUtils.cleanDirectory(screenshotsFile);
        FilesUtils.deleteFiles(allureReport);
    }

    @Override
    public void onExecutionFinish() {
        LogsUtils.info("Test Execution Finished");
    }
    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        if (!method.isTestMethod()) {
            return;
        }
        try {
            CustomSoftAssertion.customSoftAssertAll();
            if (method.isTestMethod()){
                switch (testResult.getStatus())
                {
                    case ITestResult.SUCCESS -> ScreenShotsUtils.takeScreenShot("Passed-"+testResult.getName());
                    case ITestResult.FAILURE -> ScreenShotsUtils.takeScreenShot("Failed-"+testResult.getName());
                    case ITestResult.SKIP    -> ScreenShotsUtils.takeScreenShot("Skipped-"+testResult.getName());
                }
                AttachLogsToAllureReport();
            }
        } catch (Exception e) {
            testResult.setStatus(ITestResult.FAILURE);
            testResult.setThrowable(e);
        }
//
    }

    public void onTestFailure(ITestResult tr) {
        LogsUtils.trace("Test case "+tr.getName()+" Failed");

    }
    @Override
    public void onTestSkipped(ITestResult tr) {
        LogsUtils.trace("Test case "+tr.getName()+" Skipped");

    }
    @Override
    public void onTestSuccess(ITestResult tr) {
        LogsUtils.trace("Test case "+tr.getName()+" passed");
    }
}
