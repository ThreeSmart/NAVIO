package com.example.navio.backend.service;

import androidx.annotation.NonNull;

import com.auth0.android.jwt.JWT;
import com.example.navio.R;
import com.example.navio.backend.local_storage.LocalStorage;
import com.example.navio.backend.model.User;

public class AuthenticationService {

    private static User currentUser;

    @NonNull
    public static User getAuthenticatedUser(){
        if(currentUser != null){
            return currentUser;
        }
        currentUser = new User();
        return currentUser;
    }

    private static void authenticateUser(User user){
        if(user != null) {
            currentUser = new User(user.getId(), user.getName(), user.getSurname(), user.getUsername(), user.getEmail(), user.getImageURL());
        }
    }

    public static void authenticateUser(JWT jwt){
        final long id = jwt.getClaim("id").asLong();
        final String name = jwt.getClaim("name").asString();
        final String surname = jwt.getClaim("surname").asString();
        final String username = jwt.getClaim("username").asString();
        final String email = jwt.getClaim("email").asString();
        final String imageURL = jwt.getClaim("image_url").asString();
        User currentUser = new User(id,name, surname, username, email, imageURL);
        AuthenticationService.authenticateUser(currentUser);
    }
}
