package org.dutesting.AbstractComponent;

import org.dutesting.pageObjects.CartPage;
import org.dutesting.pageObjects.OrderPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AbstractComponent {
    WebDriver driver;
    public AbstractComponent(WebDriver driver) { //Constructor
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    //Click on cart button
    // driver.findElement(By.xpath("//button[@routerlink=\"/dashboard/cart\"]")).click();
    @FindBy(xpath = "//button[@routerlink=\"/dashboard/cart\"]")
    WebElement cartHeader;

    //Click on Order button
    @FindBy(xpath = "//button[@routerlink=\"/dashboard/myorders\"]")
    WebElement orderHeader;

    public CartPage goToCartPage(){
        cartHeader.click();
        CartPage cartpage = new CartPage(driver);
        return cartpage;
    }

    public OrderPage goToOrderPage(){
        waitForElementTObeClickable(orderHeader);
        orderHeader.click();
        OrderPage orderPage = new OrderPage(driver);
        return orderPage;
    }

    //Reusable code
    public void waitForElementToAppear(By findBy) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
    }

    public void waitForElementToDisappear(By findBy){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(findBy));
    }

    public void waitForElementTObeClickable (WebElement findby){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(findby));
    }

    public void waitForElementTObeVisible (WebElement findby){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(findby));
    }


}
