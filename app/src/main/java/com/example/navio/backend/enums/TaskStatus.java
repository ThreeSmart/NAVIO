package com.example.navio.backend.enums;

public enum TaskStatus {
    PENDING("PENDING"),
    COMPLETE("COMPLETE");

    final String value;

    TaskStatus(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
