package com.booking.services;

import io.restassured.RestAssured;

public class BaseService {

    static {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
    }
}