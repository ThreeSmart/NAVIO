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
import com.example.navio.ui.login.LoginActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;

public class ForgotPasswordActivity extends AppCompatActivity {

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

        sendEmailButton.setOnClickListener(v -> {

            try {
                final Intent intent = new Intent(this, SendingEmailActivity.class);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                startActivity(intent);
                finish();

                final JSONObject body = new JSONObject();
                body.put("email", emailInput.getText().toString());
                body.put("email_type", EmailType.RESET_PASSWORD);

                final long apiCallStart = System.currentTimeMillis();
                API.makePost(
                        this,
                        "/send/email/by_type",
                        body,
                        e -> {
                            final Intent loginIntent = new Intent(this, LoginActivity.class);
                            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                            System.out.println(e.get("response"));

                            final long callDuration = System.currentTimeMillis() - apiCallStart;
                            if (callDuration <= 3000) {
                                final Timer timer = new Timer();
                                timer.schedule(new TimerTask() {
                                    @Override
                                    public void run() {
                                        startActivity(loginIntent);
                                        finish();
                                    }
                                }, Math.max(3000 - callDuration, 1000));
                            }
                        },
                        e -> {
                            final Intent loginIntent = new Intent(this, LoginActivity.class);
                            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                            startActivity(loginIntent);
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