package com.cninfotech.template.delivery.pick;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cninfotech.template.R;
import com.cninfotech.template.admin.chat.ui.ChatMessageActivity;
import com.cninfotech.template.delivery.dashboard.DeliveryProductDetailAdapter;
import com.cninfotech.template.model.AdminProduct;

import java.util.ArrayList;
import java.util.Objects;

public class DeliveryAcceptActivity extends AppCompatActivity {
    CardView btnBuyerChat;
    Button btnPicked;

    RecyclerView rvOrder;
    ArrayList<AdminProduct> deliveryProductList = new ArrayList<>();
    DeliveryProductDetailAdapter productAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_pick_accept);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Delivery Task");
        toolbar.setTitleTextColor(Color.parseColor("#000000"));
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.inflateMenu(R.menu.menu_delivery_product_info);

        btnPicked = findViewById(R.id.btnPicked);
        btnPicked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),DeliveryAcceptPickActivity.class));
                finish();
            }
        });
        btnBuyerChat = findViewById(R.id.cvBuyerChat);
        btnBuyerChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ChatMessageActivity.class);
                intent.putExtra("Username", "Seller");
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_delivery_product_info, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // action with ID action_refresh was selected
            case R.id.action_info:
                viewProductInfo();
                break;
            case android.R.id.home:
                onBackPressed();
                break;
            default:
                break;
        }
        return true;
    }
    private void viewProductInfo() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        @SuppressLint("InflateParams") View view = Objects.requireNonNull(inflater).inflate(R.layout.alert_dialog_delivery_product_info, null);

        final Dialog dialog = new Dialog(this,android.R.style.Theme_Material_Light_Dialog_Alert);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(view);
        dialog.setCancelable(true);
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCanceledOnTouchOutside(true);

        RecyclerView dismiss = dialog.findViewById(R.id.rvDeliveryProductInfo);
        ImageView btnClose = dialog.findViewById(R.id.btnClose);

        rvOrder = (RecyclerView) view.findViewById(R.id.rvDeliveryProductInfo);

        productAdapter = new DeliveryProductDetailAdapter(getApplicationContext(), deliveryProductList);
        RecyclerView.LayoutManager mLayoutManager =
                new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        rvOrder.setLayoutManager(mLayoutManager);
        rvOrder.setItemAnimator(new DefaultItemAnimator());
        rvOrder.setAdapter(productAdapter);
        makeOrder();

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void makeOrder() {
        deliveryProductList.clear();
        AdminProduct product = new AdminProduct("Adidas Boots","$70.5","3 items",R.drawable.placeholder_large);
        deliveryProductList.add(product);
        product = new AdminProduct("T-Shirt","$50.5","1 item",R.drawable.placeholder_large);
        deliveryProductList.add(product);
        product = new AdminProduct("Sweat Shirt","$20.5","4 items",R.drawable.placeholder_large);
        deliveryProductList.add(product);
        product = new AdminProduct("Demin Pant","$40.5","2 items",R.drawable.placeholder_large);
        deliveryProductList.add(product);
        product = new AdminProduct("Nike Hoddy","$62.5","3 items",R.drawable.placeholder_large);
        deliveryProductList.add(product);
        product = new AdminProduct("Caliber Shoes","$11","7 items",R.drawable.placeholder_large);
        deliveryProductList.add(product);
        productAdapter.notifyDataSetChanged();
    }
}
