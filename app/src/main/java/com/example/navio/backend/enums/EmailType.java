package com.example.navio.backend.enums;

import androidx.annotation.NonNull;

public enum EmailType {
    RESET_PASSWORD("RESET_PASSWORD");

    private final String value;

    EmailType(final String value) {
        this.value = value;
    }

    @NonNull
    @Override
    public String toString() {
        return this.value;
    }
}
