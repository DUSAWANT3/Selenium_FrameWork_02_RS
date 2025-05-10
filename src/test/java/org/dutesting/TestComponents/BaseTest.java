package org.dutesting.TestComponents;

import org.dutesting.pageObjects.landingPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class BaseTest {

    public WebDriver driver;
    public landingPage landingpage;

    public WebDriver initializeDriver() throws IOException {

        //Properties class
        Properties prop = new Properties();
        FileInputStream file = new FileInputStream(System.getProperty("user.dir")+"//src//main//java//org//dutesting//resources//GlobalDriver.properties");
        prop.load(file);
        String browserName = prop.getProperty("browser");

        //Chrome
        if (browserName.equalsIgnoreCase("Edge")) {
            driver = new EdgeDriver();
        } else if (browserName.equalsIgnoreCase("Firefox")) {
            driver = new FirefoxDriver();
        } else if (browserName.equalsIgnoreCase("Chrome")) {
            driver = new ChromeDriver();
        }
        driver.manage().window().maximize();
        return driver;
    }

    @BeforeMethod
    public landingPage launchApplication() throws IOException {
        WebDriver driver = initializeDriver();
        landingpage = new landingPage(driver);
        landingpage.goTo(); // url
        return landingpage;
    }

    @AfterMethod
    public void tearDown(){
        driver.close();
    }
}
