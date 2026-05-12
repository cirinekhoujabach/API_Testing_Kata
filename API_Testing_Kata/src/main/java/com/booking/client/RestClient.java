package com.booking.client;

import com.booking.utils.ConfigFileReader;
import com.booking.context.TestContext;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RestClient {

    /**
     * Configure et retourne la spécification de requête de base.
     * Centralise l'URL, le Content-Type et la gestion du Token.
     */
    public static RequestSpecification getRequest() {
        RestAssured.defaultParser = Parser.JSON;

        RequestSpecification spec = RestAssured.given()
                .baseUri(ConfigFileReader.getProperty("base.url"))
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON);

        String token = TestContext.getInstance().getToken();

        if (token != null && !token.isEmpty()) {
            spec.header("Cookie", "token=" + token);
        }

        return spec;
    }
}