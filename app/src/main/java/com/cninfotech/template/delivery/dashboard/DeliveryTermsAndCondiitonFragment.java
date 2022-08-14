package com.cninfotech.template.delivery.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.cninfotech.template.R;

public class DeliveryTermsAndCondiitonFragment extends Fragment {

    public DeliveryTermsAndCondiitonFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_delivery_terms_and_condition, container, false);
    }
}
