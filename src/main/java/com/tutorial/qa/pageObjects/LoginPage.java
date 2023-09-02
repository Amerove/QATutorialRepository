package com.tutorial.qa.pageObjects;

import org.checkerframework.checker.index.qual.UpperBoundUnknown;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    WebDriver driver;
    @FindBy(id = "input-email")
    private WebElement emailAddressField;
    @FindBy(xpath = "//input[@type='password']")
    private WebElement passwordAddressField;
    @FindBy(xpath = "//input[@value='Login']")
    private WebElement loginButton;
    @FindBy(xpath = "//div[contains(@class,'alert-dismissible')]")
    private WebElement actualWarningMessage;

    public LoginPage(WebDriver driver) {

        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public AccountPage click_On_Login_button() {

        loginButton.submit();
        return new AccountPage(driver);
    }

    public AccountPage login(String email, String password){

        emailAddressField.sendKeys(email);
        passwordAddressField.sendKeys(password);
        loginButton.click();
        return new AccountPage(driver);
    }

    public String actual_Warning_Message() {

        String textWarning = actualWarningMessage.getText();
        return textWarning;
    }
}

