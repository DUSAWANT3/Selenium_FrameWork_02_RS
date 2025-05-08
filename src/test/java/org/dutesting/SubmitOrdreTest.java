package org.dutesting;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.dutesting.pageObjects.*;
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
        CartPage cartpage = productCatalogue.goToCartPage();

        Boolean match = cartpage.VerifyProductDisplay(productName);
        //Assert.assertTrue(match);

        //click on checkout button
        CheckoutPage checkOutPage = cartpage.goToCheckout();
        checkOutPage.selectCountry("india");
        ConfirmationPage confirmationPage = checkOutPage.submitOrder();

        String ConfirmMessage = confirmationPage.getConfirmationMessage();
        System.out.println(confirmationPage.getConfirmationMessage());

        Assert.assertTrue(ConfirmMessage.equalsIgnoreCase("Thankyou for the order."));

        driver.quit();
    }
}

