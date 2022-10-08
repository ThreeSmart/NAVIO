package com.example.navio.ui.login;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.navio.backend.model.UserLoginDetails;
import com.example.navio.backend.service.UserService;
import com.example.navio.ui.forgot_pass.ForgotPasswordActivity;
import com.example.navio.R;


public class LoginActivity extends AppCompatActivity {

    private final UserService userService = UserService.getInstance();

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText usernameInput = findViewById(R.id.usernameInput);
        final EditText passwordInput = findViewById(R.id.passwordInput);
        final TextView forgotPassText = findViewById(R.id.forgot_pass);
        final Button loginButton = findViewById(R.id.login_button);

        loginButton.setBackgroundTintList(null);
        loginButton.setOnClickListener(view -> {
            final String username = usernameInput.getText().toString();
            final String password = passwordInput.getText().toString();

            userService.login(this, new UserLoginDetails(username, password));
        });

        forgotPassText.setOnClickListener(v -> {
            final Intent intent = new Intent(this, ForgotPasswordActivity.class);
            startActivity(intent);
            finish();
        });

    }

}