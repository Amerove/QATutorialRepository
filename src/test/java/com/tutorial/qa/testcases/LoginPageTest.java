package com.tutorial.qa.testcases;

import Base.Base;
import com.tutorial.qa.pageObjects.AccountPage;
import com.tutorial.qa.pageObjects.HomePage;
import com.tutorial.qa.pageObjects.LoginPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class LoginPageTest extends Base {

    public WebDriver driver;
    LoginPage loginPage;
    HomePage homePage;
    AccountPage accountPage;

    @BeforeMethod
    public void setup_browser() {

        driver = initialize_the_Web_Browser_and_Open_The_URL(prop.getProperty("browser"));
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        homePage.navigate_To_Login_Option();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }


    //Test case #1 -valid email and password
    @Test(priority = 1, dataProvider = "data1")
    public void verify_Login_With_Credentials(String email, String password) {

        accountPage = loginPage.login(email, password);
        Assert.assertTrue(accountPage.get_Display_Status_Of_Your_Account_Information_Option());

    }

    @DataProvider(name = "data1")
    public Object[][] data_Supplier() {

        Object[][] data = Utilities.get_Test_Data_From_Excel("LoginPageData");
        return data;
    }

    //Test case #2 -Invalid email and password
    @Test(priority = 2)
    public void verify_Login_With_Invalid_Credentials() {

        loginPage.login(Utilities.generateEmailWithTimeStamp(), dataprop.getProperty("invalidPassword"));
        Assert.assertTrue(loginPage.actual_Warning_Message().contains(dataprop.getProperty("invalidEmail/PasswordWarningMessage")), "Expected Warning message is not displayed");

    }

    //Test case #3 -Invalid email and valid password
    @Test(priority = 3)
    public void verify_Login_With_invalid_email_and_valid_password() {

        loginPage.login(Utilities.generateEmailWithTimeStamp(), prop.getProperty("validPassword"));
        Assert.assertTrue(loginPage.actual_Warning_Message().contains(dataprop.getProperty("invalidEmail/PasswordWarningMessage")), "Expected Warning message is not displayed");

    }

    //Test case #4 -valid email and invalid password
    @Test(priority = 4)
    public void verify_Login_with_valid_email_and_invalid_password() {

        loginPage.login(prop.getProperty("validEmail"), dataprop.getProperty("invalidPassword"));
        Assert.assertTrue(loginPage.actual_Warning_Message().contains(dataprop.getProperty("invalidEmail/PasswordWarningMessage")), "Expected Warning message is not displayed");

    }

    //Test case #5 -validate login without credentials
    @Test(priority = 5)
    public void verify_Login_Without_Providing_Credentials() {

        loginPage.click_On_Login_button();
        Assert.assertTrue(loginPage.actual_Warning_Message().contains(dataprop.getProperty("invalidEmail/PasswordWarningMessage")), "Expected Warning message is not displayed");

    }
}
