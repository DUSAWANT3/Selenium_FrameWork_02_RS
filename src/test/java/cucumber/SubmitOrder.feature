@tag
  Feature: Purchase the order from Ecommerce website
    I want to use this template for my feature file

    Background:
      Given I landed on Ecommerce website page

  @RegressionTest
   Scenario Outline: Positive test of submitting the order
    Given Logged in with username <name> and password <password>
    When  I add product <productName> to cart
    And  Checkout <productName> and submit the order
    Then "Thankyou for the order." message is displayed on conformationPage


    Examples:
    |name              | password  |productName  |
    | okraj@gmail.com  | Okraj@123 | ZARA COAT 3 |
