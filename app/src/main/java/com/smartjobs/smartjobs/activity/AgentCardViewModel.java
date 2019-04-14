package com.smartjobs.smartjobs.activity;


import android.graphics.drawable.Drawable;

public class AgentCardViewModel {

    private String banqueName;
    private String tauxRefBRH;
    private String tauxAchat;
    private String tauxVente;
    private String TMA;
    private Drawable logoBanque;





    //Default constructor
    public AgentCardViewModel() {

    }

    public AgentCardViewModel(String banqueName, String tauxRefBRH, String tauxAchat, String tauxVente){
        this.banqueName=banqueName;
        this.tauxRefBRH=tauxRefBRH;
        this.tauxAchat=tauxAchat;
        this.tauxVente=tauxVente;

    }

    //We define the getters here

    public String getBanqueName(){
        return this.banqueName;
    }
    public String getTauxRefBRH(){
        return this.tauxRefBRH;
    }

    public String getTauxAchat(){
        return this.tauxAchat;
    }

    public String getTauxVente(){
        return this.tauxVente;
    }
    public String getTMA(){
        return this.TMA;
    }
    public Drawable getLogoBanque(){
        return  this.logoBanque;
    }

    //Define the setters here

    public void setBanqueName(String banqueName){
        this.banqueName=banqueName;
    }
    public void setTauxRefBRH(String tauxRefBRH) {this.tauxRefBRH=tauxRefBRH;}

    public void setTauxAchat(String tauxAchat){
        this.tauxAchat=tauxAchat;
    }

    public void setTauxVente(String tauxVente){
        this.tauxVente=tauxVente;
    }
    public void setTMA(String TMA){
        this.TMA=TMA;
    }

    public void setLogoBanque(Drawable logoBanque){this.logoBanque=logoBanque;}




}
