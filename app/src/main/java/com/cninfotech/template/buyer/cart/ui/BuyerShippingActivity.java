package com.cninfotech.template.buyer.cart.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.cninfotech.template.R;

import java.util.Objects;

public class BuyerShippingActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView ivLocation;
    ImageView ivProgress1;
    ImageView ivProgress2;
    ImageView ivCard;
    ImageView ivDone;

    Button payment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_shipping);

        Window window = getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        init();
    }

    private void init() {
        payment = findViewById(R.id.btnPayment);
        payment.setOnClickListener(this);

        //Toolbar declaration
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Checkout");
        toolbar.setTitleTextColor(Color.parseColor("#000000"));
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        ivLocation = findViewById(R.id.ivLocation);
        ivProgress1 = findViewById(R.id.ivProgress1);
        ivProgress2 = findViewById(R.id.ivProgress2);
        ivDone = findViewById(R.id.ivSuccess);
        ivCard = findViewById(R.id.ivCard);

        ivLocation.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.colorBlack), android.graphics.PorterDuff.Mode.SRC_IN);
        ivProgress1.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.colorGray), android.graphics.PorterDuff.Mode.SRC_IN);
        ivProgress2.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.colorGray), android.graphics.PorterDuff.Mode.SRC_IN);
        ivDone.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.colorGray), android.graphics.PorterDuff.Mode.SRC_IN);
        ivCard.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.colorGray), android.graphics.PorterDuff.Mode.SRC_IN);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnPayment:{
                startActivity(new Intent(getApplicationContext(), BuyerPaymentActivity.class));
                this.overridePendingTransition(R.anim.start_slide_from_right,
                        R.anim.exit_slide_to_left);
                break;
            }
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
