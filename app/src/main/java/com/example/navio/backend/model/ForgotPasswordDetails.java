package com.example.navio.backend.model;

public class ForgotPasswordDetails {

    private final String email;

    public ForgotPasswordDetails(final String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

}
