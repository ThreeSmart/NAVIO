package com.example.navio.backend.api.apis;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.navio.backend.api.API;
import com.example.navio.backend.api.CallBack;
import com.example.navio.backend.enums.RequestTaskStatus;
import com.example.navio.backend.enums.TaskStatus;
import com.example.navio.backend.model.Task;
import com.example.navio.backend.util.JsonClassConvertor;
import com.example.navio.ui.home.cards.adapters.TaskStateAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class TaskAPI extends API {

    private static TaskAPI INSTANCE = null;

    private TaskAPI() {
    }

    public static TaskAPI getInstance() {
        if (INSTANCE == null) INSTANCE = new TaskAPI();
        return INSTANCE;
    }

    @SuppressLint("DefaultLocale")
    public void requestForTasks(final Context context, final RecyclerView view, final List<Task> tasks, final RequestTaskStatus status) {

        API.makeGet(
                context,
                String.format("/tasks/" + status.getValue() + "?page=%d&size=%d", 0, 10),
                response -> makeTaskCardsNew(context, view, response, tasks),
                error -> System.out.println("Could not load pending tasks")
        );

    }

    public void makeTaskComplete(final Context context, final Task task, final CallBack onSuccess) {
        API.makeGet(
                context,
                "/tasks/complete?id=" + task.getId(),
                onSuccess,
                error -> {
                    System.out.println("");
                }
        );
    }

    private void makeTaskCardsNew(Context context, RecyclerView view, String response, List<Task> tasks) throws JSONException {
        final JSONArray jsonArray = new JSONArray(response);
        // handle empty list

        for (int i = 0; i < jsonArray.length(); i++) {
            final JSONObject e = (JSONObject) jsonArray.get(i);
            final Task task = new Task();
            JsonClassConvertor.buildObject(e, task);
            tasks.add(task);
        }

        TaskStateAdapter taskStateAdapter = new TaskStateAdapter(context, tasks);
        view.setAdapter(taskStateAdapter);
    }

    public void updateTaskCount(final Context context, final TextView textView, final TaskStatus status) {
        API.makeGet(
                context,
                "/tasks/count?status=" + status.getValue(),
                e -> {
                    JSONObject jsonObject = new JSONObject(e);
                    textView.setText(jsonObject.get("count").toString());
                },
                error -> System.out.println("")
        );
    }

}
