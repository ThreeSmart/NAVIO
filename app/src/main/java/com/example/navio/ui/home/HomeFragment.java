package com.example.navio.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.example.navio.R;
import com.example.navio.backend.api.apis.TaskAPI;
import com.example.navio.backend.enums.TaskStatus;
import com.example.navio.backend.model.User;
import com.example.navio.backend.service.AuthenticationService;
import com.example.navio.databinding.FragmentHomeBinding;
import com.example.navio.ui.home.cards.CompleteTasksViewMoreActivity;
import com.example.navio.ui.home.cards.PendingTasksViewMoreActivity;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private final TaskAPI taskAPI = TaskAPI.getInstance();
    TextView pendingTasksCount;
    TextView completeTasksCount;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView nameTextView = root.findViewById(R.id.name);
        final ConstraintLayout pendingTasksViewMoreLayout = root.findViewById(R.id.pending_tasks_view_more_layout);
        final ConstraintLayout completeTasksViewMoreLayout = root.findViewById(R.id.complete_tasks_view_more_layout);
        pendingTasksCount = root.findViewById(R.id.pending_tasks_count);
        completeTasksCount = root.findViewById(R.id.complete_tasks_count);

        pendingTasksCount.setText("");
        completeTasksCount.setText("");

        final User authenticatedUser = AuthenticationService.getAuthenticatedUser(root.getContext());
        final String name = authenticatedUser.getName();
        nameTextView.setText(name);

        pendingTasksViewMoreLayout.setOnClickListener(v -> {
            final Intent intent = new Intent(root.getContext(), PendingTasksViewMoreActivity.class);
            startActivity(intent);
        });

        completeTasksViewMoreLayout.setOnClickListener(v -> {
            final Intent intent = new Intent(root.getContext(), CompleteTasksViewMoreActivity.class);
            startActivity(intent);
        });

        return root;
    }

    @Override
    public void onStart() {
        taskAPI.updateTaskCount(getContext(), pendingTasksCount, TaskStatus.PENDING);
        taskAPI.updateTaskCount(getContext(), completeTasksCount, TaskStatus.COMPLETE);
        super.onStart();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}