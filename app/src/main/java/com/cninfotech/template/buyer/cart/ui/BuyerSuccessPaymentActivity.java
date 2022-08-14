package com.cninfotech.template.buyer.cart.ui;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.cninfotech.template.R;
import com.cninfotech.template.buyer.dashboard.ui.BuyerDashboardActivity;
import com.cninfotech.template.buyer.product.ui.BuyerParticularCategoryActivity;

import java.util.Objects;

public class BuyerSuccessPaymentActivity extends AppCompatActivity {
    Button success;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_success_payment);

        Window window = getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Checkout");
        toolbar.setTitleTextColor(Color.parseColor("#000000"));
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        success = findViewById(R.id.btnSuccess);
        success.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewFeedBanner();
            }
        });
    }

    private void viewFeedBanner() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        @SuppressLint("InflateParams") View view = Objects.requireNonNull(inflater).inflate(R.layout.alert_buyer_feedback, null);

        final Dialog dialog = new Dialog(this,android.R.style.Theme_Material_Light_Dialog_Alert);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(view);
        dialog.setCancelable(false);
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCanceledOnTouchOutside(false);

        ImageView dismiss = dialog.findViewById(R.id.ivDismiss);
        Button submit = dialog.findViewById(R.id.btnSubmit);

        dismiss.setOnClickListener(view12 -> {
            dialog.dismiss();
            startActivity(new Intent(getApplicationContext(), BuyerDashboardActivity.class));
            finish();
        });

        submit.setOnClickListener(view1 -> {
            dialog.dismiss();
            viewFeedSubmitBanner();
        });

        dialog.show();
    }

    private void viewFeedSubmitBanner() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        @SuppressLint("InflateParams") View view = Objects.requireNonNull(inflater).inflate(R.layout.alert_buyer_feedback_success, null);

        final Dialog dialog = new Dialog(this,android.R.style.Theme_Material_Light_Dialog_Alert);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(view);
        dialog.setCancelable(false);
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCanceledOnTouchOutside(false);

        Button btnContinue = dialog.findViewById(R.id.btnContinue);

        btnContinue.setOnClickListener(view1 -> {
            dialog.dismiss();
            startActivity(new Intent(getApplicationContext(), BuyerDashboardActivity.class));
            finish();
        });

        dialog.show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.overridePendingTransition(R.anim.start_slide_from_left,
                R.anim.exit_slide_to_right);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
