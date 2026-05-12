package com.booking.stepdefinition;

import com.booking.context.TestContext;
import com.booking.flows.AuthFlow;
import com.booking.validations.ResponseValidator;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AuthSteps {
    private final AuthFlow authFlow = new AuthFlow();
    private final TestContext context = TestContext.getInstance();

    @Given("I send credentials with username {string} and password {string}")
    public void stepLogin(String username, String password) {
        authFlow.login(username, password);
    }

    @Then("response status should be {int}")
    public void stepVerifyStatus(Integer statusCode) {
        ResponseValidator.validateStatus(context.getResponse(), statusCode);
    }

    @Then("response should contain {string}")
    public void stepVerifyContent(String expectedText) {
        ResponseValidator.validateBodyContains(context.getResponse(), expectedText);
    }
}