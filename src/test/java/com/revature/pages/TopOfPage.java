package com.revature.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TopOfPage {

    //title
    @FindBy(xpath="//*[@id=\"root\"]/h1")
    public WebElement pageTitle;
    @FindBy(xpath = "//*[@id=\"root\"]/nav/p")
    public WebElement welcomeHeading;
    //links at top of page
    @FindBy(xpath = "//div[1]/nav/a[1]")
    public WebElement matricesLink;
    @FindBy(xpath = "//div[1]/nav/a[2]")
    public WebElement testCasesLink;
    @FindBy(xpath = "//div[1]/nav/a[3]")
    public WebElement reportDefectLink;
    @FindBy(xpath = "//div[1]/nav/a[4]")
    public WebElement defectOverviewLink;
    @FindBy(xpath = "//div[1]/nav/a[5]")
    public WebElement logOutLink;
    @FindBy(xpath = "//div[1]/nav/a[6]")
    public WebElement homeLink;

    public TopOfPage(WebDriver driver) {PageFactory.initElements(driver, this);}
}
