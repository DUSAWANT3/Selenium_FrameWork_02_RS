package org.dutesting.pageObjects;

import org.dutesting.AbstractComponent.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends AbstractComponent {
    WebDriver driver;

    public CartPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //List<WebElement> cartProducts = driver.findElements(By.xpath("//div[@class=\"cart\"]//h3"));
    //        cartProducts.stream().filter(cartProduct -> cartProduct.getText().equals(productName));

    @FindBy(xpath = "//div[@class=\"cart\"]//h3")
    private List<WebElement> cartProducts;

   // WebElement checkOutBut = driver.findElement(By.xpath("//button[normalize-space()='Checkout'][1]"));
    @FindBy (xpath = "//button[normalize-space()='Checkout'][1]")
    WebElement checkoutEle;

    public Boolean VerifyProductDisplay(String productName) {
         Boolean match = cartProducts.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));
         return match;
    }

    public CheckoutPage goToCheckout(){
        waitForElementTObeClickable(checkoutEle);
        checkoutEle.click();
        return new CheckoutPage(driver);
    }
}