Feature: Products Page Feature

  Background:
    Given I am on the Sauce Demo login page
    When I enter username "standard_user"
    And I enter password "secret_sauce"
    And I click on the login button
    Then I should be redirected to the Products page with title "Products"

  Scenario: Verify the page title on Products page
    Given I am on the Products page
    Then the page title should be "Products"
    
  Scenario: Add a product to the shopping cart
    Given I am on the Products page
    When I add the product with name "Sauce Labs Backpack" to the cart
    Then the product should be added to the cart
    And the cart should display 1 item
  
  Scenario: Open the side menu
    Given I am on the Products page
    When I open the side menu
    Then the side menu should be visible

  Scenario: Log out from the Products page
    Given I am on the Products page
    When I log out
    Then I should be redirected to the login page
