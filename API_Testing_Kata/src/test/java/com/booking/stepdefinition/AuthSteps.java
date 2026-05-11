package com.booking.stepdefinition;
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

        response = io.restassured.RestAssured
                .given()
                .header("Content-Type", "application/json")
                .body("{\"username\":\"" + username + "\", \"password\":\"" + password + "\"}")
                .when()
                .post("https://automationintesting.online/auth/login/");

    }

    @Then("response status should be {int}")
    public void response_status_should_be(Integer expectedStatus) {
        response.then().statusCode(expectedStatus);
    }
}