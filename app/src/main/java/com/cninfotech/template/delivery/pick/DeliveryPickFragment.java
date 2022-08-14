package com.cninfotech.template.delivery.pick;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.cninfotech.template.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.skyfishjy.library.RippleBackground;

public class DeliveryPickFragment extends Fragment {
    FloatingActionButton delivery;

    public DeliveryPickFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_delivery_pick, container, false);

        final RippleBackground rippleBackground = view.findViewById(R.id.content);

        rippleBackground.startRippleAnimation();

        delivery = view.findViewById(R.id.fabDelivery);
        delivery.setOnClickListener(view1 -> startActivity(new Intent(getActivity(), DeliveryPickActvity.class)));

        return view;
    }
}
