package com.booking.stepdefinition;

import com.booking.context.TestContext;
import com.booking.flows.BookingFlow;
import com.booking.flows.AuthFlow;
import com.booking.validations.ResponseValidator;
import io.cucumber.java.en.*;

public class BookingSteps {
    private final BookingFlow bookingFlow = new BookingFlow();
    private final AuthFlow authFlow = new AuthFlow();
    private final TestContext context = TestContext.getInstance();

    @Given("I am authenticated as admin")
    public void stepAuth() { authFlow.login("admin", "password"); }

    @Given("I create a booking with valid data")
    public void stepCreate() { bookingFlow.createBookingWithValidData(); }

    @When("I retrieve the booking")
    public void stepGet() { bookingFlow.retrieveCurrentBookingFlow(); }

    @Then("the booking is successfully created")
    public void stepVerifyCreated() {
        ResponseValidator.validateStatus(context.getResponse(), 201);
        ResponseValidator.validateSchema(context.getResponse(), "booking-create-schema.json");
    }

    @Then("the booking data is correctly stored")
    @Then("I get the correct booking information")
    public void stepVerifyData() {
        ResponseValidator.validateBodyField(context.getResponse(), "firstname", context.getBooking().getFirstname());
    }

    @When("I delete the booking")
    public void stepDelete() { bookingFlow.deleteCurrentBooking(); }

    @Then("the booking is successfully deleted")
    public void stepVerifyDeleted() { ResponseValidator.validateStatus(context.getResponse(), 204); }

    @When("I create a booking with negative price")
    public void stepNegPrice() { bookingFlow.createBookingWithNegativePriceFlow(); }

    @Given("I create a booking with inverted dates")
    public void stepInvDates() { bookingFlow.createBookingWithInvertedDatesFlow(); }

    @Then("the booking should be rejected")
    public void stepVerifyRejected() { ResponseValidator.validateRejected(context.getResponse()); }

    @Then("an error message should be returned")
    public void stepError() { /* Optionnel: validation log */ }

    @When("I delete the booking without authentication")
    public void stepDeleteNoAuth() {
        context.setToken(null);
        bookingFlow.deleteCurrentBooking();
    }

    @Then("the deletion should be denied")
    public void stepVerifyDenied() { ResponseValidator.validateStatus(context.getResponse(), 403); }

                 //negative Scenario outline
    @When("I create a booking with firstname {string}")
    public void stepCreateCustomName(String name) {
        bookingFlow.createBookingWithCustomFirstName(name);
    }

    @Then("the response should contain error message {string}")
    public void stepVerifyErrorMsg(String expected) {
        ResponseValidator.validateMessageInList(context.getResponse(), "errors", expected);
    }
    }




