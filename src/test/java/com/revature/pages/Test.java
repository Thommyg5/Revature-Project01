package com.revature.pages;

import com.revature.runners.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;


public class Test {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        ManagerHomePage managerHomePage = new ManagerHomePage(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        MatricesPage matricesPage = new MatricesPage(driver);
        TesterHomePage testerHomePage = new TesterHomePage(driver);
        DefectReporterPage defectReporterPage = new DefectReporterPage(driver);
        TestCasesPage testCasesPage = new TestCasesPage(driver);
        driver.get("https://bugcatcher-jasdhir.coe.revaturelabs.com/?dev=21");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.usernameInput.sendKeys("ryeGuy");
        loginPage.passwordInput.sendKeys("coolbeans");
        loginPage.loginButton.click();
        wait.until(ExpectedConditions.visibilityOf(testCasesPage.testCasesLink));
        testCasesPage.testCasesLink.click();
        wait.until(ExpectedConditions.visibilityOf(testCasesPage.newestTestCase));
        String s = testCasesPage.newestTestCase.getText();
        System.out.println(s);
        wait.until(ExpectedConditions.visibilityOf(testCasesPage.detailsButton));
        testCasesPage.detailsButton.click();
        wait.until(ExpectedConditions.attributeContains(By.xpath("//body"), "class", "ReactModal__Body--open"));
        s = driver.findElement(By.xpath("//body")).getAttribute("class");
        System.out.println(s);
        wait.until(ExpectedConditions.visibilityOf(testCasesPage.modalCaseId));
        String modalId = testCasesPage.modalCaseId.getText();
        System.out.println(modalId);
        wait.until(ExpectedConditions.visibilityOf(testCasesPage.performedBy));
        boolean performedBy = testCasesPage.performedBy.getText().equals("No one");
        wait.until(ExpectedConditions.visibilityOf(testCasesPage.closeModal));
        testCasesPage.closeModal.click();
        wait.until(ExpectedConditions.invisibilityOf(testCasesPage.modaldiv));
        String modalText = testCasesPage.modaldiv.getText();
        System.out.println(modalText);


        driver.quit();


    }
}
