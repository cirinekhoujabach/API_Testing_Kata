package com.booking.hooks;

import io.cucumber.java.Before;

public class Hooks {

    @Before
    public void setup() {

        System.out.println("--- START TEST SCENARIO ---");
    }
}