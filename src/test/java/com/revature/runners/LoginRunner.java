package com.revature.runners;

import com.revature.pages.TopOfPage;
import com.revature.pages.LoginPage;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

@CucumberOptions(features = "classpath:features/login",
        glue = "com.revature.stepimplementations"/*, publish = true*/)
public class LoginRunner extends AbstractTestNGCucumberTests {

    public static WebDriver driver;
    public static LoginPage loginPage;
    public static TopOfPage homepage;


    @BeforeMethod //will run before every scenario
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
    }

    @AfterMethod
    public void cleanup(){
        driver.quit();
    }
}
