package com.booking.services;

import com.booking.client.RestClient;
import com.booking.models.AuthRequest;
import io.restassured.response.Response;

public class AuthService {


    public Response generateToken(AuthRequest request) {
        return RestClient.getRequest()
                .body(request)
                .post("/auth/login");
    }

    public Response loginValid() {
        AuthRequest req = new AuthRequest("admin", "password");
        return generateToken(req);
    }
}