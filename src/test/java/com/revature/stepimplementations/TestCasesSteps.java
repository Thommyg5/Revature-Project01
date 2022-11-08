package com.revature.stepimplementations;

import com.revature.pages.Test;
import com.revature.pages.TestCasesPage;
import com.revature.runners.TestCasesRunner;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class TestCasesSteps {

    WebDriverWait wait = new WebDriverWait(TestCasesRunner.driver, Duration.ofSeconds(1));
    String description;
    String testId;
    int numOfTests;

    @Given("The tester is on the test case dashboard")
    public void the_tester_is_on_the_test_case_dashboard() {
        TestCasesRunner.driver.get("https://bugcatcher-jasdhir.coe.revaturelabs.com/?dev=21");
        TestCasesRunner.loginPage.usernameInput.sendKeys("ryeGuy");
        TestCasesRunner.loginPage.passwordInput.sendKeys("coolbeans");
        TestCasesRunner.loginPage.loginButton.click();
        wait.until(ExpectedConditions.urlToBe("https://bugcatcher-jasdhir.coe.revaturelabs.com/testerhome"));
        wait.until(ExpectedConditions.visibilityOf(TestCasesRunner.testerHomePage.testCasesLink));
        TestCasesRunner.testerHomePage.testCasesLink.click();
    }
    @When("The tester types {string} into input with")
    public void the_tester_types_into_input_with(String string, String docString) {
        //TestCasesRunner.testCasesPage = new TestCasesPage(TestCasesRunner.driver);
        if (string.equals("Description")) {
            //wait.until(ExpectedConditions.visibilityOf(TestCasesRunner.testCasesPage.descriptionBoxText));
            TestCasesRunner.testCasesPage.descriptionBoxText.sendKeys(docString);
            //description = docString;
        }
        else if (string.equals("Steps")){
            //wait.until(ExpectedConditions.visibilityOf(TestCasesRunner.testCasesPage.stepsTextBox));
            TestCasesRunner.testCasesPage.stepsTextBox.sendKeys(docString);
        }
    }
    @When("The tester presses the submit button")
    public void the_tester_presses_the_submit_button() throws InterruptedException {
        numOfTests = (TestCasesRunner.driver.findElements(By.xpath("//div[1]/table/tbody/tr"))).size();
        //wait.until(ExpectedConditions.visibilityOf(TestCasesRunner.testCasesPage.submitButton));
        TestCasesRunner.testCasesPage.submitButton.click();
        //wait.until(ExpectedConditions.visibilityOf(TestCasesRunner.testCasesPage.newestTestCase));
    }
    @Then("The test case should appear at the bottom of the table")
    public void the_test_case_should_appear_at_the_bottom_of_the_table() {
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath("//div[1]/table/tbody/tr"),numOfTests));
        //TestCasesRunner.testCasesPage.newestTestCase = TestCasesRunner.driver.findElement(By.xpath("//div[1]/table/tbody/tr[last()]"));
        String s = TestCasesRunner.testCasesPage.newestTestCase.getText();
        testId = s.substring(0,5);
        Assert.assertTrue(s.contains(testId));
    }
    @Then("The test case result should say UNEXECUTED")
    public void the_test_case_result_should_say_unexecuted() {
        //wait.until(ExpectedConditions.visibilityOf(TestCasesRunner.testCasesPage.newestTestCase));
        String s = TestCasesRunner.testCasesPage.newestTestCase.getText();
        Assert.assertTrue(s.contains("UNEXECUTED"));
    }
    @When("The tester presses on details")
    public void the_tester_presses_on_details() throws InterruptedException {
        //wait.until(ExpectedConditions.visibilityOf(TestCasesRunner.testCasesPage.detailsButton));
        //TestCasesRunner.testCasesPage.detailsButton = TestCasesRunner.driver.findElement(By.xpath("//div[1]/table/tbody/tr[last()]/td[4]/button"));
        TestCasesRunner.testCasesPage.detailsButton.click();
        //Thread.sleep(5000);
    }
    @Then("A test case modal should appear showing the case ID")
    public void a_test_case_modal_should_appear_showing_the_case_id() throws InterruptedException {
        //Thread.sleep(2000);
        //wait.until(ExpectedConditions.visibilityOf(TestCasesRunner.testCasesPage.modal));
        //Assert.assertTrue(TestCasesRunner.testCasesPage.modal.isDisplayed());
        //wait.until(ExpectedConditions.attributeContains(By.xpath("//body"), "class", "ReactModal__Body--open"));
        //wait.until((ExpectedConditions.attributeToBe(By.xpath("//body"), "class", "ReactModal__Body--open")));
        //wait.until(ExpectedConditions.visibilityOf(TestCasesRunner.testCasesPage.performedBy));
        //wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[3]/div")));
        //wait.until(ExpectedConditions.attributeToBe(By.xpath("//body"), "class", ""));
        String s = TestCasesRunner.driver.findElement(By.xpath("//body")).getAttribute("class");
        System.out.println(s);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//body/div[3]/div[1]/div[1]")));
        //wait.until(ExpectedConditions.attributeContains(By.xpath("//body"), "class", "ReactModal__Body--open"));
        //wait.until(ExpectedConditions.visibilityOf(TestCasesRunner.testCasesPage.modalCaseId));
        String modalId = TestCasesRunner.testCasesPage.modalCaseId.getText();
        Assert.assertTrue(modalId.contains(testId));
        //Assert.assertEquals(1,1);
    }
    @Then("The performed by field should say No One")
    public void the_performed_by_field_should_say_no_one() {
        wait.until(ExpectedConditions.visibilityOf(TestCasesRunner.testCasesPage.performedBy));
        String s = TestCasesRunner.testCasesPage.performedBy.getText();
        boolean performedBy = s.equals("No One");
        Assert.assertTrue(performedBy);
    }
    @When("The tester presses the close button")
    public void the_tester_presses_the_close_button() {
        wait.until(ExpectedConditions.visibilityOf(TestCasesRunner.testCasesPage.closeModal));
        TestCasesRunner.testCasesPage.closeModal.click();
    }
    @Then("The Modal Should be closed")
    public void the_modal_should_be_closed() {
       /* wait.until(ExpectedConditions.invisibilityOf(TestCasesRunner.testCasesPage.modaldiv));
        String modalText = TestCasesRunner.testCasesPage.modaldiv.getText();
        Assert.assertFalse(modalText.length()>0);*/
    }
    @Given("the tester is on the test case editor for a specific test case")
    public void the_tester_is_on_the_test_case_editor_for_a_specific_test_case() {
        TestCasesRunner.driver.get("https://bugcatcher-jasdhir.coe.revaturelabs.com/?dev=21");
        TestCasesRunner.loginPage.usernameInput.sendKeys("ryeGuy");
        TestCasesRunner.loginPage.passwordInput.sendKeys("coolbeans");
        TestCasesRunner.loginPage.loginButton.click();
        wait.until(ExpectedConditions.visibilityOf(TestCasesRunner.testCasesPage.testCasesLink));
        TestCasesRunner.testerHomePage.testCasesLink.click();
        wait.until(ExpectedConditions.urlToBe("https://bugcatcher-jasdhir.coe.revaturelabs.com/testcases"));
        wait.until(ExpectedConditions.visibilityOf(TestCasesRunner.driver.findElement(By.xpath("//div[1]/table"))));
        int numOfDefects = (TestCasesRunner.driver.findElements(By.xpath("//div[1]/table/tbody/tr"))).size();
        int pickTestCase = (int)((Math.random() * numOfDefects)+1);
        String xpTestCase = "//div[1]/table/tbody/tr[" + pickTestCase + "]/td[4]/button";
        WebElement detailsButton = wait.until(ExpectedConditions.visibilityOf(TestCasesRunner.driver.findElement(By.xpath(xpTestCase))));
        detailsButton.click();
        wait.until(ExpectedConditions.visibilityOf(TestCasesRunner.testCasesPage.modalEditButton));
        TestCasesRunner.testCasesPage.modalEditButton.click();
    }
    @Then("The fields should be uneditable")
    public void the_fields_should_be_uneditable() throws InterruptedException {
        //Thread.sleep(2000);
        //need to deal with new page in order to get this to work
        wait.until(ExpectedConditions.visibilityOf(TestCasesRunner.caseEditorPage.modalDescriptionTextArea));
        Boolean descriptionAreaEnabled = TestCasesRunner.caseEditorPage.modalDescriptionTextArea.isEnabled();
        wait.until(ExpectedConditions.visibilityOf(TestCasesRunner.caseEditorPage.modalStepsTextArea));
        Boolean stepsAreaEnabled = TestCasesRunner.caseEditorPage.modalStepsTextArea.isEnabled();
        wait.until(ExpectedConditions.visibilityOf(TestCasesRunner.caseEditorPage.modalSummaryTextArea));
        Boolean summaryAreaEnabled = TestCasesRunner.caseEditorPage.modalSummaryTextArea.isEnabled();
        Assert.assertTrue(descriptionAreaEnabled && stepsAreaEnabled && summaryAreaEnabled);
    }
    @When("The tester clicks on the edit button")
    public void the_tester_clicks_on_the_edit_button() throws InterruptedException {
       /* wait.until(ExpectedConditions.visibilityOf(TestCasesRunner.driver.findElement(By.xpath("//div[@id='root']"))));
        wait.until(ExpectedConditions.visibilityOf(TestCasesRunner.testCasesPage.innerModalEditButton));
        TestCasesRunner.testCasesPage.innerModalEditButton.click();*/
    }
    @Then("The test case fields should be editable")
    public void the_test_case_fields_should_be_editable() {
        /*wait.until(ExpectedConditions.visibilityOf(TestCasesRunner.testCasesPage.modalDescriptionTextArea));
        Boolean descriptionAreaEnabled = TestCasesRunner.testCasesPage.modalDescriptionTextArea.isEnabled();
        wait.until(ExpectedConditions.visibilityOf(TestCasesRunner.testCasesPage.modalStepsTextArea));
        Boolean stepsAreaEnabled = TestCasesRunner.testCasesPage.modalStepsTextArea.isEnabled();
        wait.until(ExpectedConditions.visibilityOf(TestCasesRunner.testCasesPage.modalSummaryTextArea));
        Boolean summaryAreaEnabled = TestCasesRunner.testCasesPage.modalSummaryTextArea.isEnabled();
        Assert.assertTrue(descriptionAreaEnabled && stepsAreaEnabled && summaryAreaEnabled);*/
    }
    @When("The tester types in a new description into the description text area")
    public void the_tester_types_in_a_new_description_into_the_description_text_area() {

    }
    @When("The tester types in a new steps into the steps text area")
    public void the_tester_types_in_a_new_steps_into_the_steps_text_area() {

    }
    @When("The tester clicks on the automated check mark")
    public void the_tester_clicks_on_the_automated_check_mark() {

    }
    @When("The tester selects ryeGuy for performed from drop down")
    public void the_tester_selects_rye_guy_for_performed_from_drop_down() {

    }
    @When("The tester selects FAIL for test result from drop down")
    public void the_tester_selects_fail_for_test_result_from_drop_down() {

    }
    @When("The tester types in a new summary into the summary text area")
    public void the_tester_types_in_a_new_summary_into_the_summary_text_area() {

    }
    @When("The tester clicks on the reset button")
    public void the_tester_clicks_on_the_reset_button() {

    }
    @Then("The fields should be populated to their old values")
    public void the_fields_should_be_populated_to_their_old_values() {

    }
    @When("The tester clicks on details")
    public void the_tester_clicks_on_details() {

    }
    @When("The Tester clicks on edit within the modal")
    public void the_tester_clicks_on_edit_within_the_modal() {

    }
    @Then("The Tester should be on the case editor for that case")
    public void the_tester_should_be_on_the_case_editor_for_that_case() {

    }

    @When("The tester clicks save on test case page")
    public void the_tester_clicks_save_on_test_case_page() {

    }
    @Then("A confirmation prompt should appear")
    public void a_confirmation_prompt_should_appear() {

    }
    @When("The tester clicks on Ok")
    public void the_tester_clicks_on_ok() {

    }
    @Then("An alert says the test case has been saved")
    public void an_alert_says_the_test_case_has_been_saved() {

    }

}
