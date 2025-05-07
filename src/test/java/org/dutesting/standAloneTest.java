package org.dutesting;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.dutesting.pageObjects.landingPage;
import org.openqa.selenium.AcceptedW3CCapabilityKeys;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class standAloneTest {
    @Test
    @Description("Verify Ecommerce Website")
    @Owner("DUSWANT")
    public void ecommerceWebSite() throws InterruptedException {
        WebDriver driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.get("https://rahulshettyacademy.com/client");

        landingPage landingpage = new landingPage(driver);

        String productName = "ZARA COAT 3";

        //Enter Username passsword click on login
        driver.findElement(By.id("userEmail")).sendKeys("aaaaa@gmail.com");
        driver.findElement(By.id("userPassword")).sendKeys("********pass");
        driver.findElement(By.id("login")).click();

//Collect all the elements of the products
        Thread.sleep(3000);
        List<WebElement> products = driver.findElements(By.cssSelector(".col-md-6.mb-3"));
        WebElement prod =  products.stream().filter(product -> product.findElement(By.cssSelector("b"))
                 .getText().equals(productName))
                 .findFirst().orElse(null);
        prod.findElement(By.xpath("//div[@class=\"card-body\"]/button[2]")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
        //ng-animating
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("ng-animating")));

        //Click on the cart button
        driver.findElement(By.xpath("//button[@routerlink=\"/dashboard/cart\"]")).click();

       List<WebElement> cartProducts = driver.findElements(By.xpath("//div[@class=\"cart\"]//h3"));
      cartProducts.stream().filter(cartProduct -> cartProduct.getText().equals(productName));

      //click on the checkout button
        WebElement checkOutBut = driver.findElement(By.xpath("//button[normalize-space()='Checkout'][1]"));
        wait.until(ExpectedConditions.elementToBeClickable(checkOutBut));
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

          wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
          driver.findElement(By.xpath("(//button[@type='button'])[2]")).click();

        //Click on place Order button
       WebElement placeOrderBut = driver.findElement(By.xpath("//a[normalize-space()='Place Order']"));
       wait.until(ExpectedConditions.elementToBeClickable(placeOrderBut));
       placeOrderBut.click();

       //Captchet the Thank you massage
        WebElement thankYouMsg = driver.findElement(By.xpath("//h1[@class=\"hero-primary\"]"));
        wait.until(ExpectedConditions.visibilityOf(thankYouMsg));
        thankYouMsg.getText();

        System.out.println(thankYouMsg.getText());

       driver.quit();

    }
}
