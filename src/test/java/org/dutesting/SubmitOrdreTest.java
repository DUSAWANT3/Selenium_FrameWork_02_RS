package org.dutesting;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.dutesting.pageObjects.ProductCatalogue;
import org.dutesting.pageObjects.landingPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class SubmitOrdreTest {
    @Test
    @Description("Verify Ecommerce Website")
    @Owner("DUSWANT")
    public void ecommerceWebSite() throws InterruptedException {
        WebDriver driver = new EdgeDriver();
        driver.manage().window().maximize();
        String productName = "ZARA COAT 3";

        landingPage landingpage = new landingPage(driver);
         landingpage.goTo(); // url

        //Enter Username passsword click on login
        landingpage.loginApplication("okraj@gmail.com", "Okraj@123");

//Collect all the element of products
        ProductCatalogue productCatalogue = new ProductCatalogue(driver);
        List<WebElement> products = productCatalogue.getProductList();

        productCatalogue.addProductToCart(productName);

        //Click on cart button
        driver.findElement(By.xpath("//button[@routerlink=\"/dashboard/cart\"]")).click();

        List<WebElement> cartProducts = driver.findElements(By.xpath("//div[@class=\"cart\"]//h3"));
        cartProducts.stream().filter(cartProduct -> cartProduct.getText().equals(productName));

        //click on checkout button
        WebElement checkOutBut = driver.findElement(By.xpath("//button[normalize-space()='Checkout'][1]"));
       // wait.until(ExpectedConditions.elementToBeClickable(checkOutBut));
        checkOutBut.click();

        //in autosuggestion dropdown select India
//        driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("India");
//
//        List<WebElement> options = driver.findElements(By.xpath("//button[@type='button']"));
//        options.stream().filter(option -> option.getText().equalsIgnoreCase("India"))
//                .findFirst()
//                .ifPresent(WebElement::click);

        Actions a = new Actions(driver);
        a.sendKeys(driver.findElement(By.xpath("//input[@placeholder='Select Country']")), "India").build().perform();

        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
        driver.findElement(By.xpath("(//button[@type='button'])[2]")).click();

        //Click on place Order button
        WebElement placeOrderBut = driver.findElement(By.xpath("//a[normalize-space()='Place Order']"));
        //wait.until(ExpectedConditions.elementToBeClickable(placeOrderBut));
        placeOrderBut.click();

        //Captchet the Thank you massage
        WebElement thankYouMsg = driver.findElement(By.xpath("//h1[@class=\"hero-primary\"]"));
        //wait.until(ExpectedConditions.visibilityOf(thankYouMsg));
        thankYouMsg.getText();

        System.out.println(thankYouMsg.getText());

        driver.quit();
    }
}

