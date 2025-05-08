package org.dutesting;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.dutesting.pageObjects.ProductCatalogue;
import org.dutesting.pageObjects.CartPage;
import org.dutesting.pageObjects.landingPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

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
        ProductCatalogue productCatalogue = landingpage.loginApplication("okraj@gmail.com", "Okraj@123");

//Collect all the element of products
        List<WebElement> products = productCatalogue.getProductList();

        productCatalogue.addProductToCart(productName);
        //Click on cart button
        productCatalogue.goToCartPage();

        CartPage cartpage = new CartPage(driver);
        Boolean match = cartpage.VerifyProductDisplay(productName);
        //Assert.assertTrue(match);

        //click on checkout button
        cartpage.goToCheckout();


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

