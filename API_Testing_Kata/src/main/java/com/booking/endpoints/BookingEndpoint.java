
package com.booking.endpoints;

import com.booking.models.Booking;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class BookingEndpoint {

    private static final String BASE_URL = "https://restful-booker.herokuapp.com";

    public static Response create(Booking booking) {
        return given()
                .baseUri(BASE_URL)
                .header("Content-Type", "application/json")
                .body(booking)
                .when()
                .post("/booking");
    }

    public static Response get(int id) {
        return given()
                .baseUri(BASE_URL)
                .when()
                .get("/booking/" + id);
    }

    public static Response delete(int id) {
        return given()
                .baseUri(BASE_URL)
                .when()
                .delete("/booking/" + id);
    }
}









