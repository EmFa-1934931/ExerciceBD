package com.w32.exercicebd2.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import com.google.android.material.snackbar.Snackbar;
import java.util.ArrayList;

import com.w32.exercicebd2.R;
import com.w32.exercicebd2.data.Cours;
import com.w32.exercicebd2.database.CoursTable;
import com.w32.exercicebd2.database.MyDatabaseFactory;
import com.w32.exercicebd2.repository.CoursRepository;

public class FormActivity extends AppCompatActivity {

    private View rootView;
    private TextView txtCode;
    private TextView txtNom;
    private TextView txtProf;
    private Spinner comboSession;
    private CheckBox chkTechnique;
    private MyDatabaseFactory databaseFactory;
    private CoursRepository repoCours;
    private SQLiteDatabase database;
    private Cours editCours = null;
    private String selectedSession = "";
    private int isTechniqueChecked = 0;
    private boolean isOnEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        Intent intent = getIntent();
//---
        databaseFactory = new MyDatabaseFactory(this);
        repoCours = new CoursRepository(databaseFactory.getWritableDatabase());

        rootView = findViewById(R.id.rootView);
        txtCode = findViewById(R.id.txtCodeCours);
        txtNom = findViewById(R.id.txtNomCours);
        txtProf = findViewById(R.id.txtProf);
        comboSession = findViewById(R.id.comboSession);
        chkTechnique = findViewById(R.id.chkTechnique);

        comboSession.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                selectedSession = comboSession.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                selectedSession = "";
            }
        });

        if (intent.hasExtra("CLASS_PARCELABLE")) {
            editCours = savedInstanceState.getParcelable("CLASS_PARCELABLE");
            isOnEdit = true;
        } else {
            editCours = new Cours();
        }

        this.readExistingClass();
    }

    public void onCancel(View view)
    {
        onBackPressed();
    }

    public void onSaveCours(View view)
    {
        if(isOnEdit){
            editNewCours();
        }
        else{
            if (chkTechnique.isChecked()){
                isTechniqueChecked = 1;
            }
            editCours.setCode(txtCode.getText().toString());
            editCours.setNom(txtNom.getText().toString());
            editCours.setProf(txtProf.getText().toString());
            editCours.setSession(comboSession.getSelectedItem().toString());
            editCours.setTechnique(isTechniqueChecked);

            repoCours.insert(editCours);
        }

        onBackPressed();
    }


    public void readExistingClass(){
        this.txtCode.setText(editCours.getCode());
        this.txtNom.setText(editCours.getNom());
        this.txtProf.setText(editCours.getProf());

        String compareValue = editCours.getSession();
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.sessions, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        comboSession.setAdapter(adapter);
        if (compareValue != null) {
            int spinnerPosition = adapter.getPosition(compareValue);
            comboSession.setSelection(spinnerPosition);
        }

        chkTechnique.setChecked(editCours.getTechnique() == 1);
    }

    private void editNewCours(){
        if (chkTechnique.isChecked()){
            isTechniqueChecked = 1;
        }
        editCours.setCode(txtCode.getText().toString());
        editCours.setNom(txtNom.getText().toString());
        editCours.setProf(txtProf.getText().toString());
        editCours.setSession(comboSession.getSelectedItem().toString());
        editCours.setTechnique(isTechniqueChecked);

        boolean updateSuccessful = repoCours.update(editCours);
        if(updateSuccessful)
        {
            Snackbar.make(rootView, "Cours modifié avec succes !", Snackbar.LENGTH_LONG).show();
        }
        else
        {
            Snackbar.make(rootView, "La modification du cours a été interrompue", Snackbar.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        databaseFactory.close();
    }

    //TODO : Utiliser ce code pour afficher la bonne session dans le menu déroulant selon le cours en édition :
    /*
    String compareValue = editCours.getSession();
    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.sessions, android.R.layout.simple_spinner_item);
    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    comboSession.setAdapter(adapter);
    if (compareValue != null) {
        int spinnerPosition = adapter.getPosition(compareValue);
        comboSession.setSelection(spinnerPosition);
    }
    */
}
