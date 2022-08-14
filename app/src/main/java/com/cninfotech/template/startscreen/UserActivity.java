package com.cninfotech.template.startscreen;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.cninfotech.template.R;
import com.cninfotech.template.admin.dashboard.AdminMainActivity;
import com.cninfotech.template.buyer.dashboard.ui.BuyerDashboardActivity;
import com.cninfotech.template.delivery.DeliveryMainActivity;

public class UserActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        Button buyer = findViewById(R.id.btnBuyer);
        Button admin = findViewById(R.id.btnAdmin);
        Button delivery = findViewById(R.id.btnDelivery);

        buyer.setOnClickListener(this);
        admin.setOnClickListener(this);
        delivery.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnAdmin:{
                Intent intent = new Intent(getApplicationContext(), AdminMainActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.btnBuyer:{
                Intent intent = new Intent(getApplicationContext(), BuyerDashboardActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.btnDelivery:{
                Intent intent = new Intent(getApplicationContext(), DeliveryMainActivity.class);
                startActivity(intent);
                break;
            }
        }
    }
}
