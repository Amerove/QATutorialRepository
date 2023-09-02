package com.tutorial.qa.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.security.PublicKey;

public class RegisterPage {

    WebDriver driver;

    @FindBy(id = "input-firstname")
    private WebElement firstName;
    @FindBy(id = "input-lastname")
    private WebElement lastName;
    @FindBy(id = "input-email")
    private WebElement emailInput;
    @FindBy(id = "input-telephone")
    private WebElement telephone;
    @FindBy(id = "input-password")
    private WebElement passwordInput;
    @FindBy(id = "input-confirm")
    private WebElement confirmPassword;
    @FindBy(name = "agree")
    private WebElement agreement;
    @FindBy(xpath = "//input[@class='btn btn-primary']")
    private WebElement clickContinue;
    @FindBy(xpath = "//div[@id='content']/h1")
    private WebElement expectedResult;
    @FindBy(xpath = "//label[normalize-space()='Yes']")
    private WebElement checkSmallBox;
    @FindBy(xpath = "//div[@class='alert alert-danger alert-dismissible']")
    private WebElement actualHeader;
    @FindBy(xpath = "//div[contains(@class,' alert-dismissible')]")
    private WebElement withoutFillingFieldMessage;
    @FindBy(xpath = "//input[@id='input-firstname']/following-sibling::div")
    private WebElement firstNameErrorMessage;
    @FindBy(xpath = "//input[@id='input-lastname']/following-sibling::div")
    private WebElement lastNameErrorMessage;
    @FindBy(xpath = "//input[@id='input-email']/following-sibling::div")
    private WebElement emailErrorMessage;
    @FindBy(xpath = "//input[@id='input-telephone']/following::div")
    private WebElement telephoneErrorMessage;
    @FindBy(xpath = "//input[@id='input-password']/following::div")
    private WebElement passwordErrorMessage;


    public RegisterPage(WebDriver driver) {

        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickContinue() {

        clickContinue.submit();
    }

    public String expected_Result() {

        String expectedResultText = expectedResult.getText();
        return expectedResultText;
    }

    public String actual_Result() {

        String actualHeaderText = actualHeader.getText();
        return actualHeaderText;
    }

    public String actual_Error_Message() {

        String actualMessage = withoutFillingFieldMessage.getText();
        return actualMessage;
    }

    public String first_Name_Error_Message() {

        String actualMessage1 = firstNameErrorMessage.getText();
        return actualMessage1;
    }

    public String last_Name_Error_Message() {

        String actualMessage2 = lastNameErrorMessage.getText();
        return actualMessage2;
    }

    public String email_Error_Message() {

        String actualMessage3 = emailErrorMessage.getText();
        return actualMessage3;
    }

    public String telephone_Error_Message() {

        String actualMessage4 = telephoneErrorMessage.getText();
        return actualMessage4;
    }

    public String password_Error_Message() {

        String actualMessage5 = passwordErrorMessage.getText();
        return actualMessage5;
    }

    public void verify_registeration_with_mandatory_field(String firstname, String lastname, String email, String telephon, String password, String confirmPasswor) {

        firstName.sendKeys(firstname);
        lastName.sendKeys(lastname);
        emailInput.sendKeys(email);
        telephone.sendKeys(telephon);
        passwordInput.sendKeys(password);
        confirmPassword.sendKeys(confirmPasswor);
        agreement.click();
        clickContinue.submit();
    }

    public void verify_registeration_with_all_field(String firstname, String lastname, String email, String telephon, String password, String confirmPasswor) {

        firstName.sendKeys(firstname);
        lastName.sendKeys(lastname);
        emailInput.sendKeys(email);
        telephone.sendKeys(telephon);
        passwordInput.sendKeys(password);
        confirmPassword.sendKeys(confirmPasswor);
        checkSmallBox.submit();
        agreement.click();
        clickContinue.submit();
    }

}

