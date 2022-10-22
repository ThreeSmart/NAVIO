package com.example.navio.ui.home.cards.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.navio.R;
import com.example.navio.backend.api.apis.TaskAPI;
import com.example.navio.backend.model.Task;

import java.util.List;

public class TaskStateAdapter extends RecyclerView.Adapter<TaskStateAdapter.ViewHolder> {

    private final LayoutInflater inflater;
    private final List<Task> tasks;
    private final TaskAPI taskAPI = TaskAPI.getInstance();

    public TaskStateAdapter(Context context, List<Task> tasks) {
        this.tasks = tasks;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public TaskStateAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.task, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TaskStateAdapter.ViewHolder holder, int position) {
        Task task = tasks.get(position);
        holder.name.setText(task.getName());

        final String priority = task.getPriority();
        switch (priority) {
            case "HIGH":
                holder.priority.setImageResource(R.drawable.high_priority_bg);
                break;
            case "MEDIUM":
                holder.priority.setImageResource(R.drawable.medium_priority_bg);
                break;
            case "LOW":
                holder.priority.setImageResource(R.drawable.low_priority_bg);
                break;
        }


        final String status = task.getStatus();
        switch (status) {
            case "PENDING":
                holder.status.setImageResource(R.drawable.pending_status_bg);
                holder.status.setOnClickListener(v -> {
                    taskAPI.makeTaskComplete(
                            inflater.getContext(),
                            task,
                            s -> Toast.makeText(inflater.getContext(), "Task '" + task.getName() + "' is now completed.", Toast.LENGTH_SHORT).show()
                    );
                    tasks.remove(task);
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, tasks.size());
                });
                break;

            case "COMPLETE":
                holder.status.setImageResource(R.drawable.complete_status_bg);
                Toast.makeText(inflater.getContext(), "The task is complete", Toast.LENGTH_SHORT).show();
                break;
        }

        holder.startDate.setText(task.getStartDate());
        holder.endDate.setText(task.getEndDate());
    }


    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView name;
        final ImageView priority;
        final ImageView status;
        final TextView startDate;
        final TextView endDate;

        ViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name);
            priority = view.findViewById(R.id.priority);
            status = view.findViewById(R.id.status);
            startDate = view.findViewById(R.id.startDate);
            endDate = view.findViewById(R.id.endDate);
        }

    }

}