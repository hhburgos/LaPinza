package com.cninfotech.template.startscreen;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import com.cninfotech.template.R;
import com.cninfotech.template.buyer.dashboard.BuyerSlidingImageAdapter;

import java.util.ArrayList;

public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnSignUp;
    private CardView btnFacebook;
    private Button btnLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_welcome);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        init();
    }

    private void init() {
        this.btnFacebook = findViewById(R.id.btnFacebookLogin);
        this.btnLogin = findViewById(R.id.btnLogin);
        this.btnSignUp = findViewById(R.id.btnSignUp);

        this.btnSignUp.setOnClickListener(this);
        this.btnLogin.setOnClickListener(this);
        this.btnFacebook.setOnClickListener(this);

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnFacebookLogin: {
                Toast.makeText(getApplicationContext(), "Facebook login", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.btnSignUp: {
                startActivity(new Intent(getApplicationContext(), SignUpActivity.class));
                break;
            }
            case R.id.btnLogin: {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                break;
            }
        }
    }
}
