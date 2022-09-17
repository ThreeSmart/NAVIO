package com.example.navio.ui.forgot_pass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.navio.R;
import com.example.navio.backend.api.API;
import com.example.navio.backend.enums.EmailType;
import com.example.navio.ui.login.LoginActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class ForgotPasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        final TextView backArrow = findViewById(R.id.back_arrow);
        final Button sendEmailButton = findViewById(R.id.send_email_button);
        final EditText emailInput = findViewById(R.id.input_email);

        backArrow.setOnClickListener(v -> finish());

        sendEmailButton.setOnClickListener(v -> {

            try {
                final Intent intent = new Intent(this, SendingEmailActivity.class);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                startActivity(intent);
                finish();

                final JSONObject body = new JSONObject();
                body.put("email", emailInput.getText().toString());
                body.put("email_type", EmailType.RESET_PASSWORD);
                API.makePost(
                        this,
                        "/send/email/by_type",
                        body,
                        e -> {
                            final Intent intentt = new Intent(this, LoginActivity.class);
                            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                            System.out.println(e.get("response"));
                            startActivity(intentt);
                            finish();
                        },
                        e -> {
                            final Intent intentt = new Intent(this, LoginActivity.class);
                            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                            startActivity(intentt);
                            finish();
                            Toast.makeText(this, "Failed to send email", Toast.LENGTH_SHORT).show();
                        }
                );

            } catch (JSONException e) {
                e.printStackTrace();
            }
        });

    }


}