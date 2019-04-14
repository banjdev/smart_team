package com.smartjobs.smartjobs.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.smartjobs.smartjobs.R;
import com.smartjobs.smartjobs.cloudatabasesclassmodels.AgentAvailability;
import com.smartjobs.smartjobs.cloudatabasesclassmodels.MoreInfoOnAgent;
import com.smartjobs.smartjobs.cloudatabasesclassmodels.Users;

public class AgentActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private FirebaseAuth auth;
    private ImageView ivOnline, ivOfferimage;
    private Button btnOnline;
    private boolean isOnline = true;

    TextView Offername ;
    TextView OfferTitre ;
    TextView Offercategory;

    private DatabaseReference mDatabase;
    Users element;
    MoreInfoOnAgent info;
    private String name;
    private String prenom;
    private String fullname;
    private String titre;
    private String category;
    String currentUser;
    private static String ONLINE ="online";
    private static String OFFLINE ="offline";
    String agentAvailibilty; //We asign this variable with online string when agent is online and with offline string when agent is offline

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offerer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //Get Firebase auth instance


        mDatabase = FirebaseDatabase.getInstance().getReference();
        auth = FirebaseAuth.getInstance();

        ivOnline = findViewById(R.id.iv_online);
        ivOfferimage = findViewById(R.id.ivOfferImage);
        btnOnline = findViewById(R.id.btn_online);

          Offername = findViewById(R.id.tvOfferName);
        OfferTitre = findViewById(R.id.tvOfferTitre);
         Offercategory = findViewById(R.id.tvOffercategory);

        auth = FirebaseAuth.getInstance();


//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        btnOnline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isOnline == false){
                    isOnline = true ;
                    ivOnline.setVisibility(View.VISIBLE);
                    btnOnline.setText("SE METTRE INDISPONIBLE");
                    agentAvailibilty = ONLINE;
                } else {
                    isOnline = false ;
                    ivOnline.setVisibility(View.INVISIBLE);
                    btnOnline.setText("SE METTRE EN LIGNE");
                    agentAvailibilty = OFFLINE;
                }

                currentUser = auth.getCurrentUser().getUid();
                writeIfAgentIsOnliOnCloudDatabase(currentUser,agentAvailibilty);

            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        mDatabase.child("Users").child("Agent").child(auth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {

                    try{
                        element = dataSnapshot.getValue(Users.class);
                    }catch (Exception e){
                        e.printStackTrace();
                    }


                    try{
                        name = element.getNom();
                        prenom = element.getPrenom();
                        fullname = name + " " + prenom;
                        Offername.setText(fullname);
                    }catch (NullPointerException e){
                        e.printStackTrace();
                    }


                   // String titre = info.getTitre();
                   // String category = info.getCategory();

                    //OfferTitre.setText(titre);
                  // Offercategory.setText(category);




                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                //Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        });


        mDatabase.child("Users").child("Agent").child(auth.getCurrentUser().getUid()).child(PassingDataClass.getgId()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {

                    try{
                        info = dataSnapshot.getValue(MoreInfoOnAgent.class);
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                    try{
                        titre = info.getTitre();
                        category = info.getCategory();
                        OfferTitre.setText(titre);
                        Offercategory.setText(category);
                    }catch (NullPointerException e){
                        e.printStackTrace();
                    }






                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                //Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        });




    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.offerer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_storywork) {
            // Handle the camera action
        } else if (id == R.id.nav_storypayment) {

        } else if (id == R.id.nav_setting) {


        } else if (id == R.id.nav_Sign_out) {

            signOut();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //sign out method
    public void signOut() {
        auth.signOut();
        Intent i = new Intent(AgentActivity.this, LoginActivity.class);
        startActivity(i);
        finish();
    }


    private void writeIfAgentIsOnliOnCloudDatabase( String userId, String disponibilite){
        AgentAvailability aAv = new AgentAvailability(disponibilite);

        DatabaseReference pushRef = mDatabase.child("Users").child("Agent").child(userId).push();
        //pushRef.setValue(groceryListsModel);
        pushRef.setValue(aAv);
        String gID = pushRef.getKey();
        PassingDataClass.setgId(gID);

    }
}
