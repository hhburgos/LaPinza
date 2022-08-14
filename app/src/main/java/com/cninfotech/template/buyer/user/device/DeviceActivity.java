package com.cninfotech.template.buyer.user.device;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.Toolbar;

import com.cninfotech.template.R;

import java.util.Objects;

public class DeviceActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView ivMore;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_device);

        Window window = getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Devices");
        toolbar.setTitleTextColor(Color.parseColor("#000000"));
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        ivMore = findViewById(R.id.image_more);
        ivMore.setOnClickListener(this);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.image_more:

                //creating a popup menu
                PopupMenu popup = new PopupMenu(this, ivMore);
                //inflating menu from xml resource
                popup.inflate(R.menu.menu_options);
                //adding click listener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

                    @SuppressLint("NonConstantResourceId")
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_delete:
                                //handle menu1 click
                                return true;
                            case R.id.action_view:
                                //handle menu2 click
                                return true;
                            default:
                                return false;
                        }
                    }
                });
                popup.show();
                break;
        }
    }
}
