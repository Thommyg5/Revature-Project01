package com.revature.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TestCasesPage extends TopOfPage {

    @FindBy(xpath = "//textarea[@name='desc']")
    public WebElement descriptionBoxText;
    @FindBy(xpath = "//textarea[@name='steps']")
    public WebElement stepsTextBox;
    @FindBy(xpath = "//button[@type='submit']")
    public WebElement submitButton;
    @FindBy(xpath = "//div[1]/table/tbody/tr[last()]")
    public WebElement newestTestCase;
    @FindBy(xpath = "(//button[contains(text(),'Details')])[last()]")
    public WebElement detailsButton;
    @FindBy(xpath = "//div[3]/div/div")
    public WebElement modal;
    @FindBy(xpath = "//div[@class='ReactModalPortal']//h3")
    public WebElement modalCaseId;
    @FindBy(xpath = "//div[3]/div/div/p[6]")
    public WebElement performedBy;
    @FindBy(xpath = "//div[3]/div/div/button[1]")
    public WebElement closeModal;
    @FindBy(xpath = "//div[2]")
    public WebElement modaldiv;
    @FindBy(xpath = "//div[3]/div/div/button[2]")
    public WebElement modalEditButton;
    /*@FindBy(xpath = "//div/button[text()='Edit']")
    public WebElement innerModalEditButton;
    @FindBy(xpath = "//div/fieldset[1]/textarea[1]")
    public WebElement modalDescriptionTextArea;
    @FindBy(xpath = "//div/fieldset[1]/textarea[2]")
    public WebElement modalStepsTextArea;
    @FindBy(xpath = "//div/fieldset[2]/textarea")
    public WebElement modalSummaryTextArea;*/



    public TestCasesPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
}
