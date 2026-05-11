package com.booking.services;

import com.booking.endpoints.AuthEndpoint;
import com.booking.models.AuthRequest;
import io.restassured.response.Response;

public class AuthService {

    public Response generateToken(AuthRequest request) {
        return AuthEndpoint.login(request);
    }
    public Response loginValid() {
        AuthRequest req = new AuthRequest();
        req.setUsername("admin");
        req.setPassword("password123");

        return generateToken(req);
    }

    public Response loginInvalid() {
        AuthRequest req = new AuthRequest();
        req.setUsername("wrong");
        req.setPassword("wrong");

        return generateToken(req);
    }
}