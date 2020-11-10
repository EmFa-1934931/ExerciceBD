package com.w32.exercicebd2.data;

import android.os.Parcel;
import android.os.Parcelable;

public class Cours implements Parcelable {

    private int id;
    private String code;
    private String nom;
    private String prof;
    private String session;
    private int technique;

    public Cours()
    {
        this(0, "", "", "", "", 0);
    }

    //Ceci est un commentaire 1 2 3
    public Cours(int id, String code, String nom, String prof, String session, int technique)
    {
        this.id = id;
        this.code = code;
        this.nom = nom;
        this.prof = prof;
        this.session = session;
        this.technique = technique;
    }

    public int getId() { return id;}

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getProf() {
        return prof;
    }

    public void setProf(String prof) {
        this.prof = prof;
    }

    public int getTechnique() {
        return technique;
    }

    public void setTechnique(int technique) {
        this.technique = technique;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
