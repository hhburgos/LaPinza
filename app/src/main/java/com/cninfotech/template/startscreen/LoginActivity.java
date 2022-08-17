package com.cninfotech.template.startscreen;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.cninfotech.template.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tvForgetPassword;
    private Button btnLogin;
    private Button btnSignUp;
    private EditText emailEt, passwordEt;

    private AwesomeValidation validation;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        init();

        setValidations();
    }

    private void init() {
        this.tvForgetPassword = findViewById(R.id.tvForgetPassword);
        this.btnLogin = findViewById(R.id.btnLogin);
        this.btnSignUp = findViewById(R.id.btnSignUp);
        this.emailEt = findViewById(R.id.email_login);
        this.passwordEt = findViewById(R.id.password_login);

        this.tvForgetPassword.setOnClickListener(this);
        this.btnLogin.setOnClickListener(this);
        this.btnSignUp.setOnClickListener(this);

        this.validation = new AwesomeValidation(ValidationStyle.BASIC);
        this.firebaseAuth = FirebaseAuth.getInstance();
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
                login();
                break;
            }
            case R.id.btnSignUp:{
                startActivity(new Intent(getApplicationContext(), SignUpActivity.class));
                finish();
                break;
            }
        }
    }

    private void setValidations() {
        this.validation.addValidation(LoginActivity.this, R.id.email_login, Patterns.EMAIL_ADDRESS, R.string.login_email);
        this.validation.addValidation(LoginActivity.this, R.id.password_login, ".{6,}", R.string.login_password_lenght);
    }

    private void login() {
        //Check fields is valid
        if (!this.validation.validate()) {
            Toast.makeText(LoginActivity.this, getString(R.string.login_valid).toLowerCase(), Toast.LENGTH_SHORT).show();
            return;
        }

        String email = this.emailEt.getText().toString();
        String password = this.passwordEt.getText().toString();

        this.firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful()) {
                    Toast.makeText(LoginActivity.this, getString(R.string.login_error), Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent window = new Intent(LoginActivity.this, UserActivity.class);
                startActivity(window);
                finish();
            }
        });
    }
}
