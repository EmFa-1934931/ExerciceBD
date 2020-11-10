package com.w32.exercicebd2.repository;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;
import com.w32.exercicebd2.R;
import com.w32.exercicebd2.data.Cours;
import com.w32.exercicebd2.database.CoursTable;
import com.w32.exercicebd2.database.MyDatabaseFactory;

public class CoursRepository implements Repository<Cours> {

    private SQLiteDatabase database;

    public CoursRepository(SQLiteDatabase database) {
        this.database = database;
    }

    //Implémentation des méthodes de l'interface avec le "T" remplacé par l'objet de données
    @Override
    public boolean insert(Cours dataCours) {
        try{
            database.execSQL(CoursTable.INSERT_SQL, new String[]
                    {
                            dataCours.getCode(),
                            dataCours.getNom(),
                            dataCours.getProf(),
                            dataCours.getSession(),
                            String.valueOf(dataCours.getTechnique())
                    });
           return true;
        }catch(RuntimeException e){
            return false;
        }
    }

    // le prof ma dit que ça c'était le save, mais je suis pas sur lol, si je l'enleve sa marche pas
    @Override
    public boolean update(Cours dataCours) {
        //Toujours utiliser un try-catch, ce genre de manipulations peut générer des exceptions de toutes sortes
        try
        {
            //Important : on débute une transaction
            database.beginTransaction();

            database.execSQL(CoursTable.UPDATE_SQL, new String[]
                    {
                            String.valueOf(dataCours.getId()),
                            dataCours.getCode(),
                            dataCours.getNom(),
                            dataCours.getProf(),
                            dataCours.getSession(),
                            String.valueOf(dataCours.getTechnique())
                    });

            //À ce stade, la transaction est un succès
            database.setTransactionSuccessful();
            return true;
        }
        catch(RuntimeException e)
        {
            return false;
        }
    }

    @Override
    public Cours find(int id) {
        return null;
    }

    @Override
    public Cours findLast() {
        Cours cours = null;
        Cursor cursor = null;
        try
        {
            database.beginTransaction();
            cursor = database.rawQuery(CoursTable.SELECT_LAST_OF_SQL, new String[]{});

            if (cursor.moveToNext())
            {
                if(cursor.getLong(0) > 0)
                {
                    cours = new Cours();
                    cours.setId(cursor.getInt(0));
                    cours.setCode(cursor.getString(1));
                    cours.setNom(cursor.getString(2));
                    cours.setProf(cursor.getString(3));
                    cours.setSession(cursor.getString(4));
                    cours.setTechnique(Integer.parseInt(cursor.getString(5)));
                }
            }
            database.setTransactionSuccessful();
        }
        catch(RuntimeException e)
        {
            return null;
        }

        finally
        {
            if (cursor != null) {
                cursor.close();
            }
            database.endTransaction();
        }
        return cours;
    }

    @Override
    public boolean delete(Cours cours) {
        return delete(cours.getId());
    }

    @Override
    public boolean delete(int id) {
        try{
            database.execSQL(CoursTable.DELETE_SQL, new String[]
                    {String.valueOf(id)});
            return true;
        }catch(RuntimeException e){
            return false;
        }
    }
    //Autres méthodes ....


}
