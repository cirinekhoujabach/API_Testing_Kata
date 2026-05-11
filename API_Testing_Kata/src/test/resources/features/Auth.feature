@api @regression @smoke
Feature: Authentication API

  @auth
  Scenario Outline: Authentication with multiple credentials
    When I send credentials with username "<user>" and password "<pass>"
    Then response status should be <status>

    Examples:
      | user  | pass        | status |
      | admin | password123 | 200    |
      | admin | wrong       | 401    |
      | wrong | password123 | 401    |