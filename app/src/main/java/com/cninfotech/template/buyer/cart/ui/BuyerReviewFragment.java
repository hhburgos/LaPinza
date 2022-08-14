package com.cninfotech.template.buyer.cart.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.cninfotech.template.R;
import com.cninfotech.template.buyer.product.ui.BuyerProductReviewActivity;

public class BuyerReviewFragment extends Fragment {
    TextView tvReview;

    public BuyerReviewFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_buyer_review, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvReview = view.findViewById(R.id.text_review);
        tvReview.setOnClickListener(view1 -> startActivity(new Intent(getActivity(), BuyerProductReviewActivity.class)));
    }
}
