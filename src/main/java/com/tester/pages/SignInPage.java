package com.tester.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class SignInPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By headerPageText = By.xpath("//a[normalize-space()='Forgot Username/Password?']");
    private By emailInput = By.xpath("//*[@id=\"username\"]");
    private By passwordInput = By.xpath("//*[@id=\"password\"]");
    private By signinBtn = By.xpath("//*[@id=\"submit\"]");
    private By errorMsgText = By.xpath("//*[@id=\"error\"]");
    private By titleLoginTrue = By.xpath("//*[@id=\"loop-container\"]/div/article/div[1]/h1");
    private By pinInput = By.id("Pin");
    private By submitBtn = By.id("//*[@id=\"submit\"]");
    private By backBtn = By.id("RequestPinForm_Back");
    private By resetPintBtn = By.id("RequestPinForm_ResetPin");

    // Khởi tạo class khi được gọi và truyền driver vào để các thành phần trong
    // class này đọc
    public SignInPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    //    public void setupBrowser(){
//            this.driver = new BaseSetup().setupDriver("chorme","https:www.facebook.com");
//    }
    public String getSignInPageTitle() {
        String pageTitle = driver.getTitle();
        System.out.println(driver);
        return pageTitle;
    }

    public boolean verifySignInPageTitle() {
        String expectedTitle = "Sign In";
        return getSignInPageTitle().equals(expectedTitle);
    }

    public boolean verifySignInPageText() {
        WebElement element = driver.findElement(headerPageText);
        String pageText = element.getText();
        String expectedPageText = "Forgot Username/Password?";
        return pageText.contains(expectedPageText);
    }

    // Sau khi thực hiện click Submit thì khởi tạo trang DashboardPage
    public void signin(String username, String password) throws Exception {
        enterEmail(username);
        enterPassword(password);
        clickSignIn();
        Thread.sleep(1000);
    }
//    public boolean verifySignIn() {
//        enterEmail("test");
//        enterPassword("pass");
//        clickSignIn();
//        return getErrorMessage().contains("incorrect");
//    }

    public void enterEmail(String email) {
        WebElement emailTxtBox = driver.findElement(emailInput);
        if (emailTxtBox.isDisplayed()){
            emailTxtBox.clear();
            emailTxtBox.sendKeys(email);
        }
    }

    public void enterPassword(String password) {
        WebElement passwordTxtBox = driver.findElement(passwordInput);
        if (passwordTxtBox.isDisplayed()){
            passwordTxtBox.clear();
            passwordTxtBox.sendKeys(password);
        }
    }

    public void clickSignIn() {
        WebElement signin = driver.findElement(signinBtn);
        if (signin.isDisplayed()) {
            signin.click();
        }
    }

    public void clickSubmit() {
        WebElement submit = driver.findElement(submitBtn);
        if (submit.isDisplayed()) {
            submit.click();
        }
    }

    public void clickBack() {
        driver.findElement(backBtn).click();
    }

    public void clickResetPin() {
        driver.findElement(resetPintBtn).click();
    }

    public String getErrorMessUsername() {
//        WebElement element = driver.findElement(errorMsgText);
//        boolean errorMsg = element.isDisplayed();
//        if(errorMsg){
//            return element.getText();
//        }
//        String s = "";
//        return s;
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMsgText));
            return element.getText();
        } catch (Exception e) {
            return "";
        }
    }
    public String getTitleLogged(){
//        WebElement element = driver.findElement(titleLoginTrue);
//        boolean titleLogin = element.isDisplayed();
//        if (titleLogin){
//            return element.getText();
//        }
//        String s ="";
//        return s;
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(titleLoginTrue));
            return element.getText();
        } catch (Exception e) {
            return "";
        }
    }

    public void waitForPageLoaded() {
        ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString()
                        .equals("complete");
            }
        };
        try {
            Thread.sleep(1000);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            wait.until(expectation);
        } catch (Throwable error) {
            Assert.fail("Timeout waiting for Page Load Request to complete.");
        }
    }
}
