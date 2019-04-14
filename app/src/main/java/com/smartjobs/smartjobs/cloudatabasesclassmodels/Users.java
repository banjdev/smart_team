package com.smartjobs.smartjobs.cloudatabasesclassmodels;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Users {

    private String nom;
    private String prenom;
    private String sexe;
    private String status;
    public Users() {
    }

    public Users(String nom, String prenom, String sexe, String status) {
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.status = status;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public void setStatus(String status) { this.status = status; }

    public String getStatus() { return status; }
}

