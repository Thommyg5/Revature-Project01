package com.revature.stepimplementations;

import com.revature.pages.DefectReporterPage;
import com.revature.runners.DefectRunner;
import com.revature.runners.MatrixRunner;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.pagefactory.DefaultElementLocator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class DefectSteps {

    WebDriverWait wait = new WebDriverWait(DefectRunner.driver, Duration.ofSeconds(1));
    String assignedEmployee;
    String defectId;
    int pickDefect;
    String statusChangedToThis;
    int numOfDefects;

    @Given("The manager is logged in as a manager and is on the home page")
    public void the_manager_is_logged_in_as_a_manager_and_is_on_the_home_page() {
        DefectRunner.driver.get("https://bugcatcher-jasdhir.coe.revaturelabs.com/?dev=21");
        DefectRunner.loginPage.usernameInput.sendKeys("g8tor");
        DefectRunner.loginPage.passwordInput.sendKeys("chomp!");
        DefectRunner.loginPage.loginButton.click();
        wait.until(ExpectedConditions.urlToBe("https://bugcatcher-jasdhir.coe.revaturelabs.com/managerhome"));
    }
    @Then("The manager should see pending defects")
    public void the_manager_should_see_pending_defects() {
        //need to change this
        wait.until(ExpectedConditions.visibilityOf(DefectRunner.managerHomePage.pendingDefects));
        defectId = (DefectRunner.managerHomePage.pendingDefects.getText()).split(" ")[0];
        numOfDefects = (DefectRunner.driver.findElements(By.xpath("//div/table/tbody/tr"))).size();
        Assert.assertTrue((DefectRunner.managerHomePage.pendingDefects.getText()).length()>0);
    }
    @When("The manager clicks on the select button for a defect")
    public void the_manager_clicks_on_the_select_button_for_a_defect() {
        wait.until(ExpectedConditions.visibilityOf(DefectRunner.managerHomePage.selectDefectsbutton));
        DefectRunner.managerHomePage.selectDefectsbutton.click();
    }
    @Then("The defect description should appear in bold")
    public void the_defect_description_should_appear_in_bold() {
        wait.until(ExpectedConditions.visibilityOf(DefectRunner.managerHomePage.defectDescription));
        String font = DefectRunner.managerHomePage.defectDescription.getCssValue("font-weight");
        Assert.assertEquals(font, "700"); //font is bold if it's weight is >= 700
    }
    @When("The manager selects a tester from the drop down and assigns defect")
    public void the_manager_selects_a_tester_from_the_drop_down_and_assigns_defect() {
        int testerPicker = (int)(Math.random()*2);
        if(testerPicker == 0) { //pick ryeGuy
            assignedEmployee = "ryeGuy";
        }
        else{
            assignedEmployee = "cavalier89";
        }
        wait.until(ExpectedConditions.visibilityOf(DefectRunner.managerHomePage.employeeDefectInput));
        DefectRunner.managerHomePage.employeeDefectInput.sendKeys(assignedEmployee);
        wait.until(ExpectedConditions.visibilityOf(DefectRunner.managerHomePage.assignDefectToEmployee));
        DefectRunner.managerHomePage.assignDefectToEmployee.click();
    }
    @Then("The defect should disappear from the list")
    public void the_defect_should_disappear_from_the_list() {
        wait.until(ExpectedConditions.visibilityOf(DefectRunner.managerHomePage.pendingDefects));
        int newNumOfDefects = (DefectRunner.driver.findElements(By.xpath("//div/table/tbody/tr"))).size();
        Assert.assertEquals(numOfDefects, newNumOfDefects);
    }
    @Given("The assigned tester is on their home page")
    public void the_assigned_tester_is_on_their_home_page() {
        wait.until(ExpectedConditions.visibilityOf(DefectRunner.managerHomePage.logOutLink));
        DefectRunner.managerHomePage.logOutLink.click();
        //manager logs out so tester can log in
        String username = assignedEmployee;
        String password;
        if (assignedEmployee.equals("ryeGuy")){
            password = "coolbeans";
        }
        else {
            password = "alucard";
        }
        DefectRunner.driver.get("https://bugcatcher-jasdhir.coe.revaturelabs.com/?dev=21");
        DefectRunner.loginPage.usernameInput.sendKeys(username);
        DefectRunner.loginPage.passwordInput.sendKeys(password);
        DefectRunner.loginPage.loginButton.click();
        wait.until(ExpectedConditions.urlToBe("https://bugcatcher-jasdhir.coe.revaturelabs.com/testerhome"));
    }
    @Then("The tester can can see only defects assigned to them")
    public void the_tester_can_can_see_only_defects_assigned_to_them() {
        //ArrayList<WebElement> testersDefects = (ArrayList<WebElement>)DefectRunner.driver.findElements(By.xpath("//div[1]/ul/li"));
        numOfDefects = (DefectRunner.driver.findElements(By.xpath("//div[1]/ul/li"))).size();
        int howManyRight = 0;
        for (int i = 1; i <= numOfDefects; i++) {
            String xpTester = "//div[1]/ul/li[" + i + "]/div/div/div/p[5]";
            String xpCollapsible = "//div[1]/ul/li[" + i + "]/div/span";
            WebElement clickable = wait.until(ExpectedConditions.visibilityOf(DefectRunner.driver.findElement(By.xpath(xpCollapsible))));
            clickable.click();
            wait.until(ExpectedConditions.visibilityOf(DefectRunner.driver.findElement(By.xpath(xpTester))));
            String assignedTo = ((DefectRunner.driver.findElement(By.xpath(xpTester)).getText()).split(" "))[2];
            if (assignedTo.equals(assignedEmployee)){
                howManyRight++;
            }
            wait.until(ExpectedConditions.visibilityOf(clickable));
            clickable.click();
        }
        Assert.assertEquals(howManyRight, numOfDefects);
    }

    @Then("The tester should see the pending defect")
    public void the_tester_should_see_the_pending_defect() {
        wait.until(ExpectedConditions.visibilityOf(DefectRunner.testerHomePage.newestDefect));
        String getId = (DefectRunner.testerHomePage.newestDefect.getText()).split(" ")[1];
        Assert.assertEquals(getId, defectId);
    }
    @When("The tester changes status of any defect")
    public void the_tester_changes_the_status_of_any_defect() {
        int numOfDefects = (DefectRunner.driver.findElements(By.xpath("//div[1]/ul/li"))).size();
        pickDefect = (int)((Math.random() * numOfDefects)+1);
        String xpDefect = "//div[1]/ul/li[" + pickDefect + "]/div/span";
        WebElement clickable = wait.until(ExpectedConditions.visibilityOf(DefectRunner.driver.findElement(By.xpath(xpDefect))));
        clickable.click();
        String changeStatus = "//div[1]/ul/li[" + pickDefect + "]/div/div/div/div[1]/span";
        WebElement clickChangeStatus = wait.until(ExpectedConditions.visibilityOf(DefectRunner.driver.findElement(By.xpath(changeStatus))));
        clickChangeStatus.click();
        int pickStatus = (int)((Math.random() * 5)+1);
        String clickablePickStatus = "//div[1]/ul/li[" + pickDefect + "]/div/div/div/div[1]/div/div/button[" + pickStatus + "]";
        WebElement selectStatus = wait.until(ExpectedConditions.visibilityOf(DefectRunner.driver.findElement(By.xpath(clickablePickStatus))));
        statusChangedToThis = selectStatus.getText();
        selectStatus.click();
        wait.until(ExpectedConditions.textToBePresentInElement(clickable, clickable.getText()));
    }
    @Then("The tester should see the defect has a different status")
    public void the_tester_should_see_the_defect_has_a_different_status() {
        String xpDefect = "//div[1]/ul/li[" + pickDefect + "]/div/span";
        WebElement updatedDefect = wait.until(ExpectedConditions.visibilityOf(DefectRunner.driver.findElement(By.xpath(xpDefect))));
        String[] updatedStatusDefectArray = updatedDefect.getText().split(" ");
        String updatedStatusDefect = updatedStatusDefectArray[updatedStatusDefectArray.length-2];
        Assert.assertEquals(updatedStatusDefect, statusChangedToThis);
    }
    @Given("The employee is on the Defect Reporter Page")
    public void the_employee_is_on_the_defect_reporter_page() {
        DefectRunner.driver.get("https://bugcatcher-jasdhir.coe.revaturelabs.com/?dev=21");
        DefectRunner.loginPage.usernameInput.sendKeys("g8tor");
        DefectRunner.loginPage.passwordInput.sendKeys("chomp!");
        DefectRunner.loginPage.loginButton.click();
        wait.until(ExpectedConditions.urlToBe("https://bugcatcher-jasdhir.coe.revaturelabs.com/managerhome"));
        wait.until(ExpectedConditions.visibilityOf(DefectRunner.managerHomePage.reportDefectLink));
        DefectRunner.managerHomePage.reportDefectLink.click();
    }
    @When("The employee selects todays date")
    public void the_employee_selects_todays_date() {
        /*wait.until(ExpectedConditions.visibilityOf(DefectRunner.managerHomePage.reportDefectLink));
        DefectRunner.managerHomePage.reportDefectLink.click();*/
        //DefectRunner.defectReporterPage = new DefectReporterPage(DefectRunner.driver);
        LocalDateTime now = LocalDateTime.now();
        String[] date = {now.getMonthValue()+"","0" + now.getDayOfMonth(), now.getYear() + ""};
        wait.until(ExpectedConditions.visibilityOf(DefectRunner.defectReporterPage.dateInput));
        DefectRunner.defectReporterPage.dateInput.sendKeys(date[0]);
        DefectRunner.defectReporterPage.dateInput.sendKeys(date[1]);
        DefectRunner.defectReporterPage.dateInput.sendKeys(date[2]);
    }
    @When("The employee types in {string} with")
    public void the_employee_types_in_with(String string, String docString) {
        if (string.equals("Description")) {
            wait.until(ExpectedConditions.visibilityOf(DefectRunner.defectReporterPage.descriptionTextBox));
            DefectRunner.defectReporterPage.descriptionTextBox.sendKeys(docString);
        }
        else if (string.equals("Steps")){
            wait.until(ExpectedConditions.visibilityOf(DefectRunner.defectReporterPage.howToReproduceTextBox));
            DefectRunner.defectReporterPage.howToReproduceTextBox.sendKeys(docString);
        }
    }
    @When("The employee selects high priority")
    public void the_employee_selects_high_priority() {
        //do nothing
    }
    @When("The employee selects low severity")
    public void the_employee_selects_low_severity() {
        wait.until(ExpectedConditions.visibilityOf(DefectRunner.defectReporterPage.severitySlider));
        DefectRunner.defectReporterPage.severitySlider.sendKeys(Keys.ARROW_LEFT);
        DefectRunner.defectReporterPage.severitySlider.sendKeys(Keys.ARROW_LEFT);
    }
    @When("The employee clicks the report button")
    public void the_employee_clicks_the_report_button() {
        wait.until(ExpectedConditions.visibilityOf(DefectRunner.defectReporterPage.submitDefect));
        DefectRunner.defectReporterPage.submitDefect.click();
    }
    @Then("There should be a confirmation box")
    public void there_should_be_a_confirmation_box() {
        wait.until(ExpectedConditions.alertIsPresent());
        String alertText = DefectRunner.driver.switchTo().alert().getText();
        Assert.assertTrue(alertText.length() > 0);
    }
    @When("The employee clicks Ok")
    public void the_employee_clicks_ok() {
        wait.until(ExpectedConditions.alertIsPresent());
        DefectRunner.driver.switchTo().alert().accept();
    }
    @Then("A modal should appear with a Defect ID")
    public void a_modal_should_appear_with_a_defect_id() {
        wait.until(ExpectedConditions.visibilityOf(DefectRunner.defectReporterPage.modalHeading));
        String defectId = DefectRunner.defectReporterPage.modalHeading.getText();
        Assert.assertTrue(defectId.contains("Defect"));
    }
    @When("The employee clicks close")
    public void the_employee_clicks_close() {
        wait.until(ExpectedConditions.visibilityOf(DefectRunner.defectReporterPage.closeModal));
        DefectRunner.defectReporterPage.closeModal.click();
    }
    @Then("The modal should disappear")
    public void the_modal_should_disappear() {
        wait.until(ExpectedConditions.invisibilityOf(DefectRunner.defectReporterPage.modalHeading));
        String modalText = DefectRunner.defectReporterPage.modalBody.getText();
        Assert.assertFalse(modalText.length()>0);
    }
    @Then("No confirmation dialog appears")
    public void no_confirmation_dialog_appears() {
        boolean flag = true;
        try{
            wait.until(ExpectedConditions.alertIsPresent());
        }
        catch(TimeoutException e){
            flag = false;
        }
        Assert.assertFalse(flag);
    }
}
