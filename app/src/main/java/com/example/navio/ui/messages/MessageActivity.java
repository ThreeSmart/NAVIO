package com.example.navio.ui.messages;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.EditText;

import com.example.navio.R;
import com.example.navio.ui.messages.messages_builder.MessageViewAdapter;
import com.example.navio.ui.messages.util.Message;

import java.util.ArrayList;

public class MessageActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ConstraintLayout constraintLayout;
    private EditText typeMessageET;
    private int lastRVVisibleItemPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        // Populate dummy messages in List, you can implement your code here
        ArrayList<Message> messagesList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            if (i % 2 == 0) {
                messagesList.add(new Message(i, "Hi " + i, 1, 2, String.valueOf(System.currentTimeMillis()), false));
            } else {
                messagesList.add(new Message(i, "Hi " + i, 2, 1, String.valueOf(System.currentTimeMillis()), false));
            }
        }
        MessageViewAdapter adapter = new MessageViewAdapter(this, messagesList);

        constraintLayout = findViewById(R.id.messages_cl);
        typeMessageET = findViewById(R.id.type_message_et);
        recyclerView = findViewById(R.id.messages_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
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
    }
}