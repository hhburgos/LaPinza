package com.cninfotech.template.buyer.cart.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cninfotech.template.R;
import com.cninfotech.template.buyer.cart.BuyerBagAdapter;
import com.cninfotech.template.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BuyerEmptyCartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_empty_cart);

        init();
    }

    private void init() {

        //Toolbar declaration
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("My Bag");
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.parseColor("#000000"));
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Button start = findViewById(R.id.btnStart);
        start.setOnClickListener(view -> onBackPressed());

    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


}
