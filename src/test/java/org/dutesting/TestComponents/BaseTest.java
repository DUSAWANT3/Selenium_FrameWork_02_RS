package org.dutesting.TestComponents;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.dutesting.pageObjects.landingPage;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
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
        String browserName =
                System.getProperty("browser")!= null ? System.getProperty("browser") : prop.getProperty("browser");
        //when user give command inn maven CMD "mvn test -Dbrowser= firefox" so it run in firefox browser
        //String browserName = prop.getProperty("browser");

        //Chrome
        if (browserName.equalsIgnoreCase("Edge")) {
            driver = new EdgeDriver();
        } else if (browserName.equalsIgnoreCase("Firefox")) {
            driver = new FirefoxDriver();
        } else if (browserName.contains("Chrome")) {
            ChromeOptions options = new ChromeOptions();
            if(browserName.contains("headless")) {
                options.addArguments("headless");
            }

            driver = new ChromeDriver(options);
            driver.manage().window().maximize();
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


    public String getScreenShot(String testCaseName, WebDriver driver) throws IOException {
        TakesScreenshot ts = (TakesScreenshot)driver;
        File sourse = ts.getScreenshotAs(OutputType.FILE);
        File file = new File(System.getProperty("user.dir")+"//reports//" +testCaseName +".png");
        FileUtils.copyFile(sourse, file);
        return System.getProperty("user.dir")+"//reports//" +testCaseName +".png";
        //return file;
    }
}
