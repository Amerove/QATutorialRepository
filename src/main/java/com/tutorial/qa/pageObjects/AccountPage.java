package com.tutorial.qa.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountPage {
    WebDriver driver;
    @FindBy(linkText = "Edit your account information")
    private WebElement editYourAccountInformationOption;

    public AccountPage(WebDriver driver) {

        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean get_Display_Status_Of_Your_Account_Information_Option() {

        boolean displayStatus = editYourAccountInformationOption.isDisplayed();
        return displayStatus;
    }


}
