package org.dutesting.pageObjects;

import org.dutesting.AbstractComponent.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConfirmationPage extends AbstractComponent {
    WebDriver driver;

    public ConfirmationPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy (xpath = "//h1[@class=\"hero-primary\"]")
    WebElement confirmationMessage;

    By textMessage = By.xpath("//h1[@class=\"hero-primary\"]");

    public String getConfirmationMessage(){
        waitForElementToAppear(textMessage);
       return confirmationMessage.getText();
    }
}
