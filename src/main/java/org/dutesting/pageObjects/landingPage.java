package org.dutesting.pageObjects;

import org.dutesting.AbstractComponent.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class landingPage extends AbstractComponent {

    WebDriver driver;
    public landingPage(WebDriver driver){
        super(driver); //Initialization firstly execute
        this.driver = driver;//child to parrent class send keywords using supper
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

    @FindBy(css="[class*=\"flyInOut\"]")
    WebElement errorMessage;

    public void goTo(){
        driver.get("https://rahulshettyacademy.com/client");
    }

    public ProductCatalogue loginApplication(String email, String password){
        userEmail.sendKeys(email);
        userPassword.sendKeys(password);
        loginButton.click();
        ProductCatalogue productCatalogue = new ProductCatalogue(driver);
        return productCatalogue;
    }

    public String getErrorMessage(){
        waitForElementTObeVisible(errorMessage);
        return errorMessage.getText();
    }

}
