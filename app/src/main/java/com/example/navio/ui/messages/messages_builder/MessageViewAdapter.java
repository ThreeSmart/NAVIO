package com.example.navio.ui.messages.messages_builder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.navio.R;
import com.example.navio.backend.model.User;
import com.example.navio.backend.service.AuthenticationService;
import com.example.navio.ui.messages.util.Message;

import java.text.DateFormat;
import java.util.ArrayList;

public class MessageViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private final Context context;
    ArrayList<Message> list;
    public static final int MESSAGE_TYPE_IN = 0;
    public static final int MESSAGE_TYPE_OUT = 1;

    public MessageViewAdapter(final Context context,final ArrayList<Message> list) {
        this.context = context;
        this.list = list;
    }

    private class MessageInViewHolder extends RecyclerView.ViewHolder {

        TextView messageContentTV,dateTV;
        MessageInViewHolder(final View itemView) {
            super(itemView);
            messageContentTV = itemView.findViewById(R.id.message_text);
            dateTV = itemView.findViewById(R.id.date_text);
        }
        void bind(int position) {
            Message message = list.get(position);
            messageContentTV.setText(message.getContent());
            dateTV.setText(DateFormat.getTimeInstance(DateFormat.SHORT).format(Long.valueOf(message.getSentTime())));
        }
    }

    private class MessageOutViewHolder extends RecyclerView.ViewHolder {

        TextView messageContentTV,dateTV;
        MessageOutViewHolder(final View itemView) {
            super(itemView);
            messageContentTV = itemView.findViewById(R.id.message_text);
            dateTV = itemView.findViewById(R.id.date_text);
        }
        void bind(int position) {
            Message message = list.get(position);
            messageContentTV.setText(message.getContent());
            dateTV.setText(DateFormat.getTimeInstance(DateFormat.SHORT).format(Long.valueOf(message.getSentTime())));
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == MESSAGE_TYPE_IN) {
            return new MessageInViewHolder(LayoutInflater.from(context).inflate(R.layout.item_message_in, parent, false));
        }
        return new MessageOutViewHolder(LayoutInflater.from(context).inflate(R.layout.item_message_out, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getMessageType(position) == MESSAGE_TYPE_IN) {
            ((MessageInViewHolder) holder).bind(position);
        } else {
            ((MessageOutViewHolder) holder).bind(position);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        return getMessageType(position);
    }

    private int getMessageType(int position){
        if (AuthenticationService.getAuthenticatedUser().getId() == list.get(position).getFromUserId()){
            return MESSAGE_TYPE_OUT;
        }
        return MESSAGE_TYPE_IN;
    }
}
