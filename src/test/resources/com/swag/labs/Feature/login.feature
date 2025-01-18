@allure.label.layer:web
@allure.label.epic:web
@login
Feature: Login

  Background:
    Given The user is on the login page

  @smoke @regress @critical
  @allure.label.jira:X-1
  Scenario Outline: Successful user login scenario
    When "<username>" and "<password>" is entered
    And I click on login button
    Then I should see logout link in burger menu
    Examples:
      | username  | password |
      | standard_user | secret_sauce |
      | problem_user  | secret_sauce |
      | performance_glitch_user | secret_sauce |
      | error_user          | secret_sauce |
      | visual_user          | secret_sauce |

  @smoke @regress
  @allure.label.jira:X-2
  Scenario: Unsuccessful user login scenario
    When "locked_out_user" and "secret_sauce" is entered
    And I click on login button
    Then I should see error "Epic sadface: Sorry, this user has been locked out."