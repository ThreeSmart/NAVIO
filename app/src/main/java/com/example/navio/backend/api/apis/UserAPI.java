package com.example.navio.backend.api.apis;

import android.content.Context;

import com.example.navio.backend.api.API;
import com.example.navio.backend.api.CallBack;

import org.json.JSONObject;

public class UserAPI extends API {

    private static UserAPI INSTANCE = null;

    private UserAPI() {
    }

    public static UserAPI getInstance() {
        if (INSTANCE == null) INSTANCE = new UserAPI();
        return INSTANCE;
    }

    public void login(final Context context, final JSONObject body, final CallBack onSuccess, final CallBack onError) {
        makePost(context, "/login/user", body, onSuccess, onError);
    }

    public void forgotPassword(final Context context, final JSONObject body, final CallBack onSuccess, final CallBack onError) {
        makePost(context, "/send/email/by_type", body, onSuccess, onError);
    }
}
