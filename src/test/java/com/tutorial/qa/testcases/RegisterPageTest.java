package com.tutorial.qa.testcases;

import Base.Base;
import com.tutorial.qa.pageObjects.HomePage;
import com.tutorial.qa.pageObjects.RegisterPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class RegisterPageTest extends Base {

    public WebDriver driver;
    public RegisterPage registerPage;

    @BeforeMethod
    public void webDriver_Setup() {

        driver = initialize_the_Web_Browser_and_Open_The_URL(prop.getProperty("browser"));
        HomePage homePage = new HomePage(driver);
        registerPage = homePage.navigate_To_register_Option();
    }

    @AfterMethod
    public void tearDown_Browser() {driver.quit();}


    @Test(priority = 1)
    public void verify_Registering_An_Account_With_Mandatory_Field() {

        registerPage.verify_registeration_with_mandatory_field(dataprop.getProperty("Firstname"),dataprop.getProperty("Lastname"),
                Utilities.generateEmailWithTimeStamp(), dataprop.getProperty("Telephone"), dataprop.getProperty("Password"),
                dataprop.getProperty("Password"));
        Assert.assertTrue(dataprop.getProperty("AccountMessage").equals(registerPage.expected_Result()));

    }

    @Test(priority = 2)
    public void verify_Registering_An_Account_With_All_Field() {

        registerPage.verify_registeration_with_all_field(dataprop.getProperty("Firstname"), dataprop.getProperty("Lastname"),
                Utilities.generateEmailWithTimeStamp(), dataprop.getProperty("Telephone"), dataprop.getProperty("Password"), dataprop.getProperty("Password"));
        Assert.assertTrue(dataprop.getProperty("AccountMessage").equals(registerPage.expected_Result()));

    }

    @Test(priority = 3)
    public void verify_Registering_An_Account_With_Existing_Email() {

        registerPage.verify_registeration_with_all_field(dataprop.getProperty("Firstname"), dataprop.getProperty("Lastname"),
                prop.getProperty("validEmail"), dataprop.getProperty("Telephone"), dataprop.getProperty("Password"), dataprop.getProperty("Password"));
        Assert.assertTrue(dataprop.getProperty("ExistingEmailMessage").equals(registerPage.actual_Result()));

    }

    @Test(priority = 4)
    public void verify_Registering_An_Account_Without_filling_Any_Field() {

        registerPage.clickContinue();
        Assert.assertTrue(registerPage.actual_Error_Message().contains(dataprop.getProperty("AgreementMassage")), "The email you have entered is already being used.");
        //first name checking
        Assert.assertEquals(registerPage.first_Name_Error_Message(), dataprop.getProperty("FirstNameMessage"), "validating the first name entries");
        //last name checking
        Assert.assertEquals(registerPage.last_Name_Error_Message(), dataprop.getProperty("LastNameMessage"), "Validating the last name entries");
        //validating the email entries
        Assert.assertTrue(registerPage.email_Error_Message().contains(dataprop.getProperty("EmailMessage")), "Email address wan not provided");
        //validating the phone number
        Assert.assertEquals(registerPage.telephone_Error_Message(), dataprop.getProperty("PhoneMessage"), "No phone number was provided");
        //password validation
        Assert.assertTrue(registerPage.password_Error_Message().contains(dataprop.getProperty("PasswordMessage")), "No password was provided");

    }
}
