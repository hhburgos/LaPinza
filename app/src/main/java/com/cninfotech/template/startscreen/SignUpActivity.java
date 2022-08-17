package com.cninfotech.template.startscreen;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.cninfotech.template.R;
import com.cninfotech.template.model.FinalVar;
import com.cninfotech.template.model.Usuario;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.UUID;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnSignUp;
    private Button btnLogIn;
    private EditText nameEt, emailEt, akaEt, passwordEt;
    private AwesomeValidation validation;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference userDb;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_signup);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        this.firebaseDatabase = FirebaseDatabase.getInstance(FinalVar.URL_DATABASE);
        this.firebaseAuth = FirebaseAuth.getInstance();

        this.userDb = this.firebaseDatabase.getReference().child(FinalVar.DATABASE_NAME);

        this.validation = new AwesomeValidation(ValidationStyle.BASIC);

        setValidations();

        this.btnSignUp = findViewById(R.id.btnSignUp);
        this.btnLogIn = findViewById(R.id.btnLogin);

        this.nameEt = findViewById(R.id.name_register);
        this.emailEt = findViewById(R.id.email_register);
        this.passwordEt = findViewById(R.id.password_register);
        this.akaEt = findViewById(R.id.aka_register);

        this.btnSignUp.setOnClickListener(this);
        this.btnLogIn.setOnClickListener(this);
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
                registerUser();
                break;
            }
        }
    }

    private void setValidations() {
        this.validation.addValidation(SignUpActivity.this, R.id.email_register, Patterns.EMAIL_ADDRESS, R.string.register_valid_email);
        this.validation.addValidation(SignUpActivity.this, R.id.name_register, ".{2,}", R.string.register_valid_name);
        this.validation.addValidation(SignUpActivity.this, R.id.password_register, ".{6,}", R.string.register_valid_password);
        this.validation.addValidation(SignUpActivity.this, R.id.aka_register, ".{2,}", R.string.register_valid_aka);
    }

    private void registerUser() {
        //Check the fields
        if (!this.validation.validate()) {
            Toast.makeText(SignUpActivity.this, getString(R.string.register_valid), Toast.LENGTH_SHORT).show();
            return;
        }

        String id = UUID.randomUUID().toString();
        String nombre = this.nameEt.getText().toString();
        String aka = this.akaEt.getText().toString();
        String email = this.emailEt.getText().toString().toLowerCase();
        String password = this.passwordEt.getText().toString();

        Usuario usuario = new Usuario(aka, email, id,nombre,password);

        //Check the emails not insert
        Query query = this.userDb.child(FinalVar.TABLE_NAME_USUARIOS)
                .orderByChild(FinalVar.ROW_NAME_EMAIL)
                .equalTo(email);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    Toast.makeText(SignUpActivity.this, getString(R.string.register_email_exist), Toast.LENGTH_SHORT).show();
                    return;
                }

                userDb.child(FinalVar.TABLE_NAME_USUARIOS).child(usuario.getId()).setValue(usuario);
                //Create firebase auth
                firebaseAuth.createUserWithEmailAndPassword(usuario.getEmail(), usuario.getPassword());

                Toast.makeText(SignUpActivity.this, getString(R.string.register_ok), Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}
