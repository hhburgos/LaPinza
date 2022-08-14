package com.cninfotech.template.delivery.pick;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.cninfotech.template.R;

public class DeliverySuccessActivity extends AppCompatActivity {
    Button btnSuccess;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_product_delivered);
        btnSuccess = findViewById(R.id.btnHome);
        btnSuccess.setOnClickListener(view -> finish());
    }
}
