package com.cninfotech.template.admin.chat;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.cninfotech.template.R;
import com.cninfotech.template.admin.chat.ui.ChatMessageActivity;
import com.cninfotech.template.model.ChatMessage;

import java.util.List;

public class ChatListAdapter extends RecyclerView.Adapter<ChatListAdapter.MyViewHolder> {

    private List<ChatMessage> chatMessageList;
    Context context;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView userName, timeOfMessage, message;
        public ImageView chatStatus;

        public MyViewHolder(View view) {
            super(view);
            userName = view.findViewById(R.id.tvUserName);
            timeOfMessage = view.findViewById(R.id.tvTimeOfMessage);
            message = view.findViewById(R.id.tvMessage);
            chatStatus = view.findViewById(R.id.image_chat_status);
        }
    }

    public ChatListAdapter(Context context, List<ChatMessage> chatMessageList) {
        this.chatMessageList = chatMessageList;
        this.context = context;
    }

    @NonNull
    @Override
    public ChatListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_admin_chat_message, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ChatListAdapter.MyViewHolder holder, int position) {
        final ChatMessage chatMessage = chatMessageList.get(position);
        holder.userName.setText(chatMessage.getUserName());
        holder.timeOfMessage.setText(chatMessage.getTimeOfMessage());
        holder.message.setText(chatMessage.getMessage());

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(context, ChatMessageActivity.class);
            intent.putExtra("Username", chatMessage.getUserName());
            context.startActivity(intent);
        });

        if (chatMessage.isStatus()){
            holder.chatStatus.setColorFilter(ContextCompat.getColor(context,R.color.colorGreen));
        }else{
            holder.chatStatus.setColorFilter(ContextCompat.getColor(context,R.color.colorRed));
        }
    }

    @Override
    public int getItemCount() {
        return chatMessageList.size();
    }
}

