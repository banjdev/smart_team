package com.smartjobs.smartjobs.cloudatabasesclassmodels;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class MoreInfoOnAgent {

    private  String zone;
    private  String category;
    private String dateInscription ;
    private String titre;
    private String formation;


    public MoreInfoOnAgent() {
    }

    public MoreInfoOnAgent(String zone, String category, String dateInscription, String titre, String formation) {
        this.zone = zone;
        this.category = category;
        this.dateInscription = dateInscription;
        this.titre = titre;
        this.formation = formation;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDateInscription() {
        return dateInscription;
    }

    public void setDateInscription(String dateInscription) {
        this.dateInscription = dateInscription;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getFormation() {
        return formation;
    }

    public void setFormation(String formation) {
        this.formation = formation;
    }
}
