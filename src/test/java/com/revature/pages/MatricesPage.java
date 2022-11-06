package com.revature.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MatricesPage extends TopOfPage {

    @FindBy(xpath = "//ul/li[1]")
    public WebElement firstMatrix;
    @FindBy(xpath = "//ul/li[1]/div/span/button")
    public WebElement firstShowButton;
    //add or remove defect
    @FindBy(xpath = "//div/ul/li[1]/div/div/div/table/tbody/tr[1]/td[6]/button")
    public WebElement firstUserStoryEditButton;
    @FindBy(xpath = "//div/ul/li[1]/div/div/div/ul[2]/li[1]")
    public WebElement firstDefect;
    @FindBy(xpath = "//div/ul/li[1]/div/div/div/ul[2]/li[1]/button")
    public WebElement removeFirstDefect;
    @FindBy(xpath = "//div/ul/li[1]/div/div/div/ul[2]/li/input[@list='defectlist']")
    public WebElement addDefectTextBox;
    @FindBy(xpath = "//div/ul/li[1]/div/div/div/ul[2]/li/button[text()='Add']")
    public WebElement addDefectButton;
    @FindBy(xpath = "//ul/li[last()]")
    public WebElement newestMatrix;
    @FindBy(xpath = "//div/ul/li[1]/div/div/div/ul[2]/li[2]")
    public WebElement secondDefect;
    @FindBy(xpath = "//div/ul/li[1]/div/div/div/button")
    public WebElement saveChangesToMatrix;

    //add or remove test
    @FindBy(xpath = "//div/ul/li[1]/div/div/div/ul[1]/li[1]")
    public WebElement firstTestId;
    @FindBy(xpath = "//div/ul/li[1]/div/div/div/ul[1]/li/button[text()='Remove']")
    public WebElement removeFirstTest;
    @FindBy(xpath = "//div/ul/li[1]/div/div/div/ul[1]/li/input[@list='testlist']")
    public WebElement testIdTextBox;
    @FindBy(xpath = "//div/ul/li[1]/div/div/div/ul[1]/li/button[text()='Add']")
    public WebElement testIdAddButton;




    public MatricesPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
}
