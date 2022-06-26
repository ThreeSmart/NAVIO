package com.example.navio.backend.enums;

public enum ErrorMessages {
    USER_NOT_FOUND("No such user");

    public final String label;

    ErrorMessages(final String label) {
        this.label = label;
    }

    public static String findMessageByKey(final String key) {
        for (ErrorMessages message : ErrorMessages.values()) {
            if (message.name().equals(key)) {
                return message.label;
            }
        }
        return null;
    }

}
