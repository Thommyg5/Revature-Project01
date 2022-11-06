package com.revature.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    @FindBy(xpath = "//*[@id=\"root\"]/fieldset/input[1]")
    public WebElement usernameInput;
    @FindBy(xpath = "//*[@id=\"root\"]/fieldset/input[2]")
    public WebElement passwordInput;
    @FindBy(xpath="//button[text()='Login']")
    public WebElement loginButton;
    @FindBy(xpath = "//*[@id=\"root\"]/h1")
    public WebElement loginTitle;

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
