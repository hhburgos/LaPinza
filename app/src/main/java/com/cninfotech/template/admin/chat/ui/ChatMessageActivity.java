package com.cninfotech.template.admin.chat.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cninfotech.template.R;
import com.cninfotech.template.admin.chat.ChatAdapter;

import java.util.ArrayList;
import java.util.Objects;

public class ChatMessageActivity extends AppCompatActivity {
    ImageView ivSendl;
    EditText message;
    RecyclerView rvChat;
    ArrayList<String> chatString = new ArrayList<>();
    ChatAdapter chatAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_chat);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(getIntent().getStringExtra("Username"));
        toolbar.setTitleTextColor(Color.parseColor("#000000"));
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //Recycler View for Chat
        rvChat = (RecyclerView) findViewById(R.id.rvChat);

        chatAdapter = new ChatAdapter(chatString);
        LinearLayoutManager mLayoutManager =
                new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);

        mLayoutManager.setStackFromEnd(true);
        rvChat.setLayoutManager(mLayoutManager);
        rvChat.setItemAnimator(new DefaultItemAnimator());
        rvChat.setAdapter(chatAdapter);

        makeChat();

        ivSendl = findViewById(R.id.btnSendMessage);
        message = findViewById(R.id.etMessage);

        ivSendl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!message.getText().toString().matches("")){
                    chatString.add(message.getText().toString());
                    chatAdapter.notifyDataSetChanged();
                    rvChat.smoothScrollToPosition(chatString.size()-1);
                    message.setText("");
                }
            }
        });
    }
    private void makeChat() {
        chatString.add("Hi");
        chatString.add("Hello");
        chatString.add("When can I get my order");
        chatString.add("You will receive it within 2-3 days");
        chatString.add("OK sure Thank you");
        chatString.add("No problem");
        chatAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
