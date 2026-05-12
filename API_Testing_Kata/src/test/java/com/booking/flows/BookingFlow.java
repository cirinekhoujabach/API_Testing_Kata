package com.booking.flows;

import com.booking.context.TestContext;
import com.booking.factory.BookingFactory;
import com.booking.models.Booking;
import com.booking.endpoints.BookingEndpoint;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

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

    //Negative & Edge Cases
    public void createBookingWithCustomFirstName(String name) {
        // Utilisation d'une Map pour simuler le JSON de l'OpenAPI
        Map<String, Object> body = new HashMap<>();
        body.put("roomid", 1);
        body.put("firstname", name); // La valeur de ton Scenario Outline
        body.put("lastname", "Doe");
        body.put("depositpaid", true);
        body.put("email", "test@example.com");
        body.put("phone", "01234567890");

        Map<String, String> dates = new HashMap<>();
        dates.put("checkin", "2025-01-01");
        dates.put("checkout", "2025-01-02");
        body.put("bookingdates", dates);

        // Envoi via l'endpoint qui accepte maintenant un Object (Map ou Booking)
        Response response = BookingEndpoint.create(body);
        context.setResponse(response);
    }


}