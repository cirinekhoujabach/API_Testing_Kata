@auth
Feature: Authentication

  Scenario Outline: Authentication with multiple credentials
    When I send credentials with username "<username>" and password "<password>"
    Then response status should be <status>
    And response should contain "<expected>"

    Examples:
      | username | password | status | expected |
      | admin    | password | 200    | token    |
      | admin    | wrong    | 401    | error    |
      | wrong    | password | 401    | error    |