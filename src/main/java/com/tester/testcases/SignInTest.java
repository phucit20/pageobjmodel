package com.tester.testcases;

import com.aventstack.extentreports.reporter.configuration.Theme;
import com.tester.base.BaseSetup;
import com.tester.base.ExelHelper;
import com.tester.base.ITestListener;
import com.tester.pages.SignInPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

@Listeners(ITestListener.class)
public class SignInTest extends BaseSetup {
    public SignInPage signInPage;
    //    public DashBoardPage dashBoardPage;
    public ExelHelper exel;

    @BeforeClass
    public void beforeClass() throws Exception {
//        PG = new DashboardPage(driver);
        exel = new ExelHelper();
        exel.setExelFile("src/main/java/com/tester/Data/DataLogin.xlsx","sheet1");
    }
    @BeforeMethod
    public void setUp() {
        System.out.println("Setup loading....");
        System.out.println("Setup - Thread ID: " + Thread.currentThread().getId());
        setupDriver("chrome", "https://practicetestautomation.com/practice-test-login/");// Khởi tạo driver từ BaseSetup
        signInPage = new SignInPage(BaseSetup.getDriver());
    }
    @Test()
    public void signIn() throws Exception {
        System.out.println("SignIn loading....");
        String username = exel.getCell(2,1);
        String password = exel.getCell(3,1);
        signInPage.signin(username,password);
//        Thread.sleep(1000);
//        Assert.assertEquals(signInPage.getTitleLogged(),
//                "Logged In Successfully");
        System.out.println(signInPage.getTitleLogged());
    }
    @Test()
    public void signInEmptyUsername() throws Exception {
        System.out.println("SignInEmpty loading....");
        String username1 = exel.getCell(2,2);
        String password1 = exel.getCell(3,2);
        signInPage.signin(username1,password1);
//        Thread.sleep(1000);
//        Assert.assertEquals(signInPage.getErrorMessUsername(),
//                "Your username is invalid!");
        System.out.println(signInPage.getErrorMessUsername());
    }
    @AfterMethod
    public void tearDown() {
        super.tearDown();
    }
}
