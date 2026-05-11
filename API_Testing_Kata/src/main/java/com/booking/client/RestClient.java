package com.booking.client;

import com.booking.utils.ConfigFileReader;
import com.booking.context.TestContext;
import io.restassured.RestAssured;
import io.restassured.config.RedirectConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RestClient {

    private static final String BASE_URL =
            ConfigFileReader.getProperty("base.url");

    public static RequestSpecification getRequest() {

        RestAssuredConfig config = RestAssured.config()
                .redirect(RedirectConfig.redirectConfig()
                        .followRedirects(true)
                        .allowCircularRedirects(true));

        RequestSpecification spec = RestAssured.given()
                .config(config)
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .log().all();

        String token = TestContext.getInstance().getToken();
        if (token != null && !token.isEmpty()) {
            spec.cookie("token", token);
        }

        return spec;
    }
}