package com.booking.flows;

import com.booking.context.TestContext;
import com.booking.factory.BookingFactory;
import com.booking.models.Booking;
import com.booking.services.BookingService;
import io.restassured.response.Response;

public class BookingFlow {

    private final BookingService service = new BookingService();

    public void createBooking(TestContext context) {

        // 1 Payload
        Booking booking = BookingFactory.createValidBooking();

        // 2 API CALL
        Response response = service.create(booking);

        // 3 Save response
        context.setResponse(response);
        context.setBooking(booking);

        // 4 Extract ID
        int id = response.jsonPath().getInt("bookingid");

        if (id <= 0) {
            throw new RuntimeException(
                    "Invalid booking id: " + response.asString()
            );
        }

        context.setBookingId(id);
    }
}