@auth
Feature: Authentication API

  Scenario Outline: Authentication with multiple credentials
    When I send credentials with username "<username>" and password "<password>"
    Then response status should be 200
    And response should contain "<message>"

    Examples:
      | username | password     | message          |
      | admin    | password123  | token            |
      | admin    | wrong        | Bad credentials  |
      | wrong    | password123  | Bad credentials  |