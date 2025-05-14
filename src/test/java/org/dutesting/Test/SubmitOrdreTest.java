package org.dutesting.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.apache.commons.io.FileUtils;
import org.dutesting.TestComponents.BaseTest;
import org.dutesting.pageObjects.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class SubmitOrdreTest extends BaseTest {

    //String productName = "ZARA COAT 3";

    @Test(dataProvider = "getData", groups = {"PurchaseOrder"})
    @Description("Verify Ecommerce Website")
    @Owner("DUSWANT")
    public void ecommerceWebSite(HashMap<String,String> input) throws InterruptedException, IOException {

        //Enter Username passsword click on login
        ProductCatalogue productCatalogue = landingpage.loginApplication(input.get("email"),input.get("password"));

//Collect all the element of products
        List<WebElement> products = productCatalogue.getProductList();

        productCatalogue.addProductToCart(input.get("productName"));
        //Click on cart button
        CartPage cartpage = productCatalogue.goToCartPage();
        Boolean match = cartpage.VerifyProductDisplay(input.get("productName"));
        //Assert.assertTrue(match);

        //click on checkout button
        CheckoutPage checkOutPage = cartpage.goToCheckout();
        checkOutPage.selectCountry("india");
        ConfirmationPage confirmationPage = checkOutPage.submitOrder();

        String ConfirmMessage = confirmationPage.getConfirmationMessage();
        System.out.println(confirmationPage.getConfirmationMessage());
        Assert.assertTrue(ConfirmMessage.equalsIgnoreCase("Thankyou for the order."));
    }

    @Test(dependsOnMethods = "ecommerceWebSite", dataProvider = "getData")
    @Description("Verify ZARA COAT 3 is dispiaying in Order page")
    @Owner("DUSWANT")
    public void OrderHistoryTest(HashMap<String,String> input1){
        //Enter Username passsword click on login
        ProductCatalogue productCatalogue = landingpage.loginApplication(input1.get("email"),input1.get("password"));
        //click on Order Button
        OrderPage orderPage = productCatalogue.goToOrderPage();
        Assert.assertTrue(orderPage.VerifyOrderDisplay(input1.get("productName")));
    }

    public String getScreenShot(String testCaseName) throws IOException {
       TakesScreenshot ts = (TakesScreenshot)driver;
       File sourse = ts.getScreenshotAs(OutputType.FILE);
       File file = new File(System.getProperty("user.dir")+"//reports//" +testCaseName +".png");
        FileUtils.copyFile(sourse, file);
        return System.getProperty("user.dir")+"//reports//" +testCaseName +".png";
        //return file;
    }

    @DataProvider
    public Object[][] getData() throws IOException {
        //get data from Json file
        List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"/src/test/java/org/dutesting/Data/PurchaseOrder.json");
        return new Object[][] {{data.get(0)},{data.get(1)}};
        //{{Data_set_1},{Data_set_2}}

//        HashMap<String,String> map = new HashMap<String,String>();
//        map.put("email","okraj@gmail.com");
//        map.put("password","Okraj@123");
//        map.put("productName","ZARA COAT 3" );
//
//        HashMap<String,String> map1 = new HashMap<String,String>();
//        map1.put("email","okraj@gmail.com");
//        map1.put("password","Okraj@123");
//        map1.put("productName","ADIDAS ORIGINAL" );

//        @DataProvider
//        public Object[][] getData(){
//            return new Object[][] {{"okraj@gmail.com","Okraj@123","ZARA COAT 3"},{"okraj@gmail.com","Okraj@123","ADIDAS ORIGINAL"}};
//            //{{Data_set_1},{Data_set_2}}

    }
}

