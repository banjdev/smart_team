package com.smartjobs.smartjobs.model;

public class Offer {
    private String uId;
    private String date;
    private String prix;
    private String description;

    public Offer(String uId, String date, String prix, String description) {
        this.uId = uId;
        this.date = date;
        this.prix = prix;
        this.description = description;
    }

    public Offer() {
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
