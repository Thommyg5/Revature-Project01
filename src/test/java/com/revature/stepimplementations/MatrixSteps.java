package com.revature.stepimplementations;

import com.revature.pages.ManagerHomePage;
import com.revature.pages.MatricesPage;
import com.revature.pages.TesterHomePage;
import com.revature.runners.MatrixRunner;
import com.revature.runners.NavigationRunner;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MatrixSteps {

    WebDriverWait wait = new WebDriverWait(MatrixRunner.driver, Duration.ofSeconds(3));
    String time;
    String defectId;
    String newTestId;
    @Given("A manager is on their home page")
    public void a_manager_is_on_their_home_page() {
        MatrixRunner.driver.get("https://bugcatcher-jasdhir.coe.revaturelabs.com/?dev=21");
        MatrixRunner.loginPage.usernameInput.sendKeys("g8tor");
        MatrixRunner.loginPage.passwordInput.sendKeys("chomp!");
        MatrixRunner.loginPage.loginButton.click();
        wait.until(ExpectedConditions.urlToBe("https://bugcatcher-jasdhir.coe.revaturelabs.com/managerhome"));

    }
    @Then("A manager can pull up a form to make a new matrix")
    public void a_manager_can_pull_up_a_form_to_make_a_new_matrix() {
        //MatrixRunner.managerHomePage = new ManagerHomePage(MatrixRunner.driver);
        MatrixRunner.managerHomePage.newRequirementsMatrixButton.click();
        //this will only show once create a new matrix is clicked on
        String actualResult = MatrixRunner.managerHomePage.newMatrixTitle.getText();
        Assert.assertEquals(actualResult, "New matrix");
    }
    @When("A manager creates a title for a matrix")
    public void a_manager_creates_a_title_for_a_matrix()  {
        LocalDateTime now = LocalDateTime.now();
        time = "Matrix created on date " + now.getMonthValue() + "/" + now.getDayOfMonth() + "/" +
                now.getYear() + " at time " + now.getHour() + ":" + now.getMinute() + ":" + now.getSecond()
                + " EST";
        MatrixRunner.managerHomePage.newMatrixTitleTextBox.sendKeys(time);
    }
    @When("A manager adds requirements to a matrix")
    public void a_manager_adds_requirements_to_a_matrix()  {
        //first enter text in user story field
        MatrixRunner.managerHomePage.inputUserStory.sendKeys("Test");
        //select the priority from the select list
        int pickPriority = (int)(Math.random()*3);
        Select select = new Select(MatrixRunner.managerHomePage.prioritySelectOption);
        if (pickPriority == 0)
            select.selectByVisibleText("Low");
        else if (pickPriority == 1)
            select.selectByVisibleText("Medium");
        else if (pickPriority == 2)
            select.selectByVisibleText("High");
        //enter text into notes field
        MatrixRunner.managerHomePage.inputNote.sendKeys("This is a test");
        //add the requirement
        MatrixRunner.managerHomePage.addRequirementButton.click();
    }
    @When("A manager saves a matrix")
    public void a_manager_saves_a_matrix()  {
        wait.until(ExpectedConditions.visibilityOf(MatrixRunner.managerHomePage.createMatrixButton));
        MatrixRunner.managerHomePage.createMatrixButton.click();
        wait.until(ExpectedConditions.alertIsPresent());
        MatrixRunner.driver.switchTo().alert().accept();
    }
    @Then("The matrix should be visible for all testers and managers")
    public void the_matrix_should_be_visible_for_all_testers_and_managers()  {
        //check matrix exists for manager
        MatrixRunner.managerHomePage.matricesLink.click();
        wait.until(ExpectedConditions.visibilityOf(MatrixRunner.matricesPage.newestMatrix));
        String matrixTitle = MatrixRunner.matricesPage.newestMatrix.getText();
        matrixTitle = new String(matrixTitle.split("\n")[0]);
        Assert.assertEquals(matrixTitle, time);

        //logout and check to see matrices are there for testers
        MatrixRunner.managerHomePage.logOutLink.click();
        wait.until(ExpectedConditions.urlToBe("https://bugcatcher-jasdhir.coe.revaturelabs.com/?dev=21"));
        String[][] employees = {{"ryeGuy", "coolbeans"},{"cavalier89", "alucard"}};
        for (int i = 0; i < employees.length; i++){
            MatrixRunner.loginPage.usernameInput.sendKeys(employees[i][0]);
            MatrixRunner.loginPage.passwordInput.sendKeys(employees[i][1]);
            MatrixRunner.loginPage.loginButton.click();
            wait.until(ExpectedConditions.urlToBe("https://bugcatcher-jasdhir.coe.revaturelabs.com/testerhome"));
            MatrixRunner.topOfPage.matricesLink.click();
            wait.until(ExpectedConditions.visibilityOf(MatrixRunner.matricesPage.newestMatrix));
            matrixTitle = (MatrixRunner.matricesPage.newestMatrix.getText()).split("\n")[0];
            MatrixRunner.topOfPage.logOutLink.click();
            Assert.assertEquals(matrixTitle, time);
        }
    }
    @Given("A manager or tester has selected a matrix")
    public void a_manager_or_tester_has_selected_a_matrix()  {
        MatrixRunner.driver.get("https://bugcatcher-jasdhir.coe.revaturelabs.com/?dev=21");
        MatrixRunner.loginPage.usernameInput.sendKeys("g8tor");
        MatrixRunner.loginPage.passwordInput.sendKeys("chomp!");
        MatrixRunner.loginPage.loginButton.click();
        wait.until(ExpectedConditions.urlToBe("https://bugcatcher-jasdhir.coe.revaturelabs.com/managerhome"));
        wait.until(ExpectedConditions.visibilityOf(MatrixRunner.managerHomePage.matricesLink));
        MatrixRunner.managerHomePage.matricesLink.click();
        wait.until(ExpectedConditions.visibilityOf(MatrixRunner.matricesPage.firstShowButton));
        MatrixRunner.matricesPage.firstShowButton.click();
    }
    @When("A manager or tester adds or removes defects")
    public void a_manager_or_tester_adds_or_removes_defects() {
        wait.until(ExpectedConditions.visibilityOf(MatrixRunner.matricesPage.firstUserStoryEditButton));
        MatrixRunner.matricesPage.firstUserStoryEditButton.click();
        wait.until((ExpectedConditions.visibilityOf(MatrixRunner.matricesPage.firstDefect)));
        defectId = (MatrixRunner.matricesPage.firstDefect.getText()).split(" ")[0];
        MatrixRunner.matricesPage.removeFirstDefect.click();
        wait.until((ExpectedConditions.visibilityOf(MatrixRunner.matricesPage.addDefectButton)));
        MatrixRunner.matricesPage.addDefectTextBox.sendKeys(defectId);
        MatrixRunner.matricesPage.addDefectButton.click();
    }
    @Then("A manager or tester confirms defect is updated and saves the matrix")
    public void a_manager_or_tester_confirms_defect_is_updated_and_saves_the_matrix() {
        wait.until(ExpectedConditions.visibilityOf(MatrixRunner.matricesPage.secondDefect));
        String secondDefectText = (MatrixRunner.matricesPage.secondDefect.getText()).split(" ")[0];
        Assert.assertEquals(secondDefectText, defectId);
        MatrixRunner.matricesPage.saveChangesToMatrix.click();
    }
    @When("A manager or tester adds or removes Test Cases")
    public void a_manager_or_tester_adds_or_removes_test_cases() {
        wait.until(ExpectedConditions.visibilityOf(MatrixRunner.matricesPage.firstUserStoryEditButton));
        MatrixRunner.matricesPage.firstUserStoryEditButton.click();
        wait.until(ExpectedConditions.visibilityOf(MatrixRunner.matricesPage.removeFirstDefect));
        MatrixRunner.matricesPage.removeFirstTest.click();
        newTestId = (int)(Math.random()+800) + "";
        wait.until(ExpectedConditions.visibilityOf(MatrixRunner.matricesPage.testIdTextBox));
        MatrixRunner.matricesPage.testIdTextBox.sendKeys(newTestId);
        wait.until(ExpectedConditions.visibilityOf(MatrixRunner.matricesPage.testIdAddButton));
        MatrixRunner.matricesPage.testIdAddButton.click();

    }
    @Then("A manager or tester confirms test case is updated and saves the matrix")
    public void a_manager_or_tester_confirms_test_case_is_updated_and_saves_the_matrix() {
        wait.until(ExpectedConditions.visibilityOf(MatrixRunner.matricesPage.firstTestId));
        String firstTestId = (MatrixRunner.matricesPage.firstTestId.getText()).split(" ")[0];
        Assert.assertEquals(firstTestId,newTestId);
        MatrixRunner.matricesPage.saveChangesToMatrix.click();
    }

}
