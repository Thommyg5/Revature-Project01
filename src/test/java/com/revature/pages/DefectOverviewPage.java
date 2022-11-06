package com.revature.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class DefectOverviewPage extends TopOfPage {






    public DefectOverviewPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
}
