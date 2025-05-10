package org.dutesting.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.dutesting.TestComponents.BaseTest;
import org.dutesting.pageObjects.CartPage;
import org.dutesting.pageObjects.CheckoutPage;
import org.dutesting.pageObjects.ConfirmationPage;
import org.dutesting.pageObjects.ProductCatalogue;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class ErrorValidation extends BaseTest {
    @Test
    @Description("Verify invalid lod in ID 1")
    @Owner("DUSWANT")
    public void ecommerceWebSite() throws InterruptedException, IOException {

        String productName = "ZARA COAT 3";

        //Enter Invalid Username passsword click on login
        landingpage.loginApplication("invalid@gmail.com", "invalid@123");
        Assert.assertEquals(landingpage.getErrorMessage(), "Incorrect email or password.", "Negative testing");
    }

    @Test
    @Description("Verify invalid login Id")
    @Owner("DUSWANT")
    public void ecommerceWebSite1() throws InterruptedException, IOException {

        String productName = "ZARA COAT 3";

        //Enter Invalid Username passsword click on login
        landingpage.loginApplication("invalid1@gmail.com", "inval2id@123");
        Assert.assertEquals(landingpage.getErrorMessage(), "Incorrect email or password.", "Negative testing");
    }
}