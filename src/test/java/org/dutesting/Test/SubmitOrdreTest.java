package org.dutesting.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.dutesting.TestComponents.BaseTest;
import org.dutesting.pageObjects.*;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class SubmitOrdreTest extends BaseTest {

    String productName = "ZARA COAT 3";

    @Test(dataProvider = "getData", groups = {"PurchaseOrder"})
    @Description("Verify Ecommerce Website")
    @Owner("DUSWANT")
    public void ecommerceWebSite(String email, String password, String productName) throws InterruptedException, IOException {

        //Enter Username passsword click on login
        ProductCatalogue productCatalogue = landingpage.loginApplication(email, password);

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

    @Test(dependsOnMethods = "ecommerceWebSite")
    @Description("Verify ZARA COAT 3 is dispiaying in Order page")
    @Owner("DUSWANT")
    public void OrderHistoryTest(){
        //Enter Username passsword click on login
        ProductCatalogue productCatalogue = landingpage.loginApplication("okraj@gmail.com", "Okraj@123");
        //click on Order Button
        OrderPage orderPage = productCatalogue.goToOrderPage();
        Assert.assertTrue(orderPage.VerifyOrderDisplay(productName));
    }

    @DataProvider
    public Object[][] getData(){
       return new Object[][] {{"okraj@gmail.com","Okraj@123","ZARA COAT 3"},{"okraj@gmail.com","Okraj@123", "ADIDAS ORIGINAL"}};

    }
}

