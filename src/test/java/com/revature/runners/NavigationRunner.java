package com.revature.runners;

import com.revature.pages.LoginPage;
import com.revature.pages.ManagerHomePage;
import com.revature.pages.TopOfPage;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@CucumberOptions(features = "classpath:features/navigation",
        glue = "com.revature.stepimplementations"/*, publish = true*/)
//can easily log in as multiple users and use one class for step implementations if
//feature files all steps are exactly the same except for
public class NavigationRunner extends AbstractTestNGCucumberTests {
    public static WebDriver driver;
    public static LoginPage loginPage;
    public static TopOfPage topOfPage;


    @BeforeMethod
    //will run before every scenario
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

