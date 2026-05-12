package com.booking.flows;

import com.booking.client.RestClient;
import com.booking.context.TestContext;
import com.booking.models.AuthRequest;
import com.booking.endpoints.Endpoints;
import io.restassured.response.Response;

public class AuthFlow {
    private final TestContext context = TestContext.getInstance();

    public void login(String username, String password) {
        AuthRequest authBody = new AuthRequest(username, password);

        Response response = RestClient.getRequest()
                .body(authBody)
                .post(Endpoints.AUTH_LOGIN);

        context.setResponse(response);

        // Si le login réussit, on extrait et stocke le token pour les futurs appels
        if (response.statusCode() == 200) {
            String token = response.jsonPath().getString("token");
            context.setToken(token);
        }
    }
}