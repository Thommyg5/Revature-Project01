package com.revature.runners;

import com.revature.pages.*;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

@CucumberOptions(features = "classpath:features/testcases/testcases.feature",
        glue = "com.revature.stepimplementations"/*, publish = true*/)
public class Runner extends AbstractTestNGCucumberTests {

    public static WebDriver driver;
    public static LoginPage loginPage;
    public static TopOfPage topOfPage;
    public static TesterHomePage testerHomePage;
    public static TestCasesPage testCasesPage;
    public static CaseEditorPage caseEditorPage;

    @BeforeMethod //will run before every scenario
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        topOfPage = new TopOfPage(driver);
        testerHomePage = new TesterHomePage(driver);
        testCasesPage = new TestCasesPage(driver);
        caseEditorPage = new CaseEditorPage((driver));
    }

    @AfterMethod
    public void cleanup(){
        driver.quit();
    }


}
