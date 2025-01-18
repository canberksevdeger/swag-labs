@allure.label.layer:web
@allure.label.epic:web
@checkout
Feature: Checkout

  Background:
    Given The user is on the inventory page

  @smoke @regress @critical
  @allure.label.jira:X-5
  Scenario: Purchase 2 items and log checkout summary
    When I add products to cart with "$15.99" price
    And I continue to checkout as "Test" "User" "999"
    And I click on finish button
    Then I should see success order message
