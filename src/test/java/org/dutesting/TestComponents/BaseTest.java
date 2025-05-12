package org.dutesting.TestComponents;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.dutesting.pageObjects.landingPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
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

    public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
        //read json data to string

        String jsonContent =
                FileUtils.readFileToString(new File(filePath));

        //Convert String to HashMap Jackson Databind
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String,String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
        });
        return data;
    }

    @BeforeMethod(alwaysRun = true)
    public landingPage launchApplication() throws IOException {
        WebDriver driver = initializeDriver();
        landingpage = new landingPage(driver);
        landingpage.goTo(); // url
        return landingpage;
    }

    @AfterMethod (alwaysRun = true)
    public void tearDown(){
        driver.close();
    }
}
