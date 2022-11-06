package com.revature.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DefectReporterPage extends TopOfPage {

    @FindBy(xpath = "//div[1]/form/input[1]")
    public WebElement dateInput;
    @FindBy(xpath = "//div[1]/form/textarea[1]")
    public WebElement descriptionTextBox;
    @FindBy(xpath = "//div[1]/form/textarea[2]")
    public WebElement howToReproduceTextBox;
    @FindBy(xpath = "//div[1]/form/input[2]")
    public WebElement severitySlider;
    @FindBy(xpath = "//div[1]/form/input[3]")
    public WebElement prioritySlider;
    @FindBy(xpath = "//div[1]/form/button")
    public WebElement submitDefect;
    @FindBy(xpath = "//div[3]/div/div/h4")
    public WebElement modalHeading;
    @FindBy(xpath = "//div[3]/div/div/button")
    public WebElement closeModal;
    @FindBy(xpath = "//div[2]")
    public WebElement modalBody;

    public DefectReporterPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
}
