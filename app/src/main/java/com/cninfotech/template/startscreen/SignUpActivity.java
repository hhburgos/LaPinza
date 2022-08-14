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

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnSignUp;
    Button btnLogIn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_signup);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        btnSignUp = findViewById(R.id.btnSignUp);
        btnLogIn = findViewById(R.id.btnLogin);

        btnSignUp.setOnClickListener(this);
        btnLogIn.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnLogin:{
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                finish();
                break;
            }
            case R.id.btnSignUp:{
                startActivity(new Intent(getApplicationContext(), UserActivity.class));
                finish();
                break;
            }
        }
    }
}
