package com.booking.models;

import java.util.List;

public class ErrorResponse {

    private String error;
    private List<String> errors;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}