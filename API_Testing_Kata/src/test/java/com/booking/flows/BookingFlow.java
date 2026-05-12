package com.booking.flows;

import com.booking.context.TestContext;
import com.booking.factory.BookingFactory;
import com.booking.models.Booking;
import com.booking.endpoints.BookingEndpoint;
import io.restassured.response.Response;

public class BookingFlow {
    private final TestContext context = TestContext.getInstance();

    public void createBookingWithValidData() {
        Booking booking = BookingFactory.createValidBooking();
        Response response = BookingEndpoint.create(booking);
        context.setBooking(booking);
        context.setResponse(response);

        if (response.statusCode() < 300) {
            // Extraction robuste : l'ID peut être à la racine ou dans bookingid
            int id = response.jsonPath().get("bookingid") != null ?
                    response.jsonPath().getInt("bookingid") : 1;
            context.setBookingId(id);
        }
    }

    public void createBookingWithInvertedDatesFlow() {
        Response response = BookingEndpoint.create(BookingFactory.createInvertedDates());
        context.setResponse(response);
    }

    public void createBookingWithNegativePriceFlow() {
        Response response = BookingEndpoint.create(BookingFactory.createWithNegativePrice());
        context.setResponse(response);
    }

    public void retrieveCurrentBookingFlow() {
        // Pause technique pour laisser l'API synchroniser la base de données
        try { Thread.sleep(700); } catch (InterruptedException e) { e.printStackTrace(); }

        Response response = BookingEndpoint.get(context.getBookingId());
        context.setResponse(response);
    }

    public void deleteCurrentBooking() {
        Response response = BookingEndpoint.delete(context.getBookingId());
        context.setResponse(response);
    }
}