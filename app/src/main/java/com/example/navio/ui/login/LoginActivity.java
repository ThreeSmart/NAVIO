package com.example.navio.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.navio.ui.main.MainActivity;
import com.example.navio.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final Button loginButton = findViewById(R.id.login_button);
        loginButton.setBackgroundTintList(null);
        loginButton.setOnClickListener(view -> {
            final Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }

}