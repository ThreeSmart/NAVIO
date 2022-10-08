package com.example.navio.backend.service;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.navio.R;
import com.example.navio.backend.api.API;
import com.example.navio.backend.api.CallBack;
import com.example.navio.backend.api.apis.UserAPI;
import com.example.navio.backend.enums.EmailType;
import com.example.navio.backend.local_storage.LocalStorage;
import com.example.navio.backend.model.ForgotPasswordDetails;
import com.example.navio.backend.model.User;
import com.example.navio.backend.model.UserLoginDetails;
import com.example.navio.backend.util.messages.ErrorMessageHandler;
import com.example.navio.ui.forgot_pass.ForgotPasswordActivity;
import com.example.navio.ui.forgot_pass.SendingEmailActivity;
import com.example.navio.ui.login.LoginActivity;
import com.example.navio.ui.main.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;

public class UserService {

    private static UserService INSTANCE = null;
    private final UserAPI userAPI = UserAPI.getInstance();

    private UserService() {
    }

    public static UserService getInstance() {
        if (INSTANCE == null) INSTANCE = new UserService();
        return INSTANCE;
    }

    public void login(final Context context,
                      final UserLoginDetails userLoginDetails) {

        final JSONObject body = new JSONObject();
        try {
            body.put("username", userLoginDetails.getUsername());
            body.put("password", userLoginDetails.getPassword());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        userAPI.login(context, body, onLoginSuccess(context), onLoginError(context));
    }

    @NonNull
    private CallBack onLoginSuccess(Context context) {
        return success -> {
            try {
                final JSONObject jwtObject = new JSONObject(success);
                final String jwt = String.valueOf(jwtObject.get("jwt"));

                // Write jwt into local storage
                LocalStorage.getInstance()
                        .setContext(context)
                        .setKey(context.getString(R.string.local_authentication))
                        .writeString(context.getString(R.string.local_jwt), jwt);

                // Change screen from Login to Main page
                final Intent intent = new Intent(context, MainActivity.class);
                context.startActivity(intent);
                ((Activity) context).finish();

            } catch (final JSONException e) {
                e.printStackTrace();
            }
        };
    }

    @NonNull
    private CallBack onLoginError(Context context) {
        return error -> {
            try {
                final JSONObject errorObject = new JSONObject(error);

                // Toast error message
                Toast.makeText(context, ErrorMessageHandler.handle(errorObject.get("message").toString()), Toast.LENGTH_SHORT).show();
            } catch (final JSONException e) {
                e.printStackTrace();
            }
        };
    }

    public void forgotPassword(final Context context, final ForgotPasswordDetails forgotPasswordDetails) {
        final JSONObject body = new JSONObject();
        try {
            body.put("email", forgotPasswordDetails.getEmail());
            body.put("email_type", EmailType.RESET_PASSWORD);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        final Intent sendingEmailActivity = new Intent(context, SendingEmailActivity.class);
        ((Activity) context).overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        context.startActivity(sendingEmailActivity);
        ((Activity) context).finish();

        userAPI.forgotPassword(context, body, onForgotPasswordSuccess(context), onForgotPasswordError(context));
    }

    @NonNull
    private CallBack onForgotPasswordSuccess(final Context context) {
        return success -> System.out.println("Successfully sent email");
    }

    @NonNull
    private CallBack onForgotPasswordError(final Context context) {
        return error -> {
            final JSONObject errorObject = new JSONObject(error);
            String message = errorObject.get("message").toString();
            System.out.println("Failed to send email, error is: " + message);
            Toast.makeText(context, "Failed to send email", Toast.LENGTH_SHORT).show();
        };
    }

}
