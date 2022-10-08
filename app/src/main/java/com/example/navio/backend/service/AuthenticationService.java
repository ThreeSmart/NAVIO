package com.example.navio.backend.service;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.auth0.android.jwt.JWT;
import com.example.navio.R;
import com.example.navio.backend.local_storage.LocalStorage;
import com.example.navio.backend.model.User;
import com.example.navio.ui.login.LoginActivity;

import java.util.Objects;

public class AuthenticationService {

    private static User currentUser;

    public static User getAuthenticatedUser(final Context context) {
        if (currentUser == null) currentUser = authenticateUser(context);
        validateUser(context);
        return currentUser;
    }

    private static void validateUser(final Context context) {
        if (currentUser == null) {
            final Intent intent = new Intent(context, LoginActivity.class);
            context.startActivity(intent);
            Toast.makeText(context, "Something went wrong, login again", Toast.LENGTH_SHORT).show();
            ((Activity) context).finish();
        }
    }

    private static User authenticateUser(final Context context) {
        final String jwtToken = JWTService.getInstance(context).getJwtToken();
        if (jwtToken == null) return null;

        final JWT jwt = new JWT(jwtToken);
        final long id = Long.parseLong(Objects.requireNonNull(jwt.getClaim("id").asString()));
        final String name = jwt.getClaim("name").asString();
        final String surname = jwt.getClaim("surname").asString();
        final String username = jwt.getClaim("username").asString();
        final String email = jwt.getClaim("email").asString();
        final String imageURL = jwt.getClaim("image_url").asString();
        return new User(id, name, surname, username, email, imageURL);
    }

}
