package com.tutorial.qa.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    WebDriver driver;

    //Objects
    @FindBy(xpath = "//span[contains(text(),'My Account')]")
    private WebElement myAccountDropMenu;
    @FindBy(xpath = "//div[@id='top-links']//a[normalize-space()='Login']")
    private WebElement loginOption;
    @FindBy(linkText = "Register")
    private WebElement registerButton;
    @FindBy(xpath = "//input[@name='search']")
    private WebElement searchBox;
    @FindBy(xpath = "//i[@class='fa fa-search']")
    private WebElement searchButton;

    public HomePage(WebDriver driver) {

        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //Actions

    public void navigate_To_Login_Option(){

        myAccountDropMenu.click();
        loginOption.click();
    }

    public RegisterPage navigate_To_register_Option(){

        myAccountDropMenu.click();
        registerButton.click();
        return new RegisterPage(driver);
    }

    public SearchPage navigate_To_Search_Box(String text){

        searchBox.sendKeys(text);
        searchButton.click();
        return new SearchPage(driver);
    }
}
