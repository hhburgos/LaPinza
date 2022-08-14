package com.cninfotech.template.buyer.cart.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cninfotech.template.R;
import com.cninfotech.template.buyer.cart.BuyerProductCartAdapter;
import com.cninfotech.template.model.Product;

import java.util.ArrayList;
import java.util.Objects;

public class BuyerPaymentActivity extends AppCompatActivity implements View.OnClickListener {

    Button pay;
    ImageView ivLocation;
    ImageView ivProgress1;
    ImageView ivProgress2;
    ImageView ivCard;
    ImageView ivDone;

    CardView llCash;
    CardView llVisa;
    CardView llNetBank;
    CardView llPaypal;

    RecyclerView rvCartItems;
    BuyerProductCartAdapter buyerProductCartAdapter;

    ArrayList<Product> products = new ArrayList<>();

    public static int defaultValue = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_payment);

        Window window = getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        init();
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnPay: {
                startActivity(new Intent(getApplicationContext(), BuyerSuccessPaymentActivity.class));
                this.overridePendingTransition(R.anim.start_slide_from_right,
                        R.anim.exit_slide_to_left);
                break;
            }
            case R.id.linear_cash:
                defaultValue = 1;
                checkDefault();
                Toast.makeText(getApplicationContext(), "Cash on delivery", Toast.LENGTH_SHORT).show();
                break;

            case R.id.linear_paypal:
                defaultValue = 4;
                checkDefault();
                Toast.makeText(getApplicationContext(), "Paypal", Toast.LENGTH_SHORT).show();
                break;

            case R.id.linear_visa:
                defaultValue = 2;
                checkDefault();
                Toast.makeText(getApplicationContext(), "Visa", Toast.LENGTH_SHORT).show();
                break;

            case R.id.linear_net_banking:
                defaultValue = 3;
                checkDefault();
                Toast.makeText(getApplicationContext(), "Net Banking", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public void checkDefault() {
        if (defaultValue == 1) {
            llCash.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.colorThemeFaded));
            llPaypal.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.colorGrey1));
            llNetBank.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.colorGrey1));
            llVisa.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.colorGrey1));
        } else if (defaultValue == 2) {
            llVisa.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.colorThemeFaded));
            llPaypal.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.colorGrey1));
            llNetBank.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.colorGrey1));
            llCash.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.colorGrey1));
        } else if (defaultValue == 3) {
            llNetBank.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.colorThemeFaded));
            llPaypal.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.colorGrey1));
            llCash.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.colorGrey1));
            llVisa.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.colorGrey1));
        } else if (defaultValue == 4) {
            llPaypal.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.colorThemeFaded));
            llCash.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.colorGrey1));
            llNetBank.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.colorGrey1));
            llVisa.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(),R.color.colorGrey1));
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        this.overridePendingTransition(R.anim.start_slide_from_left,
                R.anim.exit_slide_to_right);
    }

    private void init() {

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
        pay = findViewById(R.id.btnPay);

        pay.setOnClickListener(this);

        ivLocation.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.colorBlack), android.graphics.PorterDuff.Mode.SRC_IN);
        ivProgress1.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.colorBlack), android.graphics.PorterDuff.Mode.SRC_IN);
        ivProgress2.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.colorGray), android.graphics.PorterDuff.Mode.SRC_IN);
        ivDone.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.colorGray), android.graphics.PorterDuff.Mode.SRC_IN);
        ivCard.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.colorBlack), android.graphics.PorterDuff.Mode.SRC_IN);

        rvCartItems = findViewById(R.id.rvCartItems);
        rvCartItems.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false));

        buyerProductCartAdapter = new BuyerProductCartAdapter(getApplicationContext(), products);
        rvCartItems.setAdapter(buyerProductCartAdapter);

        llCash = findViewById(R.id.linear_cash);
        llNetBank = findViewById(R.id.linear_net_banking);
        llVisa = findViewById(R.id.linear_visa);
        llPaypal = findViewById(R.id.linear_paypal);

        llCash.setOnClickListener(this);
        llNetBank.setOnClickListener(this);
        llVisa.setOnClickListener(this);
        llPaypal.setOnClickListener(this);

        makeCartItems();
    }

    @SuppressLint("NotifyDataSetChanged")
    void makeCartItems() {
        products.clear();
        Product product = new Product(
                "Both Side Winter Fleece Zipper Jacket For Women's By Rc",
                R.drawable.placeholder_small,
                "$30.23",
                "4"
        );
        products.add(product);

        product = new Product(
                "Nyptra Black Grey Premium Oversize Swed Fur Bomber Jacket For Women",
                R.drawable.placeholder_small,
                "$90.23",
                "8"
        );
        products.add(product);

        product = new Product(
                "Aamayra Fashion House Cream Woolen Kurti For Women",
                R.drawable.placeholder_small,
                "$69.23",
                "1"
        );
        products.add(product);

        buyerProductCartAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
