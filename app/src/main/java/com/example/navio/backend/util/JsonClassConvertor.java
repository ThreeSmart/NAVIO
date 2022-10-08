package com.example.navio.backend.util;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;

public class JsonClassConvertor {

    // TODO: Beta version just for testing, please implement new logic every time when needed
    @SuppressWarnings("unchecked")
    public static <T> void buildObject(final JSONObject jsonObject, T object) throws JSONException {
        final Field[] fields = object.getClass().getDeclaredFields();
        try {
            for (final Field field : fields) {
                if (jsonObject.has(field.getName())) {
                    field.setAccessible(true);
                    if (jsonObject.get(field.getName()) instanceof Integer) {
                        field.set(object, Long.parseLong(jsonObject.get(field.getName()).toString()));
                    } else {
                        field.set(object, jsonObject.get(field.getName()));
                    }
                    field.setAccessible(false);
                } else {
                    field.setAccessible(true);
                    field.set(object, null);
                    field.setAccessible(false);
                }
            }
        } catch (IllegalAccessException e) {
            System.out.println("Could not set field value");
            e.printStackTrace();
        }

    }

}
