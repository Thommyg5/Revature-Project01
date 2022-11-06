package com.revature.stepimplementations;

import com.revature.pages.TopOfPage;
import com.revature.runners.LoginRunner;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class LoginSteps {
    WebDriverWait wait = new WebDriverWait(LoginRunner.driver, Duration.ofSeconds(2));
    @Given("The employee is on the login page")
    public void the_employee_is_on_the_login_page() {
        LoginRunner.driver.get("https://bugcatcher-jasdhir.coe.revaturelabs.com/?dev=21");
    }
    @When("The employee types {string} into username input")
    public void the_employee_types_into_username_input(String username) {
        LoginRunner.loginPage.usernameInput.sendKeys(username);

    }
    @When("The employee types {string} into password input")
    public void the_employee_types_into_password_input(String password) {
        LoginRunner.loginPage.passwordInput.sendKeys(password);
        wait.until(ExpectedConditions.urlMatches("dev=21$"));
    }
    @When("The employee clicks on the login button")
    public void the_employee_clicks_on_the_login_button() {
        LoginRunner.loginPage.loginButton.click();

    }
    @Then("the employee should be on the {string} page")
    public void the_employee_should_be_on_the_page(String testerTitle) {
        wait.until(ExpectedConditions.urlMatches("https://bugcatcher-jasdhir.coe.revaturelabs.com/" +
                testerTitle.toLowerCase() + "home"));
        LoginRunner.homepage = new TopOfPage(LoginRunner.driver);
        String pageTitle = LoginRunner.homepage.pageTitle.getText();
        String[] role = pageTitle.split(" ");
        Assert.assertEquals(role[0], testerTitle);
    }
    @Then("The employee should see their name {string} {string} on the home page")
    public void the_employee_should_see_their_name_on_the_home_page(String first, String last) {
        LoginRunner.homepage = new TopOfPage(LoginRunner.driver);
        String welcomeHeading = LoginRunner.homepage.welcomeHeading.getText();
        String fullName = welcomeHeading.substring(8);
        Assert.assertEquals(fullName, first + " " + last);
    }
    @Then("The employee should see an alert saying {string}")
    public void the_employee_should_see_an_alert_saying(String alertText) {
        boolean flag = true;
        boolean isEqual = false;
        try{
            wait.until(ExpectedConditions.alertIsPresent());
            String actualResult = LoginRunner.driver.switchTo().alert().getText();
            isEqual = actualResult.equals(alertText);
        }
        catch(Exception e){
            flag = false;
        }
        /*String actualResult = LoginRunner.driver.switchTo().alert().getText();
        boolean isEqual = actualResult.equals(alertText);*/
        Assert.assertTrue(flag && isEqual);
    }



}
