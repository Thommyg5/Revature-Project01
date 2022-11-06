package com.revature.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TesterHomePage extends TopOfPage {

    @FindBy(xpath = "//*[@id=\"root\"]/h3[1]")
    public WebElement myDefectsTitle;
    @FindBy(xpath = "//*[@id=\"root\"]/ul")
    public WebElement listOfDefects;
    //figure out how to work with collapsible div to work with tester defects
    //@FindBy(xpath = "//*[@id=\"root\"]/ul/li[1]")
    @FindBy(xpath = "//div[1]/ul/li[last()]/div/span/p")
    public WebElement newestDefect;

    public TesterHomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

}
