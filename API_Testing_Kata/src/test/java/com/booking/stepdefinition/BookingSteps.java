package com.booking.stepdefinition;

import com.booking.context.TestContext;
import com.booking.factory.BookingFactory;
import com.booking.models.Booking;
import com.booking.services.AuthService;
import com.booking.services.BookingService;
import io.cucumber.java.en.*;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

public class BookingSteps {

    private final TestContext context = TestContext.getInstance();
    private final BookingService bookingService = new BookingService();
    private final AuthService authService = new AuthService();


    // CREATE BOOKING
    @When("I create a booking with valid data")
    public void createValidBooking() {

        Booking booking = BookingFactory.createValidBooking();
        var response = bookingService.create(booking);

        context.setBooking(booking);
        context.setResponse(response);

        response.then()
                .statusCode(anyOf(equalTo(200), equalTo(201)))
                .body(matchesJsonSchemaInClasspath("schemas/booking-create-schema.json"));

        int id = response.jsonPath().getInt("bookingid");

        if (id <= 0) {
            throw new AssertionError("Invalid booking id");
        }

        context.setBookingId(id);
    }

    // NEGATIVE CASES

    @When("I create a booking with negative price")
    public void createNegativePrice() {

        Booking booking = BookingFactory.createWithNegativePrice();
        var response = bookingService.create(booking);

        context.setResponse(response);

        response.then()
                .statusCode(greaterThanOrEqualTo(400))
                .body(matchesJsonSchemaInClasspath("schemas/error-schema.json"));
    }

    @When("I create a booking with inverted dates")
    public void createInvalidDates() {

        Booking booking = BookingFactory.createInvertedDates();
        var response = bookingService.create(booking);

        context.setResponse(response);

        response.then()
                .statusCode(greaterThanOrEqualTo(400))
                .body(matchesJsonSchemaInClasspath("schemas/error-schema.json"));
    }

    // RETRIEVE BOOKING

    @When("I retrieve the booking")
    public void retrieveBooking() {

        var response = bookingService.get(context.getBookingId());

        context.setResponse(response);

        response.then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/booking-get-schema.json"));
    }

    // DELETE BOOKING

    @When("I delete the booking")
    public void deleteBooking() {

        var response = bookingService.delete(context.getBookingId());

        context.setResponse(response);

        response.then()
                .statusCode(anyOf(equalTo(200), equalTo(204)));
    }

    @When("I delete the booking without authentication")
    public void deleteWithoutAuth() {

        context.setToken(null);

        var response = bookingService.delete(context.getBookingId());

        context.setResponse(response);

        response.then()
                .statusCode(anyOf(equalTo(401), equalTo(403)))
                .body(matchesJsonSchemaInClasspath("schemas/error-schema.json"));
    }

    // AUTH

    @Given("I am authenticated as admin")
    public void authenticateAsAdmin() {

        var response = authService.loginValid();

        response.then()
                .statusCode(200);

        String token = response.jsonPath().getString("token");

        context.setToken(token);
    }

    // BUSINESS VALIDATIONS

    @Then("the booking data is correctly stored")
    public void validateStoredData() {

        Booking expected = context.getBooking();

        context.getResponse().then()
                .body("booking.firstname", equalTo(expected.getFirstname()))
                .body("booking.lastname", equalTo(expected.getLastname()))
                .body("booking.email", equalTo(expected.getEmail()))
                .body("booking.phone", equalTo(expected.getPhone()))
                .body("booking.roomid", equalTo(expected.getRoomid()));
    }

    @Then("I get the correct booking information")
    public void validateRetrievedBooking() {

        Booking expected = context.getBooking();

        context.getResponse().then()
                .body("firstname", equalTo(expected.getFirstname()))
                .body("lastname", equalTo(expected.getLastname()))
                .body("email", equalTo(expected.getEmail()))
                .body("phone", equalTo(expected.getPhone()));
    }

    @Then("the booking should be rejected")
    public void bookingRejected() {

        context.getResponse().then()
                .statusCode(greaterThanOrEqualTo(400))
                .body(matchesJsonSchemaInClasspath("schemas/error-schema.json"));
    }

    @Then("an error message should be returned")
    public void errorMessageReturned() {

        context.getResponse().then()
                .body(matchesJsonSchemaInClasspath("schemas/error-schema.json"))
                .body(anyOf(
                        hasKey("error"),
                        hasKey("errors")
                ));
    }

    @Then("the deletion should be denied")
    public void deletionDenied() {

        context.getResponse().then()
                .statusCode(anyOf(equalTo(401), equalTo(403)))
                .body(matchesJsonSchemaInClasspath("schemas/error-schema.json"));
    }

    @Then("the booking is successfully deleted")
    public void bookingDeleted() {

        context.getResponse().then()
                .statusCode(anyOf(equalTo(200), equalTo(204)));
    }
}