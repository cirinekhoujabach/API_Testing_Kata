package com.booking.validations;

import io.restassured.response.Response;

import static org.hamcrest.Matchers.*;

public class ResponseValidator {

    public static void status(Response response, int expected) {
        response.then().statusCode(expected);
    }

    public static void statusIn(Response response, int... codes) {
        int actual = response.statusCode();

        for (int code : codes) {
            if (actual == code) return;
        }

        throw new AssertionError("Unexpected status: " + actual);
    }

    public static void responseTime(Response response, long maxMs) {
        response.then().time(lessThan(maxMs));
    }
}