package com.tester.base;

import org.testng.ITestContext;
import org.testng.ITestResult;

import java.io.IOException;

public class ITestListener implements org.testng.ITestListener {
    @Override
    public void onTestStart(ITestResult result) {
        ExtentReport.createTest(result.getMethod().getMethodName());
    }
    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentReport.getTest().pass("Test pass");
        try{
            ExtentReport.addScreenShot(result.getName());
        } catch (Exception e) {
            System.out.println("Capture Screenshot:"+ e.getMessage());
        }

    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentReport.getTest().fail("Test fail");
//        try {
//            ExtentReport.addScreenShot(result.getName()); // Chụp ảnh khi Pass TC
//        } catch (IOException e) {
//            System.err.println("Capture screenshot: " + e.getMessage());
//        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentReport.getTest().skip("Test skip");
//        System.out.println("TC Skip: "+ result.getName());
    }

    @Override
    public void onStart(ITestContext context) {
        ExtentReport.getExtentReport();
        ExtentReport.setUpScreenShot();
    }

    @Override
    public void onFinish(ITestContext context) {
        ExtentReport.closeReport();
    }
}
