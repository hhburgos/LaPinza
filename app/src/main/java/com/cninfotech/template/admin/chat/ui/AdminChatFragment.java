package com.cninfotech.template.admin.chat.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cninfotech.template.R;
import com.cninfotech.template.admin.chat.ChatListAdapter;
import com.cninfotech.template.model.ChatMessage;

import java.util.ArrayList;

public class AdminChatFragment extends Fragment {

    RecyclerView rvChatList;
    ArrayList<ChatMessage> chatMessageArrayList = new ArrayList<>();
    ChatListAdapter chatAdapter;

    public AdminChatFragment() {
        //Called When initialized
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin_chat_message_list, container, false);
//Recycler View for Chat
        rvChatList = (RecyclerView) view.findViewById(R.id.rvChatMessage);

        chatAdapter = new ChatListAdapter(getActivity(), chatMessageArrayList);
        RecyclerView.LayoutManager mLayoutManager =
                new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rvChatList.setLayoutManager(mLayoutManager);
        rvChatList.setItemAnimator(new DefaultItemAnimator());
        rvChatList.setAdapter(chatAdapter);

        makeChatList();
        return view;
    }

    private void makeChatList() {
        ChatMessage chatMessage = new ChatMessage("Adam Johnson", "Hey can you please help me out with my delivery I was wondering if you can delivery it today please.", "Now",true);
        chatMessageArrayList.add(chatMessage);
        chatMessage = new ChatMessage("Richard Rorger", "Hey can you please help me out with my delivery I was wondering if you can delivery it today please.", "2 min ago",true);
        chatMessageArrayList.add(chatMessage);
        chatMessage = new ChatMessage("Drake James", "Hey can you please help me out with my delivery I was wondering if you can delivery it today please.", "5 min ago",false);
        chatMessageArrayList.add(chatMessage);
        chatMessage = new ChatMessage("Ellie Jackson", "Hey can you please help me out with my delivery I was wondering if you can delivery it today please.", "1 hr ago",true);
        chatMessageArrayList.add(chatMessage);
        chatMessage = new ChatMessage("Roger Wayne", "Hey can you please help me out with my delivery I was wondering if you can delivery it today please.", "Yesterday",false);
        chatMessageArrayList.add(chatMessage);
        chatAdapter.notifyDataSetChanged();
    }


}
