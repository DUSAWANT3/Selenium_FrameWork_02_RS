package org.dutesting.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.dutesting.TestComponents.BaseTest;
import org.dutesting.pageObjects.*;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.List;

public class StepDefinitionImp extends BaseTest {

    public landingPage landingpage;
    public ProductCatalogue productCatalogue;
    public ConfirmationPage confirmationPage;

    @Given("I landed on Ecommerce website page")
    public void I_landed_on_Ecommerce_website_page() throws IOException {
        //code
        landingpage = launchApplication();
    }

    @Given("^Logged in with username (.+) and password (.+)$")
    public void logged_in_username_and_password(String username, String password) {
        productCatalogue = landingpage.loginApplication(username, password);
    }

    @When("^I add product (.+) to cart$")
    public void i_add_product_to_cart(String productName) {
        List<WebElement> products = productCatalogue.getProductList();
        productCatalogue.addProductToCart(productName);
    }

    @And("^Checkout (.+) and submit the order$")
    public void checkout_and_submit_order(String productName) {
        CartPage cartpage = productCatalogue.goToCartPage();
        Boolean match = cartpage.VerifyProductDisplay(productName);
        //Assert.assertTrue(match);
        //click on checkout button
        CheckoutPage checkOutPage = cartpage.goToCheckout();
        checkOutPage.selectCountry("india");
        confirmationPage = checkOutPage.submitOrder();
    }

    @Then("{string} message is displayed on conformationPage")
    public void message_displayed_confirmationPage(String string) {
        String ConfirmMessage = confirmationPage.getConfirmationMessage();
        Assert.assertTrue(ConfirmMessage.equalsIgnoreCase(string));
        driver.close();
    }

    @Then("{string} message is displayed on screen")
    public void messageIsDisplayedOnScreen(String string) {
        Assert.assertEquals(landingpage.getErrorMessage(), string);
        driver.close();
    }
}
