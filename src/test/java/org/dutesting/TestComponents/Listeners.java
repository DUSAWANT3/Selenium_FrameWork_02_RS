package org.dutesting.TestComponents;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.dutesting.resources.ExtentReporterNG;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class Listeners extends BaseTest implements ITestListener {

    ExtentReports extent = ExtentReporterNG.getReportObject();
    ExtentTest test;
    ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();

    @Override
    public void onTestStart(ITestResult result) {
        ITestListener.super.onTestStart(result);
        test = extent.createTest(result.getMethod().getMethodName());
        extentTest.set(test);//unique thread id(ErrorValidation test
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ITestListener.super.onTestSuccess(result);
        test.log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ITestListener.super.onTestFailure(result);
        test.log(Status.FAIL,"Test Failed");
        //test.fail(result.getThrowable());//Gives Error msg as well
        extentTest.get().fail(result.getThrowable());

        //Screenshot only take when TC fail
        //Take Screenshot --> attach to the Report
        String filePath = null;   //comes from Base test
        try {
            driver = (WebDriver) result.getTestClass().getRealClass().getField("driver")
                    .get(result.getInstance());

            filePath = getScreenShot(result.getMethod().getMethodName(),driver);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        test.addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ITestListener.super.onTestSkipped(result);
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        ITestListener.super.onTestFailedWithTimeout(result);
    }

    @Override
    public void onStart(ITestContext context) {
        ITestListener.super.onStart(context);
    }

    @Override
    public void onFinish(ITestContext context) {
        ITestListener.super.onFinish(context);
        extent.flush();
    }

    @Override
    public boolean isEnabled() {
        return ITestListener.super.isEnabled();
    }
}
