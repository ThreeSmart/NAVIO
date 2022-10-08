package com.example.navio.ui.home.cards;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.navio.R;
import com.example.navio.backend.api.apis.TaskAPI;


public class PendingTasksViewMoreActivity extends AppCompatActivity {

    private final TaskAPI taskAPI = TaskAPI.getInstance();

    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pendingtasks_view_more);

        final ConstraintLayout backArrowLayout = findViewById(R.id.back_arrow_layout);
        backArrowLayout.setOnClickListener(v -> finish());

        final ImageView backArrow = findViewById(R.id.back_arrow);
        backArrow.setOnClickListener(v -> finish());

        final LinearLayout taskCardsLayout = findViewById(R.id.tasks_card_layout);

        taskAPI.makeTaskCards(this, taskCardsLayout);

    }

}