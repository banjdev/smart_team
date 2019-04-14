package com.smartjobs.smartjobs.activity;

import android.content.Context;

import android.content.Intent;
import android.support.annotation.NonNull;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.smartjobs.smartjobs.R;

import java.util.ArrayList;

//Adapter for Dollar Fragment
public class AgentRecyclerViewAdapter extends RecyclerView.Adapter<AgentRecyclerViewHolder> {


    private Context context;
    private boolean mTwoPane;
    private ArrayList<AgentCardViewModel> listBanquesDollar= new ArrayList<>();


    private int itemClickedDollarAdapterPosition;

    String titleDialogLocalisationDollarAdapter;
    String textDialogMessageDollarAdapter;
    String textOkDialogDollarAdapter;
    String textCancelDialogDollarAdapter;


//    private Fragment mFragment;
//    FragmentManager fragmentManagerDollar;
//    private ConverterDollarSogebank mConverterDollarSogebank;



    public AgentRecyclerViewAdapter(Context context ) {
        this.context = context;
//        materialDesignAnimatedDialog = NiftyDialogBuilder.getInstance(context);


    }

    @NonNull
    public AgentRecyclerViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.agent_list_content, parent, false);
        return new AgentRecyclerViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull  final AgentRecyclerViewHolder holder,  int position) {
        AgentCardViewModel lists_items=listBanquesDollar.get(position);
        holder.banqueName.setText(lists_items.getBanqueName());
        holder.tauxRefBRH.setText(lists_items.getTauxRefBRH());
        holder.banqueLogo.setImageDrawable(lists_items.getLogoBanque());


        /*Here we affect the itemClickPostition variable with the value of position to access the value position
         * outsite the function (to use it as a global variable)*/

        /*
          adding onclicklistener event for each item in this recycleviewhttps://api-goud.herokuapp.com/api/banques
         */
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //itemClickedDollarAdapterPosition =position;

                try {


                  Intent i = new Intent(v.getContext(), ContactAgentActivity.class);
                  v.getContext().startActivity(i);

                } catch(Exception e){
                    Toast.makeText(context,""+e,Toast.LENGTH_LONG).show();
                }
            }


        });

        holder.mView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(final View v) {


                return true;
            }
        });


    }

    //Setting the arraylist for the adapter to pass it the data to be binded to the recycler view
    public void setListBanquesDollar(ArrayList<AgentCardViewModel> listBanquesDollar) {
        this.listBanquesDollar = listBanquesDollar;
        notifyItemRangeChanged(0, listBanquesDollar.size());
        notifyDataSetChanged();
    }

    //Setting the arraylist for the adapter to pass it the data to be binded to the recycler view
    public ArrayList<AgentCardViewModel>  getListBanquesDollar() {
        return this.listBanquesDollar ;

    }


    @Override
    public int getItemCount() {
        return listBanquesDollar.size();
    }


}