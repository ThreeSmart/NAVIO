package com.example.navio.ui.forgot_pass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.navio.R;
import com.example.navio.SendingEmailActivity;

public class ForgotPasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        final TextView backArrow = findViewById(R.id.back_arrow);
        final Button sendEmailButton = findViewById(R.id.send_email_button);

        backArrow.setOnClickListener(v -> finish());

        sendEmailButton.setOnClickListener(v -> {
            final Intent intent = new Intent(this, SendingEmailActivity.class);
            startActivity(intent);
            finish();
        });

    }


}