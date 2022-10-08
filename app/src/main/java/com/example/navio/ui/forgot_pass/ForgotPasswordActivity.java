package com.example.navio.ui.forgot_pass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.navio.R;
import com.example.navio.backend.api.API;
import com.example.navio.backend.enums.EmailType;
import com.example.navio.backend.model.ForgotPasswordDetails;
import com.example.navio.backend.service.UserService;
import com.example.navio.ui.login.LoginActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;

public class ForgotPasswordActivity extends AppCompatActivity {

    private final UserService userService = UserService.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        final Button sendEmailButton = findViewById(R.id.send_email_button);
        final EditText emailInput = findViewById(R.id.input_email);

        final ConstraintLayout backArrowLayout = findViewById(R.id.back_arrow_layout);
        backArrowLayout.setOnClickListener(v -> finish());

        final ImageView backArrow = findViewById(R.id.back_arrow);
        backArrow.setOnClickListener(v -> finish());

        sendEmailButton.setOnClickListener(v -> userService.forgotPassword(this, new ForgotPasswordDetails(emailInput.getText().toString())));

    }


}