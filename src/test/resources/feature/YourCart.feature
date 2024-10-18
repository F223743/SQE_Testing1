Feature: Cart Feature

  Scenario: View cart items and proceed to checkout
    Given I am logged in to the Sauce Demo site
    When I click on the cart icon
    Then I should be redirected to the cart page with a cart item displayed
    When I click on the checkout button
    Then I should be redirected to the checkout page
