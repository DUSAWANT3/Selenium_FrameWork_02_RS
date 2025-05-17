@tag
Feature: Purchase the order from Ecommerce website
  I want to use this template for my feature file

  @ErrorValidation
  Scenario Outline: Negative test with invalid UserName and Password
    Given I landed on Ecommerce website page
    When Logged in with username <name> and password <password>
    Then "Incorrect email or password." message is displayed on screen

    Examples:
    Examples:
      |name              | password  |
      | invalid@gmail.com  | invalidpass |