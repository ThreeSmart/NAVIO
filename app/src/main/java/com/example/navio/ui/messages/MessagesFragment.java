package com.example.navio.ui.messages;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.navio.R;
import com.example.navio.backend.model.User;
import com.example.navio.databinding.FragmentMessagesBinding;
import com.example.navio.ui.messages.messages_builder.MessageViewAdapter;
import com.example.navio.ui.messages.util.Message;

import java.util.ArrayList;

public class MessagesFragment extends Fragment {

    private FragmentMessagesBinding binding;
    private RecyclerView recyclerView;
    private ConstraintLayout constraintLayout;
    private EditText typeMessageET;
    private int lastRVVisibleItemPosition;

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater,
                             @Nullable final ViewGroup container,
                             @Nullable final Bundle savedInstanceState) {
        binding = FragmentMessagesBinding.inflate(inflater, container, false);
        final View root = binding.getRoot();

        // Populate dummy messages in List, you can implement your code here
        ArrayList<Message> messagesList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            if (i % 2 == 0) {
                messagesList.add(new Message(i, "Hi " + i, 1, 2, String.valueOf(System.currentTimeMillis()), false));
            } else {
                messagesList.add(new Message(i, "Hi " + i, 2, 1, String.valueOf(System.currentTimeMillis()), false));
            }
        }
        MessageViewAdapter adapter = new MessageViewAdapter(root.getContext(), messagesList);

        constraintLayout = root.findViewById(R.id.messages_cl);
        typeMessageET = root.findViewById(R.id.type_message_et);
        recyclerView = root.findViewById(R.id.messages_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
        recyclerView.setAdapter(adapter);
        recyclerView.scrollToPosition(messagesList.size() - 1);
        LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        typeMessageET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                recyclerView.scrollToPosition(lastRVVisibleItemPosition);
            }
        });
        constraintLayout.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View view, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                if (bottom < oldBottom) {
                    recyclerView.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            recyclerView.scrollToPosition(lastRVVisibleItemPosition);
                        }
                    }, 0);
                }

            }

        });
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }

            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_FLING) {
                    // do something
                } else if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    // do something
                } else {
                    // do something
                }
                if (layoutManager != null) {
                    lastRVVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
                }
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}
