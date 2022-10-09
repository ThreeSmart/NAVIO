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
import com.example.navio.ui.messages.util.UsersMessage;

import java.util.ArrayList;

public class MessagesFragment extends Fragment {

    private FragmentMessagesBinding binding;
    public ArrayList<UsersMessage> usersMessages = new ArrayList<UsersMessage>();

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater,
                             @Nullable final ViewGroup container,
                             @Nullable final Bundle savedInstanceState) {
        binding = FragmentMessagesBinding.inflate(inflater, container, false);
        final View root = binding.getRoot();
        usersMessages.add(new UsersMessage("navIO Technical Support", "You: Hi bro!","10:33", R.drawable.status_online,R.drawable.status_online, R.drawable.waiting));
        usersMessages.add(new UsersMessage ("Zaven Khanamiryan", "You: Hi bro!", "12:00",R.drawable.status_online,R.drawable.status_offline, R.drawable.check));
        usersMessages.add(new UsersMessage ("Zaven Khanamiryan", "You: Hi bro!", "14:09",R.drawable.status_online,R.drawable.status_offline, R.drawable.doublecheck));
        usersMessages.add(new UsersMessage ("Zaven Khanamiryan", "You: Hi bro!", "14:09",R.drawable.status_online,R.drawable.status_offline, R.drawable.doublecheck));
        usersMessages.add(new UsersMessage ("Zaven Khanamiryan", "You: Hi bro!", "14:09",R.drawable.status_online,R.drawable.status_offline, R.drawable.doublecheck));
        usersMessages.add(new UsersMessage ("Zaven Khanamiryan", "You: Hi bro!", "14:09",R.drawable.status_online,R.drawable.status_offline, R.drawable.doublecheck));
        usersMessages.add(new UsersMessage ("Zaven Khanamiryan", "You: Hi bro!", "14:09",R.drawable.status_online,R.drawable.status_offline, R.drawable.doublecheck));
        usersMessages.add(new UsersMessage ("Zaven Khanamiryan", "You: Hi bro!", "14:09",R.drawable.status_online,R.drawable.status_offline, R.drawable.doublecheck));
        usersMessages.add(new UsersMessage ("Zaven Khanamiryan", "You: Hi bro!", "14:09",R.drawable.status_online,R.drawable.status_offline, R.drawable.doublecheck));
        usersMessages.add(new UsersMessage ("Zaven Khanamiryan", "You: Hi bro!", "14:09",R.drawable.status_online,R.drawable.status_offline, R.drawable.doublecheck));
        usersMessages.add(new UsersMessage ("Zaven Khanamiryan", "You: Hi bro!", "14:09",R.drawable.status_online,R.drawable.status_offline, R.drawable.doublecheck));
        usersMessages.add(new UsersMessage ("Zaven Khanamiryan", "You: Hi bro!", "14:09",R.drawable.status_online,R.drawable.status_offline, R.drawable.doublecheck));
        usersMessages.add(new UsersMessage ("Zaven Khanamiryan", "You: Hi bro!", "14:09",R.drawable.status_online,R.drawable.status_offline, R.drawable.doublecheck));
        usersMessages.add(new UsersMessage ("Zaven Khanamiryan", "You: Hi bro!", "14:09",R.drawable.status_online,R.drawable.status_offline, R.drawable.doublecheck));
        usersMessages.add(new UsersMessage ("Zaven Khanamiryan", "You: Hi bro!", "14:09",R.drawable.status_online,R.drawable.status_offline, R.drawable.doublecheck));
        RecyclerView recyclerView = root.findViewById(R.id.users_message_rv);
        // создаем адаптер
        UsersMessageAdapter adapter = new UsersMessageAdapter(root.getContext(), this.usersMessages);
        // устанавливаем для списка адаптер
        recyclerView.setAdapter(adapter);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    private void setInitialData(){


    }

}
