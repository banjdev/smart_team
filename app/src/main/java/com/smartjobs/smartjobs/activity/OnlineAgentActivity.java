package com.smartjobs.smartjobs.activity;

import android.graphics.drawable.Drawable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.smartjobs.smartjobs.R;

import java.util.ArrayList;

public class OnlineAgentActivity extends AppCompatActivity {

    private RecyclerView mBanqueRecycleView;
    AgentRecyclerViewAdapter mAgentListAdapter;
    ItemClickListener mItemClickListener;
    private ArrayList<AgentCardViewModel> listBanqueContentDollarArr = new ArrayList<AgentCardViewModel>();
    Drawable img;
    DatabaseReference mDatabase;

    //BanqueInsertTauxDollarModel element;
    String [] nomBanques={"Banque République d'Haïti","Sogebank", "Banque Nationale de Crédit", "Unibank","Banque de l'union Haïtienne", "Capital Bank", "Banque Populaire Haïtienne"};
    SwipeRefreshLayout mSwipeRefreshLayoutDollar;
    private LinearLayoutManager mLinearLayoutManager;
    private TextView textViewDate;
    private String titleDialogLocalisationATM;
    private String messageLocalisationATM;
    private String textOkDialogEuroATM;
    private String textCancelDialogEuroATM;
    //    private  NiftyDialogBuilder materialDesignAnimatedDialogATM;
    private int position; //To track on what bank a click is made
    // private PassDataBetweenFragment mPassDataBetweenFragment;
    private String body, title;
    private FloatingActionButton fab;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_agent_list);
        mBanqueRecycleView = findViewById(R.id.agent_list);
//        textViewDate = rootView.findViewById(R.id.date_fragment_do);
        mSwipeRefreshLayoutDollar = findViewById(R.id.swipe_refresh_layout_dollar);
        firebasePopulateRecyclerFragmentAgent();
        mSwipeRefreshLayoutDollar.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                listBanqueContentDollarArr.clear();
                firebasePopulateRecyclerFragmentAgent();
                mSwipeRefreshLayoutDollar.setRefreshing(false);
            }
        });
//        fab = rootView.findViewById(R.id.fab);

    }

    //method onBindViewHolder method to bind data to the recyclerView

    private boolean firebasePopulateRecyclerFragmentAgent() {
        for (int i = 0; i < 7; i++) {
            AgentCardViewModel mDollarCardViewModel = new AgentCardViewModel();
            if (i == 0) {
                try{
                    img = ResourcesCompat.getDrawable(this.getResources(), R.drawable.icone_contact, null);
                    mDollarCardViewModel.setLogoBanque(img);
                    mDollarCardViewModel.setBanqueName("Jean");
                    mDollarCardViewModel.setTauxRefBRH("Technicien");
                    mDollarCardViewModel.setTMA("Jeanne");
                    mDollarCardViewModel.setTauxAchat("Diplome");
                    mDollarCardViewModel.setTauxVente("Test") ;

                }catch (NullPointerException e){
                    e.printStackTrace();
                }



            }

            if (i == 1) {
                try{
                    img = ResourcesCompat.getDrawable(this.getResources(), R.drawable.icone_contact, null);
                    mDollarCardViewModel.setLogoBanque(img);
                    mDollarCardViewModel.setBanqueName("Jean");
                    mDollarCardViewModel.setTauxRefBRH("Technicien");
                    mDollarCardViewModel.setTMA("Jeanne");
                    mDollarCardViewModel.setTauxAchat("Diplome");
                    mDollarCardViewModel.setTauxVente("Test") ;

                }catch (NullPointerException e){
                    e.printStackTrace();
                }

            }

            if (i == 2) {
                try{
                    img = ResourcesCompat.getDrawable(this.getResources(), R.drawable.icone_contact, null);
                    mDollarCardViewModel.setLogoBanque(img);
                    mDollarCardViewModel.setBanqueName("Jean");
                    mDollarCardViewModel.setTauxRefBRH("Technicien");
                    mDollarCardViewModel.setTMA("Jeanne");
                    mDollarCardViewModel.setTauxAchat("Diplome");
                    mDollarCardViewModel.setTauxVente("Test") ;

                }catch (NullPointerException e){
                    e.printStackTrace();
                }

            }
            if (i == 3) {
                try{
                    img = ResourcesCompat.getDrawable(this.getResources(), R.drawable.icone_contact, null);
                    mDollarCardViewModel.setLogoBanque(img);
                    mDollarCardViewModel.setBanqueName("Jean");
                    mDollarCardViewModel.setTauxRefBRH("Technicien");
                    mDollarCardViewModel.setTMA("Jeanne");
                    mDollarCardViewModel.setTauxAchat("Diplome");
                    mDollarCardViewModel.setTauxVente("Test") ;

                }catch (NullPointerException e){
                    e.printStackTrace();
                }


            }
            if (i == 4) {

                try{
                    img = ResourcesCompat.getDrawable(this.getResources(), R.drawable.icone_contact, null);
                    mDollarCardViewModel.setLogoBanque(img);
                    mDollarCardViewModel.setBanqueName("Jean");
                    mDollarCardViewModel.setTauxRefBRH("Technicien");
                    mDollarCardViewModel.setTMA("Jeanne");
                    mDollarCardViewModel.setTauxAchat("Diplome");
                    mDollarCardViewModel.setTauxVente("Test") ;

                }catch (NullPointerException e){
                    e.printStackTrace();
                }


            }
            if (i == 5) {

                try{
                    img = ResourcesCompat.getDrawable(this.getResources(), R.drawable.icone_contact, null);
                    mDollarCardViewModel.setLogoBanque(img);
                    mDollarCardViewModel.setBanqueName("Jean");
                    mDollarCardViewModel.setTauxRefBRH("Technicien");
                    mDollarCardViewModel.setTMA("Jeanne");
                    mDollarCardViewModel.setTauxAchat("Diplome");
                    mDollarCardViewModel.setTauxVente("Test") ;

                }catch (NullPointerException e){
                    e.printStackTrace();
                }


            }
            if (i == 6) {

                try{
                    img = ResourcesCompat.getDrawable(this.getResources(), R.drawable.icone_contact, null);
                    mDollarCardViewModel.setLogoBanque(img);
                    mDollarCardViewModel.setBanqueName("Jean");
                    mDollarCardViewModel.setTauxRefBRH("Technicien");
                    mDollarCardViewModel.setTMA("Jeanne");
                    mDollarCardViewModel.setTauxAchat("Diplome");
                    mDollarCardViewModel.setTauxVente("Test") ;

                }catch (NullPointerException e){
                    e.printStackTrace();
                }


            }

            //After setting the values, we add all the Objects to the array
            listBanqueContentDollarArr.add(mDollarCardViewModel);
        }

        mLinearLayoutManager=new LinearLayoutManager(this);
        mBanqueRecycleView.setLayoutManager(mLinearLayoutManager);
        mAgentListAdapter = new AgentRecyclerViewAdapter(this);

        // We set the array to the adapter in other way, we me bind the data to the recyclerviewAdapter and the adapter will display the data in the recyclerview
        mAgentListAdapter .setListBanquesDollar(listBanqueContentDollarArr);
        mBanqueRecycleView.setAdapter(mAgentListAdapter );

        //We in turn set the adapter to the RecyclerView
        return true;
    }
}
