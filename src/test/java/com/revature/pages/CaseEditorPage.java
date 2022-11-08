package com.revature.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CaseEditorPage {

    @FindBy(xpath = "//div/button[text()='Edit']")
    public WebElement innerModalEditButton;
    @FindBy(xpath = "//div/fieldset[1]/textarea[1]")
    public WebElement modalDescriptionTextArea;
    @FindBy(xpath = "//div/fieldset[1]/textarea[2]")
    public WebElement modalStepsTextArea;
    @FindBy(xpath = "//div/fieldset[2]/textarea")
    public WebElement modalSummaryTextArea;

    public CaseEditorPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
