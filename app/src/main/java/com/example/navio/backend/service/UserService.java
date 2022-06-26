package com.example.navio.backend.service;

import android.content.Context;

import com.example.navio.backend.api.API;
import com.example.navio.backend.api.CallBack;
import com.example.navio.backend.model.User;
import com.example.navio.backend.model.UserLoginDetails;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Optional;

public class UserService {

    private static UserService INSTANCE = null;

    private UserService() {
    }

    public static UserService getInstance() {
        if (INSTANCE == null) INSTANCE = new UserService();
        return INSTANCE;
    }

    public void login(final Context context,
                      final UserLoginDetails userLoginDetails,
                      final CallBack onSuccess,
                      final CallBack onError) {

        final JSONObject body = new JSONObject();
        try {
            body.put("username", userLoginDetails.getUsername());
            body.put("password", userLoginDetails.getPassword());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        API.makePost(
                context,
                "/login/user",
                body,
                onSuccess,
                onError
        );
    }

}
