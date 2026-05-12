package com.booking.endpoints;

import com.booking.client.RestClient;
import com.booking.models.Booking;
import io.restassured.response.Response;

public class BookingEndpoint {

    public static Response create(Object body) {
        return RestClient.getRequest()
                .body(body)
                .post("/booking");
    }


    public static Response get(int id) {
        return RestClient.getRequest().pathParam("id", id).get(Endpoints.BOOKING_BY_ID);
    }

    public static Response delete(int id) {
        return RestClient.getRequest().pathParam("id", id).delete(Endpoints.BOOKING_BY_ID);


    }
}