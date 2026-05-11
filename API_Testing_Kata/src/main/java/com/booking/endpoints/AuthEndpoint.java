package com.booking.endpoints;

import com.booking.client.RestClient;
import com.booking.endpoints.Endpoints;
import io.restassured.response.Response;

public class AuthEndpoint {

    public static Response login(Object body) {
        return RestClient.getRequest()
                .body(body)
                .post(Endpoints.AUTH_LOGIN);
    }
}