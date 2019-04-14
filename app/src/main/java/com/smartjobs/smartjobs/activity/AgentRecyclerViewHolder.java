package com.smartjobs.smartjobs.activity;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.smartjobs.smartjobs.R;


//Holder for Dollar
public class AgentRecyclerViewHolder extends RecyclerView.ViewHolder{
    /**
     * fields name in cardview for the banque list on main page recycleview
     */
    public final View mView;
    public TextView banqueName;
    public TextView tauxRefBRH;
    public TextView tauxAchat,tauxVente, TMA;
    public ImageView banqueLogo;



    public AgentRecyclerViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        banqueName = itemView.findViewById(R.id.Banque_name_succursales);
        tauxRefBRH= itemView.findViewById(R.id.taux_ref_brh_dollar);
        banqueLogo =  itemView.findViewById(R.id.BanqueLogo);

    }



}

