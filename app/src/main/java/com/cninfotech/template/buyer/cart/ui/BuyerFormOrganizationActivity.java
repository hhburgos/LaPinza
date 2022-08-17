package com.cninfotech.template.buyer.cart.ui;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.cninfotech.template.R;
import com.cninfotech.template.buyer.cart.MemberAdapter;
import com.cninfotech.template.model.FinalVar;
import com.cninfotech.template.model.Organizacion;
import com.cninfotech.template.model.Usuario;
import com.cninfotech.template.startscreen.SignUpActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.regex.Pattern;

public class BuyerFormOrganizationActivity extends AppCompatActivity {
    private List<Usuario> miembros;
    private EditText nameEt, streetEt;
    private AwesomeValidation validation;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference userDb;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organization_form);

        //Init list
        init();

        LinearLayout lvMemberAlert = findViewById(R.id.llMembers);
        lvMemberAlert.setOnClickListener(v -> initSearchMembersView());

        Button btnCreateOrganization = findViewById(R.id.btnCreateOrganization);
        btnCreateOrganization.setOnClickListener(v -> createOrganization());

        setValidations();
    }

    private void init() {
        this.miembros = new ArrayList<>();
        this.validation = new AwesomeValidation(ValidationStyle.BASIC);

        this.firebaseDatabase = FirebaseDatabase.getInstance(FinalVar.URL_DATABASE);

        this.userDb = this.firebaseDatabase.getReference().child(FinalVar.DATABASE_NAME);

        this.nameEt = findViewById(R.id.input_organization_name);
        this.streetEt = findViewById(R.id.input_organization_street);
    }

    private void setValidations() {
        this.validation.addValidation(BuyerFormOrganizationActivity.this, R.id.input_organization_name, ".{2,}", R.string.organization_validation_name);
        this.validation.addValidation(BuyerFormOrganizationActivity.this, R.id.input_organization_street, ".{2,}", R.string.organization_validation_street);
    }

    private void initSearchMembersView() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        @SuppressLint("InflateParams") View view = Objects.requireNonNull(inflater).inflate(R.layout.alert_members_add, null);

        final Dialog dialog = new Dialog(this,android.R.style.Theme_Material_Light_Dialog_Alert);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(view);
        dialog.setCancelable(false);
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCanceledOnTouchOutside(true);

        MemberAdapter adapter = new MemberAdapter(BuyerFormOrganizationActivity.this, this.miembros);

        ListView listView = dialog.findViewById(R.id.listMembers);
        listView.setAdapter(adapter);

        ImageView dismiss = dialog.findViewById(R.id.ivDismiss);
        dismiss.setOnClickListener(v -> dialog.dismiss());

        EditText editText = dialog.findViewById(R.id.input_member_add);

        Button btnAdd = dialog.findViewById(R.id.btnAddMember);
        btnAdd.setOnClickListener(v -> {
            String email = editText.getText().toString().toLowerCase();
            final Pattern EMAIL_REGEX = Pattern.compile("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", Pattern.CASE_INSENSITIVE);

            //Check data
            if (!EMAIL_REGEX.matcher(email).matches()) {
                Toast.makeText(BuyerFormOrganizationActivity.this, getString(R.string.member_alert), Toast.LENGTH_SHORT).show();
                return;
            } else if (this.miembros.contains(email)) {
                Toast.makeText(BuyerFormOrganizationActivity.this, getString(R.string.organization_email_repeat), Toast.LENGTH_SHORT).show();
                return;
            }

            Query query = this.userDb.child(FinalVar.TABLE_NAME_USUARIOS)
                    .orderByChild(FinalVar.ROW_NAME_EMAIL)
                    .equalTo(email);

            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (!snapshot.exists()) {
                        Toast.makeText(BuyerFormOrganizationActivity.this, getString(R.string.email_alert_member), Toast.LENGTH_SHORT).show();
                        return;
                    }

                    for (DataSnapshot snapshot1: snapshot.getChildren()) {
                        Usuario usuario = snapshot1.getValue(Usuario.class);
                        miembros.add(usuario);
                    }

                    editText.setText("");

                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        });



        dialog.show();
    }

    private void createOrganization() {
        //Check all data
        if (!this.validation.validate()) {
            Toast.makeText(BuyerFormOrganizationActivity.this, getString(R.string.organization_data_valid), Toast.LENGTH_SHORT).show();
            return;
        } else if (this.miembros.size() < 2){
            Toast.makeText(BuyerFormOrganizationActivity.this, getString(R.string.organization_members_minimun), Toast.LENGTH_SHORT).show();
            return;
        }

        String id = UUID.randomUUID().toString();
        String name = this.nameEt.getText().toString();
        String street = this.streetEt.getText().toString();

        Organizacion organizacion = new Organizacion(id, name, this.miembros, street);

        this.userDb.child(FinalVar.TABLE_NAME_ORGANIZACION).child(organizacion.getId()).setValue(organizacion);
        Toast.makeText(BuyerFormOrganizationActivity.this, getString(R.string.organization_created_ok), Toast.LENGTH_SHORT).show();
        finish();
    }
}
