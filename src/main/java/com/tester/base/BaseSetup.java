package com.tester.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;

import java.util.concurrent.TimeUnit;

public class BaseSetup {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return driver.get();
    }

    public WebDriver setupDriver(String browserType, String URL) {
        switch (browserType.trim().toLowerCase()) {
            case "chrome":
                driver.set(initChromeDriver(URL));
                break;
            default:
                System.out.println("Browser: " + browserType + " is invalid, launching Chrome...");
                driver.set(initChromeDriver(URL));
        }
        return driver.get();
    }

    private WebDriver initChromeDriver(String url) {
        System.out.println("Launching Chrome browser...");
        driver.set(new ChromeDriver());
        driver.get().manage().window().maximize();
        driver.get().get(url);
        return driver.get();
    }
    public void tearDown() {
            WebDriver webDriver = driver.get();
            if (webDriver != null) {
                webDriver.quit();
                driver.remove(); // Xóa instance khỏi ThreadLocal sau khi quit
            }
    }
}
