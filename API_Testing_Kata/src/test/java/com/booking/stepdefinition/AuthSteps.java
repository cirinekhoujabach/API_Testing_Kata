package com.booking.stepdefinition;

import com.booking.context.TestContext;
import com.booking.models.AuthRequest;
import com.booking.services.AuthService;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.containsString;

public class AuthSteps {

    private final TestContext context = TestContext.getInstance();
    private final AuthService service = new AuthService();

    @When("I send credentials with username {string} and password {string}")
    public void loginWithParams(String username, String password) {
        AuthRequest request = new AuthRequest();
        request.setUsername(username);
        request.setPassword(password);
        Response response = service.generateToken(request);
        context.setResponse(response);
    }

    @Then("response status should be {int}")
    public void response_status_should_be(Integer statusCode) {
        context.getResponse().then().statusCode(statusCode);
    }

    @Then("response should contain {string}")
    public void response_should_contain(String expectedText) {
        context.getResponse().then().body(containsString(expectedText));
    }
}