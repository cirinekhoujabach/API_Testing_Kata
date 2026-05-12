package com.booking.factory;

import com.booking.models.Booking;
import com.booking.models.BookingDates;
import net.datafaker.Faker;

public class BookingFactory {
    private static final Faker faker = new Faker();

    public static Booking createValidBooking() {
        Booking b = new Booking();
        b.setRoomid(1);
        b.setFirstname("JamesonAutomation");
        b.setLastname("SmithfieldTester");
        b.setDepositpaid(true);
        b.setEmail("test@example.com");
        b.setPhone("012345678901");

        BookingDates dates = new BookingDates();
        dates.setCheckin("2026-06-01");
        dates.setCheckout("2026-06-10");
        b.setBookingdates(dates);
        return b;
    }

    public static Booking createWithNegativePrice() {
        return createValidBooking();
    }

    public static Booking createInvertedDates() {
        Booking b = createValidBooking();
        BookingDates dates = new BookingDates();
        dates.setCheckin("2026-12-31");
        dates.setCheckout("2026-01-01");
        b.setBookingdates(dates);
        return b;
    }
}