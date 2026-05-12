@api
Feature: Booking API - Negative and Edge Cases

  Background:
    Given I am authenticated as admin

  @negative
  Scenario Outline: Validate firstname length boundaries
    When I create a booking with firstname "<name>"
    Then the booking should be rejected
    And response status should be 400
    And the response should contain error message "<expected_error>"

    Examples:
      | name                      | expected_error               | description |
      | Si                        | size must be between 3 and 18| Too short   |
      | ABCDEFGHIJKLMNOPQRSTUVWXY | size must be between 3 and 18| Too long    |
      |                           | size must be between 3 and 18| Empty       |

  @notfound
  Scenario: Retrieve a non-existent booking
    When I retrieve a booking with ID 99999999
    Then response status should be 404

  @security
  Scenario: Partially update booking without authentication
    When I create a booking with valid data
    When I update the booking "firstname" to "NewName" without a token
    Then the update should be denied
    And response status should be 401

  @business
  Scenario: Partial update of a booking (PATCH)
    When I create a booking with valid data
    When I update the booking "firstname" to "Marc" and "depositpaid" to false
    Then the booking is successfully updated
    And the booking data "firstname" should be "Marc"

  @security @patch
  Scenario: Partial update (PATCH) security check
    When I create a booking with valid data
    When I attempt to update the booking "firstname" to "Hack" without authentication
    Then the update should be denied
    And response status should be 401

  @notfound
  Scenario: Operations on non-existent booking
    When I retrieve a booking with ID 999999
    Then response status should be 404

  @smoke
  Scenario: System health check
    When I check the API health status
    Then the status code should be 200