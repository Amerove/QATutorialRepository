package Base;

import com.tutorial.qa.testcases.Utilities;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

public class Base {

    public WebDriver driver;
    public Properties prop;
    public Properties dataprop;



    public Base() {

        prop = new Properties();
        File propFile = new File(System.getProperty("user.dir") + "//src//main//java//com//tutorial//qa//testcases//utils//config.properties");

        dataprop = new Properties();
        File dataPropFile = new File(System.getProperty("user.dir") + "//src//main//java//com//tutorial//qa//testdata//dataConfig.properties");

        try {
            FileInputStream dataFis = new FileInputStream(dataPropFile);
            dataprop.load(dataFis);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        try {
            FileInputStream fis = new FileInputStream(propFile);
            prop.load(fis);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public WebDriver initialize_the_Web_Browser_and_Open_The_URL(String browser) {

        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        } else if (browser.equalsIgnoreCase("safari")) {
            WebDriverManager.safaridriver().setup();
            driver = new SafariDriver();
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICIT_WAIT_TIME));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.PAGE_LOAD_TIME));
        driver.get(prop.getProperty("url"));

        return driver;
    }
}
