package org.dutesting.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class landingPage {

    WebDriver driver;
    public landingPage(WebDriver driver){ //Initialization firstly execute
        this.driver = this.driver;
        PageFactory.initElements(driver,this);
    }
   // WebElement userEmail = driver.findElement(By.id("userEmail"));
    //PageFactory
    @FindBy(id="userEmail")
    WebElement userEmail;

    // WebElement userPassword = driver.findElement(By.id("userPassword"))
    @FindBy(id="userPassword")
    WebElement userPassword;

    //driver.findElement(By.id("login"))
    @FindBy(id="login")
    WebElement loginButton;

    public void goTo(){
        driver.get("https://rahulshettyacademy.com/client");
    }

    public void loginApplication(String email, String password){
        userEmail.sendKeys(email);
        userPassword.sendKeys(password);
        loginButton.click();
    }


}
