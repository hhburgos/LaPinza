package com.cninfotech.template.admin.product.ui;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cninfotech.template.R;
import com.cninfotech.template.admin.order.ProductImageListAdapter;

import java.util.ArrayList;
import java.util.Objects;

public class AdminInventoryEditActivity extends AppCompatActivity {
    RecyclerView rvOrder;
    ArrayList<Integer> productImgList = new ArrayList<>();
    ProductImageListAdapter productAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_edit_product);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Edit Product");
        toolbar.setTitleTextColor(Color.parseColor("#000000"));
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //Recycler View for WishList
        rvOrder = (RecyclerView) findViewById(R.id.rvProductImageScroller);

        productAdapter = new ProductImageListAdapter(getApplicationContext(), productImgList);
        RecyclerView.LayoutManager mLayoutManager =
                new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        rvOrder.setLayoutManager(mLayoutManager);
        rvOrder.setItemAnimator(new DefaultItemAnimator());
        rvOrder.setAdapter(productAdapter);

        makeOrder();
    }

    private void makeOrder() {
        productImgList.add(R.drawable.placeholder_large);
        productImgList.add(R.drawable.placeholder_large);
        productImgList.add(R.drawable.placeholder_large);
        productImgList.add(R.drawable.placeholder_large);
        productImgList.add(R.drawable.placeholder_large);
        productAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
