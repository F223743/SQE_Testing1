Feature: Login Feature

  Scenario Outline: Successful Login with valid credentials
    Given I am on the Sauce Demo login page
    When I enter username "<username>"
    And I enter password "<password>"
    And I click on the login button
Then I should be redirected to the Products page with title "Swag Labs"

    Examples:
      | username               | password     |
      | standard_user          | secret_sauce |
      | problem_user           | secret_sauce |
      | performance_glitch_user| secret_sauce |