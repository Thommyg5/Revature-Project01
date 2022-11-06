package com.revature.runners;

import com.revature.pages.*;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

@CucumberOptions(features = "classpath:features/defects",
        glue = "com.revature.stepimplementations"/*, publish = true*/)
public class DefectRunner extends AbstractTestNGCucumberTests {
    public static WebDriver driver;
    public static LoginPage loginPage;
    public static ManagerHomePage managerHomePage;
    public static MatricesPage matricesPage;
    public static TopOfPage topOfPage;
    public static TesterHomePage testerHomePage;
    public static DefectReporterPage defectReporterPage;

    @BeforeMethod //will run before every scenario
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        matricesPage = new MatricesPage(driver);
        topOfPage = new TopOfPage(driver);
        managerHomePage = new ManagerHomePage(driver);
        testerHomePage = new TesterHomePage(driver);
        defectReporterPage = new DefectReporterPage(driver);
    }

    @AfterMethod
    public void cleanup(){
        driver.quit();
    }


}
