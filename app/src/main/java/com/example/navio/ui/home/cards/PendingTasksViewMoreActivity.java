package com.example.navio.ui.home.cards;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.navio.R;

public class PendingTasksViewMoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pendingtasks_view_more);

        final ConstraintLayout backArrowLayout = findViewById(R.id.back_arrow_layout);
        backArrowLayout.setOnClickListener(v -> finish());

        final ImageView backArrow = findViewById(R.id.back_arrow);
        backArrow.setOnClickListener(v -> finish());

    }
}