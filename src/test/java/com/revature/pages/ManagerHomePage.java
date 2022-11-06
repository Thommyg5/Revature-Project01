package com.revature.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ManagerHomePage extends TopOfPage {

    /*@FindBy(xpath = "")
    public WebElement managerName;*/ //Need to figure out how log in is done first

    //Create new requirements matrix button
    @FindBy(xpath = "//button[text()='Create A new Requirements Matrix']")
    public WebElement newRequirementsMatrixButton;
    @FindBy(xpath = "//div/h2")
    public WebElement newMatrixTitle;
    //Elements after clicking create new matrix
    @FindBy(xpath = "//input[@name='title']")
    public WebElement newMatrixTitleTextBox;
    @FindBy(xpath = "//input[@placeholder='User Story']")
    public WebElement inputUserStory;
    @FindBy(xpath = "//select")
    public WebElement prioritySelectOption;
    @FindBy(xpath = "//input[@placeholder='Note']")
    public WebElement inputNote;
    @FindBy(xpath = "//button[text()='Add Requirement']")
    public WebElement addRequirementButton;
    @FindBy(xpath = "//button[text()='Create Matrix']")
    public WebElement createMatrixButton;
    //Assign defect
    @FindBy(xpath = "//div/table/tbody")
    public WebElement pendingDefects;
    @FindBy(xpath = "//div/table/tbody/tr/td[3]/button[text()='Select']")
    public WebElement selectDefectsbutton;
    @FindBy(xpath = "//*[@id=\"root\"]/div/h4")
    public WebElement defectDescription;
    @FindBy(xpath = "//div/div/input[@list='employees']")
    public WebElement employeeDefectInput;
    @FindBy(xpath = "//*[@id=\"root\"]/div/datalist")
    public WebElement employeeDefectDataList;
    @FindBy(xpath = "//div/div/button[text()='Assign']")
    public WebElement assignDefectToEmployee;

    public ManagerHomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }


}
