package com.example.navio.backend.enums;

public enum RequestTaskStatus {
    PENDING("pending"),
    COMPLETE("completed");

    final String value;

    RequestTaskStatus(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
