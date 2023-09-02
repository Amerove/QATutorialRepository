package com.tutorial.qa.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {

    WebDriver driver;

    @FindBy(linkText = "HP LP3065")
    private WebElement actualMessage;
    @FindBy(xpath = "//p[contains(text(),'There is no product')]")
    private WebElement messageWithNoProduct;

    public SearchPage(WebDriver driver) {

        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String actual_Message() {

        String actualProductMessage = actualMessage.getText();
        return actualProductMessage;
    }

    public String message_without_Product() {

        String message = messageWithNoProduct.getText();
        return message;
    }
}
