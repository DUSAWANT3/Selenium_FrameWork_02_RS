package org.dutesting.pageObjects;

import org.dutesting.AbstractComponent.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductCatalogue extends AbstractComponent {
    WebDriver driver;

    public ProductCatalogue(WebDriver driver) {//Initialization firstly execute
        super(driver); //child to parrent class send keywords using supper
        this.driver = this.driver;
        PageFactory.initElements(driver, this);
    }

    // List<WebElement> products = driver.findElements(By.cssSelector(".col-md-6.mb-3"));
    //PageFactory
    @FindBy(css = ".col-md-6.mb-3")
    List<WebElement> products;


    By productsBy = By.cssSelector(".col-md-6.mb-3");
    By addToCart = By.xpath("//div[@class=\"card-body\"]/button[2]");
    By toastMessage = By.id("toast-container");
    By spinner =By.className("ng-animating");

    public List<WebElement> getProductList() {
        waitForElementToAppear(productsBy);
        return products;
    }

    public WebElement getProductByName(String productName) {
        WebElement prod = getProductList().stream().filter(product -> product.findElement(By.cssSelector("b"))
                        .getText().equals(productName))
                .findFirst().orElse(null);
        return prod;
    }

    public void addProductToCart(String productName){
        WebElement prod = getProductByName(productName);
                prod.findElement(addToCart).click();
                waitForElementToAppear(toastMessage);
                waitForElementToDisappear(spinner);
    }
}
