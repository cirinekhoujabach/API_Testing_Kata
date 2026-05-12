@api @booking @smoke @business @regression
Feature: Booking API - Business lifecycle

  Scenario: Create and retrieve a valid booking
    Given I create a booking with valid data
    Then the booking is successfully created
    And the booking data is correctly stored

    When I retrieve the booking
    Then I get the correct booking information


  Scenario: Booking validation rules must be enforced
    When I create a booking with negative price
    Then the booking should be rejected
    And an error message should be returned


  Scenario: Booking dates must be valid
    Given I create a booking with inverted dates
    Then the booking should be rejected
    And an error message should be returned


  Scenario: Unauthorized user cannot delete booking
    When I create a booking with valid data
    And I delete the booking without authentication
    Then the deletion should be denied


  Scenario: Authorized user can delete booking
    Given I am authenticated as admin
    And I create a booking with valid data
    When I delete the booking
    Then the booking is successfully deleted