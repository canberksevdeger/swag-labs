@allure.label.layer:web
@allure.label.epic:web
@inventory
Feature: Inventory

  Background:
    Given The user is on the inventory page

  @smoke @regress
  @allure.label.jira:X-3
  Scenario: Sorting the item prices from high to low
    When Sort products as HIGH_TO_LOW
    Then Check product sorting type for HIGH_TO_LOW

  @smoke @regress
  @allure.label.jira:X-4
  Scenario: Sorting the item prices from low to high
    When Sort products as LOW_TO_HIGH
    Then Check product sorting type for LOW_TO_HIGH