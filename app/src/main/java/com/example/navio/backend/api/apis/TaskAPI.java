package com.example.navio.backend.api.apis;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.view.Display;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import com.example.navio.R;
import com.example.navio.backend.api.API;
import com.example.navio.backend.model.Task;
import com.example.navio.backend.util.JsonClassConvertor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TaskAPI extends API {

    private static TaskAPI INSTANCE = null;

    public static TaskAPI getInstance() {
        if (INSTANCE == null) INSTANCE = new TaskAPI();
        return INSTANCE;
    }

    @SuppressLint("DefaultLocale")
    public void makeTaskCards(final Context context, final LinearLayout taskCardsLayout) {

        API.makeGet(
                context,
                String.format("/tasks/pending?page=%d&size=%d", 0, 10),
                e -> makeTaskCards(context, e, taskCardsLayout),
                error -> System.out.println("Could not load pending tasks")
        );

    }

    @SuppressLint("DefaultLocale")
    private void makeTaskCards(final Context context, final String response, final LinearLayout view) throws JSONException {
        final JSONArray jsonArray = new JSONArray(response);

        final TextView noPendingTasksTest = new TextView(context);
        noPendingTasksTest.setText(context.getString(R.string.No_pending_tasks));
        noPendingTasksTest.setTextSize(25);
        noPendingTasksTest.setTextColor(Color.BLACK);
        noPendingTasksTest.setPadding(250, 50, 0, 0);

        if (jsonArray.length() == 0) {
            view.addView(noPendingTasksTest);
            return;
        }

        for (int i = 0; i < jsonArray.length(); i++) {
            final JSONObject e = (JSONObject) jsonArray.get(i);
            final Task task = new Task();
            JsonClassConvertor.buildObject(e, task);
            final int taskId = Integer.parseInt(task.getId().toString());

            int counter = 0;
            // Making layout for card
            final ConstraintLayout constraintLayout = new ConstraintLayout(context);
            final LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    300
            );
            params.setMargins(50, 50, 50, 50);

            constraintLayout.setId(taskId + counter++);
            constraintLayout.setLayoutParams(params);
            constraintLayout.setBackgroundResource(R.drawable.task_card_bg);
            constraintLayout.setElevation(700);

            // adding name text to layout
            final TextView nameText = new TextView(context);
            nameText.setId(taskId + counter++);
            nameText.setText((CharSequence) task.getName());
            nameText.setTextColor(Color.BLACK);
            nameText.setTextSize(20);
            nameText.setLines(2);
            Display defaultDisplay = ((Activity) context).getWindowManager().getDefaultDisplay();
            Point point = new Point();
            defaultDisplay.getSize(point);

            nameText.setWidth(point.x - 300);
            constraintLayout.addView(nameText);

            ConstraintSet set = new ConstraintSet();
            set.clone(constraintLayout);
            set.connect(nameText.getId(), ConstraintSet.START, constraintLayout.getId(), ConstraintSet.START, 30);
            set.connect(nameText.getId(), ConstraintSet.TOP, constraintLayout.getId(), ConstraintSet.TOP, 10);
            set.applyTo(constraintLayout);

            // priority field
            final ImageView priorityField = new ImageView(context);

            final String priority = task.getPriority();
            switch (priority) {
                case "HIGH":
                    priorityField.setImageResource(R.drawable.high_priority_bg);
                    break;
                case "MEDIUM":
                    priorityField.setImageResource(R.drawable.medium_priority_bg);
                    break;
                case "LOW":
                    priorityField.setImageResource(R.drawable.low_priority_bg);
                    break;
            }

            priorityField.setOnClickListener(
                    v -> Toast.makeText(context, priority, Toast.LENGTH_SHORT).show()
            );

            priorityField.setId(taskId + counter++);
            final ViewGroup.LayoutParams priorityFieldSizes = new ViewGroup.LayoutParams(170, 170);
            priorityField.setLayoutParams(priorityFieldSizes);
            constraintLayout.addView(priorityField);

            set.clone(constraintLayout);
            set.connect(priorityField.getId(), ConstraintSet.END, constraintLayout.getId(), ConstraintSet.END, 20);
            set.connect(priorityField.getId(), ConstraintSet.TOP, constraintLayout.getId(), ConstraintSet.TOP, -20);
            set.applyTo(constraintLayout);

            // status field
            final ImageView statusField = new ImageView(context);
            statusField.setImageResource(R.drawable.pending_status_bg);
            statusField.setId(taskId + counter++);

            final ViewGroup.LayoutParams statusFieldSizes = new ViewGroup.LayoutParams(390, 390);
            statusField.setLayoutParams(statusFieldSizes);
            statusField.setOnClickListener(v -> {
                try {
                    API.makeGet(
                            context,
                            "/tasks/complete?id=" + (e.get("id")),
                            onSuccess -> {
                                view.removeAllViews();
                                API.makeGet(
                                        context,
                                        String.format("/tasks/pending?page=%d&size=%d", 0, 10),
                                        onUpdate -> makeTaskCards(context, onUpdate, view),
                                        error -> System.out.println("Could not load pending tasks")
                                );
                            },
                            error -> {
                                System.out.println("");
                            }
                    );
                } catch (JSONException ex) {
                    ex.printStackTrace();
                }
            });
            constraintLayout.addView(statusField);

            set.clone(constraintLayout);
            set.connect(statusField.getId(), ConstraintSet.BOTTOM, constraintLayout.getId(), ConstraintSet.BOTTOM, -200);
            set.connect(statusField.getId(), ConstraintSet.TOP, constraintLayout.getId(), ConstraintSet.TOP, 0);
            set.connect(statusField.getId(), ConstraintSet.START, constraintLayout.getId(), ConstraintSet.START, 0);
            set.connect(statusField.getId(), ConstraintSet.END, constraintLayout.getId(), ConstraintSet.END, 0);
            set.applyTo(constraintLayout);

            // start date
            final LinearLayout startDateLayout = new LinearLayout(context);
            startDateLayout.setId(taskId + counter++);
            startDateLayout.setOrientation(LinearLayout.HORIZONTAL);
            constraintLayout.addView(startDateLayout);

            final ImageView startDateIcon = new ImageView(context);
            startDateIcon.setId(taskId + counter++);
            final ViewGroup.LayoutParams startDateIconParams = new ViewGroup.LayoutParams(50, 50);
            startDateIcon.setLayoutParams(startDateIconParams);
            startDateIcon.setImageResource(R.drawable.start_date_icon);

            startDateLayout.addView(startDateIcon);

            final TextView startDateField = new TextView(context);
            startDateField.setId(taskId + counter++);
            startDateField.setText(task.getStartDate());
            startDateField.setTextColor(Color.BLACK);
            startDateField.setTextSize(13);
            startDateField.setLines(1);
            startDateField.setPadding(10, 0, 0, 0);
            startDateLayout.addView(startDateField);

            set.clone(constraintLayout);
            set.connect(startDateLayout.getId(), ConstraintSet.BOTTOM, constraintLayout.getId(), ConstraintSet.BOTTOM, -200);
            set.connect(startDateLayout.getId(), ConstraintSet.TOP, constraintLayout.getId(), ConstraintSet.TOP, 0);
            set.connect(startDateLayout.getId(), ConstraintSet.START, constraintLayout.getId(), ConstraintSet.START, -700);
            set.connect(startDateLayout.getId(), ConstraintSet.END, constraintLayout.getId(), ConstraintSet.END, 0);
            set.applyTo(constraintLayout);

            // end date
            final LinearLayout endDateLayout = new LinearLayout(context);
            endDateLayout.setId(taskId + counter++);
            endDateLayout.setOrientation(LinearLayout.HORIZONTAL);
            constraintLayout.addView(endDateLayout);

            final ImageView endDateIcon = new ImageView(context);
            endDateIcon.setId(taskId + counter++);
            final ViewGroup.LayoutParams endDateLayoutParams = new ViewGroup.LayoutParams(50, 50);
            endDateIcon.setLayoutParams(endDateLayoutParams);
            endDateIcon.setImageResource(R.drawable.end_date_icno);

            endDateLayout.addView(endDateIcon);

            final TextView endDateFiled = new TextView(context);
            endDateFiled.setId(taskId + counter++);
            endDateFiled.setText((CharSequence) task.getEndDate());
            endDateFiled.setTextColor(Color.BLACK);
            endDateFiled.setTextSize(13);
            endDateFiled.setLines(1);
            endDateFiled.setPadding(10, 0, 0, 0);
            endDateLayout.addView(endDateFiled);

            set.clone(constraintLayout);
            set.connect(endDateLayout.getId(), ConstraintSet.BOTTOM, constraintLayout.getId(), ConstraintSet.BOTTOM, -200);
            set.connect(endDateLayout.getId(), ConstraintSet.TOP, constraintLayout.getId(), ConstraintSet.TOP, 0);
            set.connect(endDateLayout.getId(), ConstraintSet.START, constraintLayout.getId(), ConstraintSet.START, 0);
            set.connect(endDateLayout.getId(), ConstraintSet.END, constraintLayout.getId(), ConstraintSet.END, -700);
            set.applyTo(constraintLayout);

            // adding layout to scroll view
            view.addView(constraintLayout);
        }

    }

}
