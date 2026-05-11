package com.booking.services;

import com.booking.endpoints.BookingEndpoint;
import com.booking.models.Booking;
import io.restassured.response.Response;

public class BookingService {

    public Response create(Booking booking) {
        return BookingEndpoint.create(booking);
    }

    public Response get(int id) {
        return BookingEndpoint.get(id);
    }

    public Response delete(int id) {
        return BookingEndpoint.delete(id);
    }
}
