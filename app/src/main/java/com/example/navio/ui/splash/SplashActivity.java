package com.example.navio.ui.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.navio.R;
import com.example.navio.backend.local_storage.LocalStorage;
import com.example.navio.ui.lets_start.LetsStartActivity;
import com.example.navio.ui.login.LoginActivity;
import com.example.navio.ui.main.MainActivity;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        final Context context = this;
        new Handler().postDelayed(() -> {
            boolean letsStartExecuted = LocalStorage.getInstance()
                    .setContext(this)
                    .setKey(getString(R.string.local_lets_start))
                    .getBoolean(getString(R.string.local_executed));

            final Intent intent;
            if (!letsStartExecuted) {
                intent = new Intent(context, LetsStartActivity.class);
            } else {
                final String jwt = LocalStorage.getInstance()
                        .setContext(this)
                        .setKey(getString(R.string.local_authentication))
                        .getString(getString(R.string.local_jwt));

                // TODO change jwt validation with parser or something written in JWT connected class
                if (jwt == null) {
                    intent = new Intent(context, LoginActivity.class);
                } else {
                    intent = new Intent(context, MainActivity.class);
                }

            }

            startActivity(intent);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            finish();
        }, 500);
    }

}