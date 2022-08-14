package com.cninfotech.template.admin.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.cninfotech.template.R;

public class AdminAccountFragment extends Fragment implements View.OnClickListener {
    TextView btnEditprofile;
    LinearLayout btnTermsConditions;
    LinearLayout btnAboutUs;

    public AdminAccountFragment() {
        //Run when the empty instance of the fragment
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin_account, container, false);

        btnTermsConditions = view.findViewById(R.id.llTermsAndCondition);
        btnAboutUs = view.findViewById(R.id.llAboutUs);

        btnEditprofile = view.findViewById(R.id.tvEditProfile);
        btnEditprofile.setOnClickListener(this);
        btnTermsConditions.setOnClickListener(this);
        btnTermsConditions.setOnClickListener(this);
        btnAboutUs.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.llAboutUs:{
                startActivity(new Intent(getContext(), AdminAboutUsActivity.class));
                break;
            }
            case R.id.llTermsAndCondition:{
                startActivity(new Intent(getContext(), AdminTermsConditionActivity.class));
                break;
            }
            case R.id.tvEditProfile:{
                startActivity(new Intent(getContext(), AdminProfileUpdateActvity.class));
                break;
            } default:
                break;
        }
    }
}
