package com.cninfotech.template.startscreen;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.cninfotech.template.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvForgetPassword;
    Button btnLogin;
    Button btnSignUp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        init();
    }

    private void init() {
        tvForgetPassword = findViewById(R.id.tvForgetPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnSignUp = findViewById(R.id.btnSignUp);

        tvForgetPassword.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
        btnSignUp.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tvForgetPassword:{
                startActivity(new Intent(getApplicationContext(),ForgetPasswordActivity.class));
                break;
            }
            case R.id.btnLogin:{
                startActivity(new Intent(getApplicationContext(), UserActivity.class));
                finish();
                break;
            }
            case R.id.btnSignUp:{
                startActivity(new Intent(getApplicationContext(), SignUpActivity.class));
                finish();
                break;
            }
        }
    }
}
