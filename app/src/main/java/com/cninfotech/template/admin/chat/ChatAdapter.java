package com.cninfotech.template.admin.chat;

import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.cninfotech.template.R;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.MyViewHolder> {


    private List<String> adminChatString;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout llChat;
        public TextView message, time;
        CardView profile;

        public MyViewHolder(View view) {
            super(view);
            message = view.findViewById(R.id.tvMessage);
            time = view.findViewById(R.id.tvTimeOfMessage);
            llChat = view.findViewById(R.id.llChat);
            profile = view.findViewById(R.id.cvProfileImage);
        }
    }

    public ChatAdapter(List<String> adminChatString) {
        this.adminChatString = adminChatString;
    }

    @NonNull
    @Override
    public ChatAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_chat_head_left, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ChatAdapter.MyViewHolder holder, int position) {
        holder.message.setText(adminChatString.get(position));
        holder.setIsRecyclable(false);
        if (holder.getAbsoluteAdapterPosition() % 2 == 0) {
            holder.llChat.setGravity(Gravity.START);
            holder.time.setGravity(Gravity.START);
            holder.profile.setVisibility(View.VISIBLE);
        } else {
            holder.time.setGravity(Gravity.END);
            holder.profile.setVisibility(View.GONE);
            holder.llChat.setGravity(Gravity.END);
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) holder.time.getLayoutParams();
            params.gravity = Gravity.END;
            holder.time.setLayoutParams(params);
            holder.message.setBackgroundColor(Color.parseColor("#000000"));
        }
    }

    @Override
    public int getItemCount() {
        return adminChatString.size();
    }
}

