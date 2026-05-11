package com.booking.stepdefinition;
import com.booking.models.AuthRequest;
import io.restassured.response.Response;
import com.booking.context.TestContext;
import com.booking.services.AuthService;
import io.cucumber.java.en.*;


public class AuthSteps {


    Response response;
    private final TestContext context = TestContext.getInstance();
    private final AuthService service = new AuthService();

    @When("I send credentials with username {string} and password {string}")
    public void loginWithParams(String username, String password) {

        AuthRequest request = new AuthRequest();
        request.setUsername(username);
        request.setPassword(password);

        response = service.generateToken(request);
    }
    @Then("response should contain {string}")
    public void response_should_contain(String expectedText) {
        response.then().body(org.hamcrest.Matchers.containsString(expectedText));
    }

    @Then("response status should be {int}")
    public void response_status_should_be(Integer statusCode) {
        response.then().statusCode(statusCode);
    }
}