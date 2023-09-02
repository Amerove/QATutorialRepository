package com.tutorial.qa.testcases;

import Base.Base;
import com.tutorial.qa.pageObjects.HomePage;
import com.tutorial.qa.pageObjects.SearchPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

//update by co-worker
public class SearchTest extends Base {

    public WebDriver driver;
    SearchPage search;
    HomePage homePage;

    public SearchTest(){
        super();
    }

    @BeforeMethod
    public void setup_Browser() {

        driver = initialize_the_Web_Browser_and_Open_The_URL(prop.getProperty("browser"));
        homePage = new HomePage(driver);
    }


    @AfterMethod
    public void tearDown() {
        driver.quit();
    }


    @Test(priority = 1)
    public void verify_Search_with_Valid_Product() {

        search = homePage.navigate_To_Search_Box(dataprop.getProperty("ValidProduct"));
        Assert.assertTrue(search.actual_Message().contains(dataprop.getProperty("ExpectedProduct")), "Test successes");

    }

    @Test(priority = 2)
    public void verify_Search_With_Invalid_Product() {

        search = homePage.navigate_To_Search_Box(dataprop.getProperty("invalidProduct"));
        String actualResult = search.message_without_Product();
        Assert.assertEquals(actualResult,"asde", "Test successes");

    }

    @Test(priority = 3, dependsOnMethods = {"verify_Search_with_Valid_Product", "verify_Search_With_Invalid_Product"})
    public void verify_Search_Without_Product() {

        search = homePage.navigate_To_Search_Box("");
        String actualResult = search.message_without_Product();
        Assert.assertEquals(actualResult, dataprop.getProperty("ExpectedMessage"), "Test successes");
    }
}
