package com.cninfotech.template.buyer.cart.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
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

public class BuyerCartActivity extends AppCompatActivity implements View.OnClickListener {

    private BuyerBagAdapter bagAdapter;
    Button checkOut;

    private final List<Product> productList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_cart);

        Window window = getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

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

        checkOut = findViewById(R.id.btnCheckout);
        checkOut.setOnClickListener(this);

        //Recycler View for WishList
        RecyclerView rvWishlist = findViewById(R.id.rvBag);

        bagAdapter = new BuyerBagAdapter(productList);
        RecyclerView.LayoutManager mLayoutManager =
                new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        rvWishlist.setLayoutManager(mLayoutManager);
        rvWishlist.setItemAnimator(new DefaultItemAnimator());
        rvWishlist.setAdapter(bagAdapter);

        makeWishList();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnCheckout: {
                startActivity(new Intent(getApplicationContext(), BuyerShippingActivity.class));
                break;
            }
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void makeWishList() {
        Product product = new Product("Aamayra Fashion House Cream Woolen Kurti For Women",R.drawable.placeholder_small,"$4.99","M","Grey","$3.99","-10%");
        productList.add(product);
        product = new Product("Both Side Winter Fleece Zipper Jacket For Women's By Rc",R.drawable.placeholder_small,"$5.99","L","White","$3.99","-10%");
        productList.add(product);
        product = new Product("Aamayra Fashion House Cream Woolen Kurti For Women",R.drawable.placeholder_small,"$6.99","S","Black","$3.99","-10%");
        productList.add(product);
        product = new Product("Nyptra Black Grey Premium Oversize Swed Fur Bomber Jacket For Women",R.drawable.placeholder_small,"$8.99","S","Purple","$3.99","-10%");
        productList.add(product);
        product = new Product("Aamayra Fashion House Cream Woolen Kurti For Women",R.drawable.placeholder_small,"$16.99","S","Black","$3.99","-10%");
        productList.add(product);
        product = new Product("Nyptra Black Grey Premium Oversize Swed Fur Bomber Jacket For Women",R.drawable.placeholder_small,"$16.99","XL","Black","$3.99","-10%");
        productList.add(product);
        product = new Product("Both Side Winter Fleece Zipper Jacket For Women's By Rc",R.drawable.placeholder_small,"$16.99","S","Black","$3.99","-10%");
        productList.add(product);
        bagAdapter.notifyDataSetChanged();
    }

}
