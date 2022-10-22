package com.example.navio.ui.home.cards;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.widget.ImageView;

import com.example.navio.R;
import com.example.navio.backend.api.apis.TaskAPI;
import com.example.navio.backend.enums.RequestTaskStatus;
import com.example.navio.backend.model.Task;

import java.util.ArrayList;
import java.util.List;

public class CompleteTasksViewMoreActivity extends AppCompatActivity {

    private final TaskAPI taskAPI = TaskAPI.getInstance();
    final List<Task> tasks = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_tasks_view_more);

        final ConstraintLayout backArrowLayout = findViewById(R.id.back_arrow_layout);
        backArrowLayout.setOnClickListener(v -> finish());

        final ImageView backArrow = findViewById(R.id.back_arrow);
        backArrow.setOnClickListener(v -> finish());

        taskAPI.requestForTasks(this, findViewById(R.id.tasks_list), tasks, RequestTaskStatus.COMPLETE);
    }
}