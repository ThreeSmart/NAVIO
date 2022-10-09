package com.example.navio.ui.messages;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.navio.R;
import com.example.navio.ui.messages.util.UsersMessage;

import java.util.List;

public class UsersMessageAdapter  extends RecyclerView.Adapter<UsersMessageAdapter.ViewHolder>{

    private final LayoutInflater inflater;
    private final List<UsersMessage> UsersMessage;

    UsersMessageAdapter(Context context, List<UsersMessage> UsersMessage) {
        this.UsersMessage = UsersMessage;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public UsersMessageAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.item_users_message, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UsersMessageAdapter.ViewHolder holder, int position) {
        UsersMessage usersMessage = UsersMessage.get(position);
        holder.user_imgView.setImageResource(usersMessage.getUserImgResource());
        holder.statusView.setImageResource(usersMessage.getUserStatus());
        holder.seenView.setImageResource(usersMessage.getSeenStatus());
        holder.nameView.setText(usersMessage.getName());
        holder.last_msgView.setText(usersMessage.getLastMsg());
        holder.msgtimeView.setText(usersMessage.getMsgTime());
    }

    @Override
    public int getItemCount() {
        return UsersMessage.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView user_imgView, statusView, seenView;
        final TextView nameView, last_msgView, msgtimeView;
        ViewHolder(View view){
            super(view);
            user_imgView = view.findViewById(R.id.user_img);
            statusView = view.findViewById(R.id.user_status);
            nameView = view.findViewById(R.id.name);
            last_msgView = view.findViewById(R.id.last_msg);
            seenView = view.findViewById(R.id.seen_status);
            msgtimeView=view.findViewById(R.id.msg_time);
        }
    }
}