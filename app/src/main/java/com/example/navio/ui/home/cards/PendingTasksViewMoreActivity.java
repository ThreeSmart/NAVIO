package com.example.navio.ui.home.cards;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.navio.R;

public class PendingTasksViewMoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pendingtasks_view_more);

        final TextView backArrow = findViewById(R.id.back_arrow);
        backArrow.setOnClickListener(v -> finish());

    }
}