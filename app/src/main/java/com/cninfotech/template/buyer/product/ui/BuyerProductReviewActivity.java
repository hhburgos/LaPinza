package com.cninfotech.template.buyer.product.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import com.cninfotech.template.R;

import java.util.Objects;

public class BuyerProductReviewActivity extends AppCompatActivity implements View.OnClickListener {
    CardView cvImage;
    Button btnSubmit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_review);

        init();
    }

    private void init() {

        //Toolbar declaration
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Write a review");
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.parseColor("#000000"));
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        cvImage = findViewById(R.id.card_image);
        btnSubmit = findViewById(R.id.button_submit);

        cvImage.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);

    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.card_image:
                Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickPhoto, 101);
                break;
            case R.id.button_submit:
                Toast.makeText(getApplicationContext(), "Submitted", Toast.LENGTH_SHORT).show();
                onBackPressed();
                break;
        }
    }
}
