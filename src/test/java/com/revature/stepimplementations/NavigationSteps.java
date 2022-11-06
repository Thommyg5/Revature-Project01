package com.revature.stepimplementations;

import com.revature.pages.*;
import com.revature.runners.NavigationRunner;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.time.temporal.TemporalUnit;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

public class NavigationSteps {

    WebDriverWait wait = new WebDriverWait(NavigationRunner.driver, Duration.ofSeconds(2));

    @Given("The manager is logged in and is on the home page")
    public void the_manager_is_logged_in_and_is_on_the_home_page() {
        NavigationRunner.driver.get("https://bugcatcher-jasdhir.coe.revaturelabs.com/?dev=21");
        NavigationRunner.loginPage.usernameInput.sendKeys("g8tor");
        NavigationRunner.loginPage.passwordInput.sendKeys("chomp!");
        NavigationRunner.loginPage.loginButton.click();
        wait.until(ExpectedConditions.urlMatches("https://bugcatcher-jasdhir.coe.revaturelabs.com/managerhome"));
        /*wait.until(ExpectedConditions.textMatches(By.xpath("//*[@id=\"root\"]/h1"),
                Pattern.compile("Manager Home")));*/
    }
    @Then("The manager should see links for Matrices, Test Cases, Defect Reporting and Defect Overview")
    public void the_manager_should_see_links_for_matrices_test_cases_defect_reporting_and_defect_overview() {
        //NavigationRunner.topOfPage = new TopOfPage(NavigationRunner.driver);
        NavigationRunner.topOfPage = new TopOfPage(NavigationRunner.driver);
        String[] actualResult = new String[4];
        actualResult[0] = NavigationRunner.topOfPage.matricesLink.getText();
        actualResult[1] = NavigationRunner.topOfPage.testCasesLink.getText();
        actualResult[2] = NavigationRunner.topOfPage.reportDefectLink.getText();
        actualResult[3] = NavigationRunner.topOfPage.defectOverviewLink.getText();
        String[] expectedResult = {"Matrices", "Test Cases", "Report a Defect", "Defect Overview"};
        Assert.assertEquals(actualResult, expectedResult);
    }
    @When("The manager clicks on {string}")
    public void the_manager_clicks_on(String link) throws InterruptedException{
        //NavigationRunner.topOfPage = new TopOfPage(NavigationRunner.driver);
        if (link.equals("Matrices")) {
            NavigationRunner.topOfPage.matricesLink.click();
        }
        else if (link.equals("Test Cases")) {
            NavigationRunner.topOfPage.testCasesLink.click();
        }
        else if (link.equals("Report a Defect")) {
            NavigationRunner.topOfPage.reportDefectLink.click();
        }
        else if (link.equals("Defect Overview")){
            NavigationRunner.topOfPage.defectOverviewLink.click();
        }
        Thread.sleep(1000);
    }
    @Then("The title of the page should be {string}")
    public void the_title_of_the_page_should_be(String title) {
        String actualResult = NavigationRunner.topOfPage.pageTitle.getText();
        Assert.assertEquals(actualResult, title);
        wait.until(ExpectedConditions.textMatches(By.xpath("//*[@id=\"root\"]/h1"),
                Pattern.compile(NavigationRunner.topOfPage.pageTitle.getText())));

    }
    @When("The manager clicks the browser back button")
    public void the_manager_clicks_the_browser_back_button() {
        NavigationRunner.driver.navigate().back();
        wait.until(ExpectedConditions.textMatches(By.xpath("//*[@id=\"root\"]/h1"),
                Pattern.compile("Manager Home")));
    }
    @Then("The manager should be on the home page and the title of page is Home")
    public void the_manager_should_be_on_the_home_page_and_the_title_of_page_is_home() throws InterruptedException {
        Thread.sleep(1000);
        String title = NavigationRunner.topOfPage.pageTitle.getText();
        Assert.assertEquals(title, "Manager Home");
    }

}
