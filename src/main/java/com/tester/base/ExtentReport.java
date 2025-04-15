package com.tester.base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.util.Base64;

public class ExtentReport {
//    private static Map<String, ExtentTest> mapTest = new HashMap<>();
    private static final ThreadLocal<ExtentTest> testThreadLocal= new ThreadLocal<>();
    private static ExtentReports report;
    private static ExtentTest test;
    private static ExtentSparkReporter exSpark;
    public static synchronized ExtentReports getExtentReport(){
        exSpark = new ExtentSparkReporter("src/main/java/com/tester/Log/ExtentReport.html");
        exSpark.config().setReportName("Test Automation Report");
        report = new ExtentReports();
        report.attachReporter(exSpark);
         return report;
    }
    public static synchronized ExtentTest createTest(String testName){
        test = report.createTest(testName);
        testThreadLocal.set(test);
        return test;
    }
    public static synchronized ExtentTest getTest(){
         return testThreadLocal.get();
    }
    public static synchronized void closeReport(){
        report.flush();
    }
    public static TakesScreenshot setUpScreenShot(){
        TakesScreenshot ts = (TakesScreenshot) BaseSetup.getDriver();
        File theDir = new File("com.tester/Screenshots/");
        if (!theDir.exists()) {
            theDir.mkdirs();
        }
        return ts;
    }
    public static void addScreenShot (String message) throws IOException {
        String srcFile = setUpScreenShot().getScreenshotAs(OutputType.BASE64);
        String filePath = "./Screenshots/" + message + ".png";
        byte[] decodedBytes = Base64.getDecoder().decode(srcFile);
        FileUtils.writeByteArrayToFile(new File(filePath), decodedBytes);
        ExtentTest test = testThreadLocal.get();
        if (test != null) {
            test.addScreenCaptureFromPath(filePath, "Screenshot on Failure");
        }
    }
}
