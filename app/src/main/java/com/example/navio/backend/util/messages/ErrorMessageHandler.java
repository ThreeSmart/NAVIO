package com.example.navio.backend.util.messages;

import com.example.navio.backend.enums.ErrorMessages;

public class ErrorMessageHandler {

    public static String handle(final String key) {
        return ErrorMessages.findMessageByKey(key);
    }

}
