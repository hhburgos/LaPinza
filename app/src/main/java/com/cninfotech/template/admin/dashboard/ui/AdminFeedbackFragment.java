package com.cninfotech.template.admin.dashboard.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.cninfotech.template.R;

public class AdminFeedbackFragment extends Fragment {
    TextView  btnSubmit;
    public AdminFeedbackFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin_feedback, container, false);
        btnSubmit = view.findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(view1 -> Toast.makeText(getContext(),"Submitted",Toast.LENGTH_SHORT).show());
        return view;
    }
}
