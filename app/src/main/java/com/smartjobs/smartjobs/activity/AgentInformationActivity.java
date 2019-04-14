package com.smartjobs.smartjobs.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.smartjobs.smartjobs.R;
import com.smartjobs.smartjobs.cloudatabasesclassmodels.MoreInfoOnAgent;
import com.smartjobs.smartjobs.cloudatabasesclassmodels.Users;

import java.util.Calendar;


public class AgentInformationActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    EditText  editTextDateIscription, editTextTitre, editTextFormation;
    TextView tv_titre_page;
    DatePickerDialog picker;
    String titrePage, titre, formation, anneeDebut,anneeFin, start_dated;
  //  String dateInscriptionAgent;
    RelativeLayout formPart1, formPart2, formPart3;
    ViewGroup container;
    Animation formSlideDown, formSlideUp;
    ImageButton btn1, btn2, insert;
    protected boolean goToHome = true;
    private FirebaseDatabase  database;
    private DatabaseReference mDatabase;
    String currentUser;
    private static final String  AGENT = "Agent";
    private static final String  CUSTOMER = "Customer";
    String status;
    private FirebaseAuth auth;
    Users element;

    String[] categories = {"Climatisation","Carrelage","Depannage d'Ordinateur","Mecanique","Menuiserie","Plomberie"};
    String[] zones = {"Delmas","Carrefour","Tabbarre","Turgeau","Petion Ville","Croix des Bouquets"};

    String category;
    String zone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent_information);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        status = database.getReference("Users").child(auth.getCurrentUser().getUid()).getParent().toString(); //Does Users -> Groups
        Toast.makeText(getApplicationContext(),mDatabase.toString(),Toast.LENGTH_LONG).show();
        //mRef = FirebaseDatabase.getInstance().getReference().child("users");
         // We retrieve the status as is useful to know the status of the current user to save his/her data on Agent or Customer status
        currentUser = auth.getCurrentUser().getUid();

        Spinner spin = (Spinner) findViewById(R.id.simpleSpinner);
        spin.setOnItemSelectedListener(this);

        Spinner spinZone = (Spinner) findViewById(R.id.sp_zone);
        spinZone.setOnItemSelectedListener(this);

        //Creating the ArrayAdapter instance having the bank name list
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,categories);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter aaZone = new ArrayAdapter(this,android.R.layout.simple_spinner_item,zones);
        aaZone.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);
        spinZone.setAdapter(aaZone);


        //button initialization
        btn1 = findViewById(R.id.insert_form1);
        //btn2 = findViewById(R.id.insert_form2);
        insert = findViewById(R.id.insert_button);
        // btnGPS =  findViewById(R.id.add_school_btn_gps) ;

        final TextView start_date = findViewById(R.id.tv_start_date);

        //formpart initialization from the layout
        formPart1 = findViewById(R.id.formPart1);
        formPart2 = findViewById(R.id.formPart2);
        //formPart3 = findViewById(R.id.formPart3);


        tv_titre_page = findViewById(R.id.tv_titre_page);
        editTextTitre = findViewById(R.id.titre);

        editTextFormation = findViewById(R.id.formation);

        start_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(AgentInformationActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                start_date.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });



        //animation initialization
        formSlideUp = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);
        //formSlideUp.setFillAfter(true);
        formSlideDown = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);

        container = (ViewGroup) findViewById(R.id.add_agent_info_container);
        //using btn click listener to pass from part 1 to part 3 of the school creation form
        btn1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //animation that hides previous layout (part1) and shows part2
                setFormAnimation(formPart1, formPart2, formSlideUp);
                goToHome = false;
            }
        });

//        btn2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //animation that hides previous layout (part2) and shows part3
//                setFormAnimation(formPart2, formPart3, formSlideUp);
//            }
//        });

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                       // dateInscriptionAgent = editTextDateIscription.getText().toString().trim();
                        start_dated = start_date.getText().toString().trim();
                        Toast.makeText(getApplicationContext(),"Test"+start_dated,Toast.LENGTH_LONG).show();
                        titre = editTextTitre.getText().toString().trim();
                        formation = editTextFormation.getText().toString().trim();

                       writeAgentOnCloudDatabase(zone,category,currentUser,start_dated,titre,formation);
                       Intent i = new Intent(AgentInformationActivity.this,AgentActivity.class);
                       startActivity(i);
                       finish();

            }
        });


        mDatabase.child("Users").child("Agent").child(auth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {

                    try{
                        element = dataSnapshot.getValue(Users.class);
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                    String bnjr="Bonjour, ";
                    String end = " !!!";
                    String prenom= element.getPrenom();
                    String allStrings = bnjr+prenom+end;
                    tv_titre_page.setText(allStrings);



                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                //Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        });

        //Performing action onItemSelected and onNothing selected

    }



    //animation for layout form
    public void setFormAnimation(RelativeLayout layoutPrevious, RelativeLayout layoutNext, Animation mAnimation) {
        if (layoutNext.getVisibility() == View.GONE) {
            layoutPrevious.setVisibility(View.GONE);
            layoutNext.startAnimation(mAnimation);
            layoutNext.setVisibility(View.VISIBLE);

        }

    }

    /**
     * we override the back button action. here we first check if the first part of the form is
     * visible (i=0), if so we go back to previous activity on back pressed otherwise we just view
     * the previous part of the form
     */
    @Override
    public void onBackPressed() {
        if (goToHome) {
            super.onBackPressed();
            overridePendingTransition(R.anim.slide_up, R.anim.slide_down);
        } else {
            for (int i = 0; i < container.getChildCount(); i++) {
                if (i != 0) {
                    if (container.getChildAt(i).getVisibility() == View.VISIBLE) {
                        int index = i - 1;
                        container.getChildAt(index).setVisibility(View.VISIBLE);
                        container.getChildAt(index).startAnimation(formSlideDown);
                        container.getChildAt(i).setVisibility(View.GONE);
                        goToHome = false;
                    }
                } else {
                    goToHome = true;
                }


            }
        }


    }

    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {


        switch (arg0.getId()) {
            case R.id.simpleSpinner:
                category = arg0.getItemAtPosition(position).toString();
                break;
            case R.id.sp_zone:
                zone = arg0.getItemAtPosition(position).toString();
                break;
            default:
                break;
        }
      //  Toast.makeText(getApplicationContext(), bankNames[position], Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
// TODO Auto-generated method stub

    }


    private void writeAgentOnCloudDatabase(String zone, String category, String userId, String dateInscriptionAgent, String titre,String formation){
        MoreInfoOnAgent moreInfoOnAgent = new MoreInfoOnAgent(zone, category,dateInscriptionAgent, titre,formation);

        DatabaseReference pushRef = mDatabase.child("Users").child("Agent").child(userId).push();
        //pushRef.setValue(groceryListsModel);
        pushRef.setValue(moreInfoOnAgent);
        String gID = pushRef.getKey();
        PassingDataClass.setgId(gID);

    }

}
