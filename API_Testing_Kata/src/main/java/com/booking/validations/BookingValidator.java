package com.booking.validations;

import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;

public class BookingValidator {

    public static void validateCoreFields(Response response) {
        response.then()
                .body("firstname", notNullValue())
                .body("lastname", notNullValue())
                .body("totalprice", greaterThan(0))
                .body("bookingdates.checkin", notNullValue())
                .body("bookingdates.checkout", notNullValue());
    }

    public static void validateConsistency(Response response, String first, String last) {
        response.then()
                .body("firstname", equalTo(first))
                .body("lastname", equalTo(last));
    }
}