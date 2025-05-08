package org.dutesting.pageObjects;

import org.dutesting.AbstractComponent.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage extends AbstractComponent {

    WebDriver driver;
    public CheckoutPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(xpath = "//a[normalize-space()='Place Order']")
            WebElement submit;

    @FindBy(xpath = "//input[@placeholder='Select Country']")
    WebElement country;

    @FindBy(xpath = "(//button[@type='button'])[2]")
         WebElement selectCountry;

    By result = By.cssSelector(".ta-results");

    //        a.sendKeys(driver.findElement(By.xpath("//input[@placeholder='Select Country']")), "India").build().perform();
    public void selectCountry(String countryName){
        Actions a = new Actions(driver);
        a.sendKeys(country, countryName).build().perform();
        waitForElementToAppear(result);
        selectCountry.click();
    }

    public ConfirmationPage submitOrder(){
        submit.click();
        return new ConfirmationPage(driver);
    }
}
