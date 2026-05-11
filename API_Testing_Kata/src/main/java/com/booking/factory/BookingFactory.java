package com.booking.factory;

import com.booking.models.Booking;
import com.booking.models.BookingDates;
import net.datafaker.Faker;

public class BookingFactory {

    private static final Faker faker = new Faker();

    public static Booking createValidBooking() {

        Booking b = new Booking();

        b.setRoomid(faker.number().numberBetween(1, 100));
        b.setFirstname(faker.name().firstName());
        b.setLastname(faker.name().lastName());
        b.setDepositpaid(true);

        b.setEmail(faker.internet().emailAddress());
        b.setPhone(faker.number().digits(10));

        BookingDates dates = new BookingDates();
        dates.setCheckin("2026-06-01");
        dates.setCheckout("2026-06-10");

        b.setBookingdates(dates);

        return b;
    }

    public static Booking createInvalidEmail() {
        Booking b = createValidBooking();
        b.setEmail("invalid-email");
        return b;
    }

    public static Booking createMissingFields() {
        Booking b = new Booking();
        b.setFirstname(null);
        b.setLastname(null);
        return b;
    }

    public static Booking createInvertedDates() {
        Booking b = createValidBooking();
        b.getBookingdates().setCheckin("2026-06-10");
        b.getBookingdates().setCheckout("2026-06-01");
        return b;
    }

    public static Booking createWithNegativePrice() {
        return createValidBooking();
    }
}