package com.cninfotech.template.buyer.cart.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.cninfotech.template.R;
import com.cninfotech.template.buyer.cart.AutoCompleteAdapter;
import com.cninfotech.template.buyer.cart.MemberAdapter;
import com.cninfotech.template.model.FinalVar;
import com.cninfotech.template.model.Organizacion;
import com.cninfotech.template.model.Torneo;
import com.cninfotech.template.model.Usuario;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.regex.Pattern;

public class BuyerFormTournamentActivity extends AppCompatActivity {
    private AwesomeValidation validation;
    private EditText tournamentName, formatTournament, locationTournament, dateTournament;
    private LinearLayout linearJudge;
    private LinearLayout linearSpeaker;
    private LinearLayout linearParticipant;

    private List<Usuario> judge;
    private List<Usuario> participants;
    private List<Usuario> speakers;

    private List<Organizacion> organizaciones;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference userDb;

    private AutoCompleteTextView autoCompleteTextView;

    private String fk_organizacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_form_tournament);

        initVariables();

        makeValidations();

    }

    private void initVariables() {
        setOrganizationsAutoComplete();

        this.tournamentName = findViewById(R.id.etNameTournament);
        this.formatTournament = findViewById(R.id.etFormatTournament);
        this.locationTournament = findViewById(R.id.etLocationTournament);
        this.dateTournament = findViewById(R.id.etDayTournament);

        this.validation = new AwesomeValidation(ValidationStyle.BASIC);

        this.judge = new ArrayList<>();
        this.participants = new ArrayList<>();
        this.speakers = new ArrayList<>();

        this.firebaseDatabase = FirebaseDatabase.getInstance(FinalVar.URL_DATABASE);

        this.userDb = this.firebaseDatabase.getReference().child(FinalVar.DATABASE_NAME);

        this.linearJudge = findViewById(R.id.llJudge);
        this.linearJudge.setOnClickListener(v -> makeFloatingWindow(this.judge, R.string.tournament_float_judges_title));

        this.linearSpeaker = findViewById(R.id.llSpeakers);
        this.linearSpeaker.setOnClickListener(v -> makeFloatingWindow(this.speakers, R.string.tournament_float_speaker_title));

        this.linearParticipant = findViewById(R.id.llParticipants);
        this.linearParticipant.setOnClickListener(v -> makeFloatingWindow(this.participants, R.string.tournament_float_participants_title));

        Button btnAdd = findViewById(R.id.btnCreateTournament);
        btnAdd.setOnClickListener(v -> createTournament());
    }

    private void makeValidations() {
        this.validation.addValidation(BuyerFormTournamentActivity.this, R.id.etNameTournament, ".{2,}", R.string.tournament_validation_name);
        this.validation.addValidation(BuyerFormTournamentActivity.this, R.id.etFormatTournament, "([1-9]|1[0-9]|2[0-3])", R.string.tournament_validation_format);
        //this.validation.addValidation(BuyerFormTournamentActivity.this, R.id.etDayTournament, "dd-MM-yyyy HH:mm", R.string.tournament_validation_date);
        this.validation.addValidation(BuyerFormTournamentActivity.this, R.id.etLocationTournament, ".{2,}", R.string.tournament_validation_location);
    }

    private void makeFloatingWindow(List<Usuario> item, int title) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        @SuppressLint("InflateParams") View view = Objects.requireNonNull(inflater).inflate(R.layout.alert_members_add, null);

        final Dialog dialog = new Dialog(this,android.R.style.Theme_Material_Light_Dialog_Alert);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(view);
        dialog.setCancelable(false);
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCanceledOnTouchOutside(true);

        MemberAdapter adapter = new MemberAdapter(BuyerFormTournamentActivity.this, item);

        TextView textView = dialog.findViewById(R.id.title);
        textView.setText(getString(title));

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
                Toast.makeText(BuyerFormTournamentActivity.this, getString(R.string.member_alert), Toast.LENGTH_SHORT).show();
                return;
            } else if (compareList(new List[]{this.speakers, this.judge, this.participants}, email) ) {
                Toast.makeText(BuyerFormTournamentActivity.this, getString(R.string.tournament_alert_compare), Toast.LENGTH_SHORT).show();
                return;
            }

            Query query = this.userDb.child(FinalVar.TABLE_NAME_USUARIOS)
                    .orderByChild(FinalVar.ROW_NAME_EMAIL)
                    .equalTo(email);

            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (!snapshot.exists()) {
                        Toast.makeText(BuyerFormTournamentActivity.this, getString(R.string.email_alert_member), Toast.LENGTH_SHORT).show();
                        return;
                    }

                    for (DataSnapshot snapshot1: snapshot.getChildren()) {
                        Usuario usuario = snapshot1.getValue(Usuario.class);
                        item.add(usuario);
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

    private boolean compareList(List[] toCompare, String item) {
        for (List<Usuario> list : toCompare) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getEmail().equals(item)) {
                    return true;
                }
            }
        }

        return false;
    }

    private void createTournament() {
        if (!this.validation.validate()) {
            Toast.makeText(BuyerFormTournamentActivity.this, getString(R.string.organization_data_valid), Toast.LENGTH_SHORT).show();
            return;
        } else if (!isExistOrganization(this.autoCompleteTextView.getText().toString())) {
            Toast.makeText(BuyerFormTournamentActivity.this, getString(R.string.tournament_organization_alert), Toast.LENGTH_SHORT).show();
            return;
        } else if (this.participants.size() < 2) {
            Toast.makeText(BuyerFormTournamentActivity.this, getString(R.string.tournament_minimum_participant), Toast.LENGTH_SHORT).show();
            return;
        }

        String id = UUID.randomUUID().toString();
        String nombre = this.tournamentName.getText().toString();
        int format = Integer.parseInt(this.formatTournament.getText().toString());
        String lugar = this.locationTournament.getText().toString();
        String day = this.dateTournament.getText().toString();

        Torneo torneo = new Torneo(id, nombre, format,this.participants, this.judge, this.speakers, lugar, day, this.fk_organizacion);

        this.userDb.child(FinalVar.TABLE_NAME_TORNEO).child(torneo.getId()).setValue(torneo);
        Toast.makeText(BuyerFormTournamentActivity.this, getString(R.string.organization_created_ok), Toast.LENGTH_SHORT).show();
        finish();
    }

    private void setOrganizationsAutoComplete() {
        this.organizaciones = new ArrayList<>();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance(FinalVar.URL_DATABASE).getReference(FinalVar.DATABASE_NAME).child(FinalVar.TABLE_NAME_ORGANIZACION);

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (!snapshot.exists())
                    return;

                for(DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Organizacion organizacion = dataSnapshot.getValue(Organizacion.class);
                    organizaciones.add(organizacion);
                }

                String[] items = new String[organizaciones.size()];

                for (int i = 0; i < organizaciones.size(); i++) {
                    items[i] = organizaciones.get(i).getTitulo();
                }

                autoCompleteTextView = findViewById(R.id.acOrganization);
                ArrayAdapter<String> adapter = new ArrayAdapter<>(BuyerFormTournamentActivity.this, R.layout.item_autocomplete, R.id.tvCustom, items);
                autoCompleteTextView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private boolean isExistOrganization(String item) {
        for (int i = 0; i < this.organizaciones.size(); i++)
            if (this.organizaciones.get(i).getTitulo().equals(item)) {
                this.fk_organizacion = this.organizaciones.get(i).getId();
                return true;
            }
        return false;
    }
}