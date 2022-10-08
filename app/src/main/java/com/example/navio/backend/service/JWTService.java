package com.example.navio.backend.service;

import android.content.Context;

import com.example.navio.R;
import com.example.navio.backend.local_storage.LocalStorage;

public class JWTService {

    private Context context;

    private JWTService() {
    }

    public static JWTService getInstance(final Context context) {
        final JWTService jwtService = new JWTService();
        jwtService.context = context;
        return jwtService;
    }

    public String getJwtToken() {
        return LocalStorage.getInstance()
                .setContext(context)
                .setKey(context.getString(R.string.local_authentication))
                .getString(context.getString(R.string.local_jwt));
    }

}
