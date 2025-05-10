package org.dutesting.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.dutesting.TestComponents.BaseTest;
import org.dutesting.pageObjects.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class SubmitOrdreTest extends BaseTest {

    @Test
    @Description("Verify Ecommerce Website")
    @Owner("DUSWANT")
    public void ecommerceWebSite() throws InterruptedException, IOException {
        
        String productName = "ZARA COAT 3";

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

    }
}

