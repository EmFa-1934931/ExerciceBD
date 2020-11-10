package com.w32.exercicebd2.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.w32.exercicebd2.R;
import com.w32.exercicebd2.data.Cours;
import com.w32.exercicebd2.database.CoursTable;
import com.w32.exercicebd2.database.MyDatabaseFactory;
import com.w32.exercicebd2.repository.CoursRepository;

public class MainActivity extends AppCompatActivity {

    private View rootView;
    private TextView txtCode;
    private TextView txtNom;
    private TextView txtProf;
    private TextView txtSession;
    private TextView txtTechnique;
    private View layoutCours;
    private View layoutPasDeCours;
    private Button btnSupprimer;
    private Button btnEditer;
    private Button btnAjouter;
    private MyDatabaseFactory databaseFactory;
    private SQLiteDatabase database;
    private Cours currentCours = null;

    private CoursRepository repoCours;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // =====================================================
        //===========CONNEXION A LA BD POUR L'INTERFACE=========
        //======================================================

        databaseFactory = new MyDatabaseFactory(this);
        database = databaseFactory.getWritableDatabase();

        //Obtention d'un repository pour l'accès aux données
        repoCours = new CoursRepository(database);
        //=======================================================

        rootView = findViewById(R.id.rootView);
        txtCode = findViewById(R.id.txtValueCodeCours);
        txtNom = findViewById(R.id.txtValueNomCours);
        txtProf = findViewById(R.id.txtValueProf);
        txtSession = findViewById(R.id.txtValueSession);
        txtTechnique = findViewById(R.id.txtValueTechnique);
        layoutCours = findViewById(R.id.layoutCours);
        layoutPasDeCours = findViewById(R.id.layoutPasDeCours);
        btnSupprimer = findViewById(R.id.btnDelete);
        btnEditer = findViewById(R.id.btnEdit);
        btnAjouter = findViewById(R.id.btnAdd);

        displayDernierCours();
    }
    //---
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelable("STATE_COURS", currentCours);
    }


    //---
    @Override
    protected void onResume()
    {
        super.onResume();
        //displayDernierCours();
    }

    public void onAddCours(View view)
    {
        Intent intent = new Intent(this, FormActivity.class);
        startActivity(intent);
    }

    public void onEditCours(View view)
    {
        Intent intent = new Intent(this, FormActivity.class);
        currentCours = repoCours.findLast();
        intent.putExtra("CLASS_PARCELABLE", currentCours);
        startActivity(intent);
    }

    public void onDeleteCours(View view)
    {
        boolean deleteSuccessful = repoCours.delete(currentCours);
        if(deleteSuccessful)
        {
            Snackbar.make(rootView, "Cours supprimé avec succes !", Snackbar.LENGTH_LONG).show();
        }
        else
        {
            Snackbar.make(rootView, "La suppression du cours a été interrompue", Snackbar.LENGTH_LONG).show();
        }

        displayDernierCours();
    }

    private void displayDernierCours()
    {
        currentCours = repoCours.findLast();
        if(currentCours != null)
        {
            txtCode.setText(currentCours.getCode());
            txtNom.setText(currentCours.getNom());
            txtProf.setText(currentCours.getProf());
            txtSession.setText(currentCours.getSession());
            if(currentCours.getTechnique() == 1)
            {
                txtTechnique.setText("Oui");
            }
            else
            {
                txtTechnique.setText("Non");
            }
            layoutCours.setVisibility(View.VISIBLE);
            layoutPasDeCours.setVisibility(View.INVISIBLE);
            btnEditer.setEnabled(true);
            btnSupprimer.setEnabled(true);

        }
        else
        {
            layoutCours.setVisibility(View.INVISIBLE);
            layoutPasDeCours.setVisibility(View.VISIBLE);
            btnEditer.setEnabled(false);
            btnSupprimer.setEnabled(false);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        databaseFactory.close();
    }
}
