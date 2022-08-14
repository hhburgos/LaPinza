package com.cninfotech.template.buyer.product.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.cninfotech.template.R;
import com.cninfotech.template.buyer.cart.ui.BuyerCartActivity;
import com.cninfotech.template.buyer.dashboard.BuyerFeatureImageAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class BuyerSizeSelectorActivity extends AppCompatActivity implements View.OnClickListener {

    private ArrayList<Integer> ImagesArray = new ArrayList<>();
    private static final Integer[] IMAGES = {R.drawable.placeholder_wide, R.drawable.placeholder_wide, R.drawable.placeholder_wide};

    private Button[] btn = new Button[5];
    private Button btn_unfocus;
    private int[] btn_id = {R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_size_selector);

        init();
    }

    private void init() {
        //Toolbar declaration
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Hoody Blue");
        toolbar.setTitleTextColor(Color.parseColor("#000000"));
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        ViewPager vpFeatureImage = (ViewPager) findViewById(R.id.vpFeaturedImage);

        ImagesArray.addAll(Arrays.asList(IMAGES));
        vpFeatureImage.setAdapter(new BuyerFeatureImageAdapter(getApplicationContext(), ImagesArray));

        for (int i = 0; i < btn.length; i++) {
            btn[i] = (Button) findViewById(btn_id[i]);
            btn[i].setBackgroundColor(Color.rgb(207, 207, 207));
            btn[i].setOnClickListener(this);
        }

        btn_unfocus = btn[0];
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                setFocus(btn_unfocus, btn[0]);
                break;

            case R.id.btn2:
                setFocus(btn_unfocus, btn[1]);
                break;

            case R.id.btn3:
                setFocus(btn_unfocus, btn[2]);
                break;

            case R.id.btn4:
                setFocus(btn_unfocus, btn[3]);
                break;

            case R.id.btn5:
                setFocus(btn_unfocus, btn[4]);
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_buyer_category, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_cart:
                startActivity(new Intent(getApplicationContext(), BuyerCartActivity.class));
                return true;

            default:
                break;
        }

        return false;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void setFocus(Button btn_unfocus, Button btn_focus) {
        btn_unfocus.setTextColor(Color.rgb(49, 50, 51));
        btn_unfocus.setBackgroundColor(Color.rgb(207, 207, 207));
        btn_focus.setTextColor(Color.rgb(255, 255, 255));
        btn_focus.setBackgroundColor(Color.rgb(3, 106, 150));
        this.btn_unfocus = btn_focus;
    }

}
