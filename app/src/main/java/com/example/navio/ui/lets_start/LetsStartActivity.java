package com.example.navio.ui.lets_start;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.navio.ui.login.LoginActivity;
import com.example.navio.R;

public class LetsStartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lets_start_main);

        final Button letsStartButton = findViewById(R.id.lets_start_button);
        // To read our color
        letsStartButton.setBackgroundTintList(null);
        letsStartButton.setOnClickListener(view -> {
            final Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
    }

}