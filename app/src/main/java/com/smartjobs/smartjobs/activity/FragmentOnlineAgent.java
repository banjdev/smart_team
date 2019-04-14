package com.smartjobs.smartjobs.activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.smartjobs.smartjobs.R;
import com.smartjobs.smartjobs.tools.Utils;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentOnlineAgent extends Fragment   {

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



    public FragmentOnlineAgent() {
        // Required empty public constructor
        // position = PassDataBetweenFragment.getOnSimpleClickBankListAdapter();

    }



    @Override
    public View onCreateView(@NonNull  LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_agent_list, container, false);
        mBanqueRecycleView = rootView.findViewById(R.id.agent_list);
//        textViewDate = rootView.findViewById(R.id.date_fragment_do);
        mSwipeRefreshLayoutDollar = rootView.findViewById(R.id.swipe_refresh_layout_dollar);
//        fab = rootView.findViewById(R.id.fab);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        try{
//            body = getContext().getResources().getString(R.string.text_body_notification);
//            title =  getContext().getResources().getString(R.string.text_title_notification);
        }catch (NullPointerException e){
            e.printStackTrace();
        }

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onATMLocationEvent(getContext());

            }});


//        mDatabase = Utils.getDatabase().getInstance().getReference();
//        mDatabase.child("bankDollarRates").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                if (dataSnapshot.exists()) {
//                    try {
////                        element = dataSnapshot.getValue(BanqueInsertTauxDollarModel.class);
//
//                    }catch (Exception e){
//                        e.printStackTrace();
//                    }
//                    try {
//                        textViewDate.setText(getResources().getString(R.string.text_taux_change) + " " + element.getDateDuJour());
//                    }catch (Exception e){
//                        e.printStackTrace();
//                    }
//                    listBanqueContentDollarArr.clear();
//                    firebasePopulateRecyclerFragmentDollar();
//
//
//                    try{
//                        //set up a notification manager to display notification in the statuts bar
//                        Intent attendanceIntent = new Intent(getContext(), Home.class);
//                        PendingIntent pendingIntent = PendingIntent.getActivity(getContext(), 0,
//                                attendanceIntent, PendingIntent.FLAG_UPDATE_CURRENT);
//
//                        Notification notif = new Notification.Builder(getContext())
//                                .setSmallIcon(R.drawable.ic_notif)
//                                .setContentIntent(pendingIntent)
//                                .setContentTitle(title)
//                                .setContentText(body).build();
//
//                        NotificationManager mNotificationManager =
//                                (NotificationManager) getContext().getSystemService(Context.NOTIFICATION_SERVICE);
//                        mNotificationManager.notify(0, notif);
//                    }catch (NullPointerException e){
//                        e.printStackTrace();
//                    }
//
//
//                }
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });

        firebasePopulateRecyclerFragmentDollar();
        mSwipeRefreshLayoutDollar.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                listBanqueContentDollarArr.clear();
                firebasePopulateRecyclerFragmentDollar();
                mSwipeRefreshLayoutDollar.setRefreshing(false);
            }
        });


    }

    //This method help to pass data to the recycler view using the DollarCardViewModel as a buffer and the data are use by the
    //method onBindViewHolder method to bind data to the recyclerView

    private boolean firebasePopulateRecyclerFragmentDollar() {
        for (int i = 0; i < 7; i++) {
            AgentCardViewModel mDollarCardViewModel = new AgentCardViewModel();
            if (i == 0) {
                try{
                    img = ResourcesCompat.getDrawable(getContext().getResources(), R.drawable.icone_contact, null);
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
                    img = ResourcesCompat.getDrawable(getContext().getResources(), R.drawable.icone_contact, null);
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
                    img = ResourcesCompat.getDrawable(getContext().getResources(), R.drawable.icone_contact, null);
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
                    img = ResourcesCompat.getDrawable(getContext().getResources(), R.drawable.icone_contact, null);
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
                    img = ResourcesCompat.getDrawable(getContext().getResources(), R.drawable.icone_contact, null);
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
                    img = ResourcesCompat.getDrawable(getContext().getResources(), R.drawable.icone_contact, null);
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
                    img = ResourcesCompat.getDrawable(getContext().getResources(), R.drawable.icone_contact, null);
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

        mLinearLayoutManager=new LinearLayoutManager(getContext());
        mBanqueRecycleView.setLayoutManager(mLinearLayoutManager);
        mAgentListAdapter = new AgentRecyclerViewAdapter(getContext());

        // We set the array to the adapter in other way, we me bind the data to the recyclerviewAdapter and the adapter will display the data in the recyclerview
        mAgentListAdapter .setListBanquesDollar(listBanqueContentDollarArr);
        mBanqueRecycleView.setAdapter(mAgentListAdapter );

        //We in turn set the adapter to the RecyclerView
        return true;
    }

    private void onATMLocationEvent(Context context) {

        try {
            // Search for atm nearby
            Uri gmmIntentUri = Uri.parse("geo:0,0?q=atm");
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            startActivity(mapIntent);
        } catch (Exception e) {
            Toast.makeText(context, "" + e, Toast.LENGTH_LONG).show();
        }

    }
}
