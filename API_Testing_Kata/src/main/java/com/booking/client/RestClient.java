package com.booking.client;

import com.booking.context.TestContext;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RestClient {
    public static RequestSpecification getRequest() {
        RequestSpecification spec = RestAssured.given()
                .baseUri("https://automationintesting.online/api")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON);

        String token = TestContext.getInstance().getToken();
        if (token != null && !token.isEmpty()) {
            spec.header("Cookie", "token=" + token);
        }
        return spec;
    }
}