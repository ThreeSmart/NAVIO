package com.example.navio.ui.account.pages;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.navio.R;

public class AccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        final TextView backArrow = findViewById(R.id.back_arrow);
        backArrow.setOnClickListener(v -> finish());

    }
}