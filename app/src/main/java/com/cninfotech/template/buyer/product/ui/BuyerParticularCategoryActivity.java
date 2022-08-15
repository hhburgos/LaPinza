package com.cninfotech.template.buyer.product.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cninfotech.template.R;
import com.cninfotech.template.buyer.cart.ui.BuyerCartActivity;
import com.cninfotech.template.buyer.cart.ui.BuyerEmptyCartActivity;
import com.cninfotech.template.buyer.dashboard.BuyerBrandAdapter;
import com.cninfotech.template.buyer.product.BuyerProductListAdapter;
import com.cninfotech.template.model.Product;
import com.cninfotech.template.buyer.product.BuyerProductAdapter;
import com.crystal.crystalrangeseekbar.interfaces.OnRangeSeekbarChangeListener;
import com.crystal.crystalrangeseekbar.widgets.CrystalRangeSeekbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BuyerParticularCategoryActivity extends AppCompatActivity implements View.OnClickListener {
    private List<String> brandList = new ArrayList<>();
    private List<Product> productList = new ArrayList<>();

    private RecyclerView rvDesignerCollection;
    private BuyerProductAdapter productAdapter;
    private BuyerProductListAdapter productListAdapter;

    ImageView gridview;
    ImageView verticalView;

    Toolbar toolbar;
    DrawerLayout filter;
    CrystalRangeSeekbar crystalRangeSeekbar;

    private Button[] btn = new Button[5];
    private Button btn_unfocus;
    private int[] btn_id = {R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4,R.id.btn5};

    TextView min,max;
    RecyclerView rvBrand;
    BuyerBrandAdapter brandAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_category);

        Window window = getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        init();

    }

    private void init() {
        filter = findViewById(R.id.drawer_layout);
        rvDesignerCollection = (RecyclerView) findViewById(R.id.rvParticularCategory);
        gridview = findViewById(R.id.ivCategoryGrid);
        verticalView = findViewById(R.id.ivCategoryVertical);

        gridview.setOnClickListener(this);
        verticalView.setOnClickListener(this);

        //Toolbar declaration
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //Brand recycler view
        rvBrand = (RecyclerView) findViewById(R.id.rvBrand);

        brandAdapter = new BuyerBrandAdapter(getApplicationContext(),brandList);
        RecyclerView.LayoutManager mLayoutManager =
                new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        rvBrand.setLayoutManager(mLayoutManager);
        rvBrand.setItemAnimator(new DefaultItemAnimator());
        rvBrand.setAdapter(brandAdapter);
        makeBrand();

        setCategorySort(true);
        topTrending();
        filterActivity();
    }

    private void filterActivity() {
        for(int i = 0; i < btn.length; i++){
            btn[i] = (Button) findViewById(btn_id[i]);
            btn[i].setBackgroundColor(Color.rgb(207, 207, 207));
            btn[i].setOnClickListener(this);
        }

        btn_unfocus = btn[0];
        min = findViewById(R.id.tvMin);
        max = findViewById(R.id.tvMax);
        crystalRangeSeekbar = findViewById(R.id.rangeSeekbar1);

        crystalRangeSeekbar.setOnRangeSeekbarChangeListener(new OnRangeSeekbarChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void valueChanged(Number minValue, Number maxValue) {
                min.setText("$"+ minValue);
                max.setText("$"+ maxValue);
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ivCategoryGrid:{
                setCategorySort(true);
                break;
            }
            case R.id.ivCategoryVertical:{
                setCategorySort(false);
                break;
            }
            case R.id.btn1 :
                setFocus(btn_unfocus, btn[0]);
                break;

            case R.id.btn2 :
                setFocus(btn_unfocus, btn[1]);
                break;

            case R.id.btn3 :
                setFocus(btn_unfocus, btn[2]);
                break;

            case R.id.btn4 :
                setFocus(btn_unfocus, btn[3]);
                break;

            case R.id.btn5 :
                setFocus(btn_unfocus, btn[4]);
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_buyer_product, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_cart:
                startActivity(new Intent(getApplicationContext(), BuyerEmptyCartActivity.class));
                return true;
            case R.id.action_filter:
                if(!filter.isDrawerOpen(GravityCompat.END)) filter.openDrawer(GravityCompat.END);
                else filter.closeDrawer(GravityCompat.END);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void makeBrand() {
        brandList.clear();
        brandList.add("Goldstar");
        brandList.add("Vans");
        brandList.add("Caliber");
        brandList.add("Woodland");
        brandList.add("Supra");
        brandList.add("DC");
        brandAdapter.notifyDataSetChanged();
    }

    private void topTrending() {
        productList.clear();
        Product product = new Product("Lokita Both Side Winter Fleece Zipper Jacket For Women's By Rc", R.drawable.placeholder_large, "$34.99","#808080","$30.99","-10%",3);
        productList.add(product);
        product = new Product("Aamayra Fashion House Cream Woolen Kurti For Women", R.drawable.placeholder_large, "$14.99","#808080","","",4.5f);
        productList.add(product);
        product = new Product("Nyptra Black Grey Premium Oversize Swed Fur Bomber Jacket For Women", R.drawable.placeholder_large, "$24.99","#808080","$20.99","-10%",3.5f);
        productList.add(product);
        product = new Product("New Women Hollo-pine Ultra Light Duck Jacket", R.drawable.placeholder_large, "$24.99","#808080","$20.99","-10%",2.5f);
        productList.add(product);
        product = new Product("Cashmere Embroidered Acrylic Pashmina Shawl For Women", R.drawable.placeholder_large, "$24.99","#808080","$20.99","-10%",0.5f);
        productList.add(product);
        product = new Product("INS Style Ultra-Thin Black Light Leg Stockings", R.drawable.placeholder_large, "$24.99","#808080","$20.99","-10%",1.5f);
        productList.add(product);
        product = new Product("Cotton Printed 3 Pcs Combo Set T-Shirt For Women", R.drawable.placeholder_large, "$24.99","#808080","$20.99","-10%",5f);
        productList.add(product);

        productAdapter.notifyDataSetChanged();
    }

    public void setCategorySort(boolean view){
        if (view){
            gridview.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.colorTheme), android.graphics.PorterDuff.Mode.SRC_IN);
            verticalView.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.colorGrey), android.graphics.PorterDuff.Mode.SRC_IN);

            productAdapter = new BuyerProductAdapter(BuyerParticularCategoryActivity.this, productList);
            rvDesignerCollection.setLayoutManager(new GridLayoutManager(this, 2));
            rvDesignerCollection.setItemAnimator(new DefaultItemAnimator());
            rvDesignerCollection.setAdapter(productAdapter);
            topTrending();
        }else{
            gridview.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.colorGrey), android.graphics.PorterDuff.Mode.SRC_IN);
            verticalView.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.colorTheme), android.graphics.PorterDuff.Mode.SRC_IN);

            productListAdapter = new BuyerProductListAdapter(BuyerParticularCategoryActivity.this, productList);
            RecyclerView.LayoutManager mLayoutManager =
                    new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
            rvDesignerCollection.setLayoutManager(mLayoutManager);
            rvDesignerCollection.setItemAnimator(new DefaultItemAnimator());
            rvDesignerCollection.setAdapter(productListAdapter);
            topTrending();
        }
    }

    private void setFocus(Button btn_unfocus, Button btn_focus){
        btn_unfocus.setTextColor(Color.rgb(49, 50, 51));
        btn_unfocus.setBackgroundColor(Color.rgb(207, 207, 207));
        btn_focus.setTextColor(Color.rgb(255, 255, 255));
        btn_focus.setBackgroundColor(Color.rgb(3, 106, 150));
        this.btn_unfocus = btn_focus;
    }

}
