package com.smartjobs.smartjobs.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.smartjobs.smartjobs.MainActivity;
import com.smartjobs.smartjobs.R;

import static android.os.Build.ID;

public class LoginActivity extends AppCompatActivity {

    private EditText inputEmail, inputPassword;
    private FirebaseAuth auth;
    private ProgressBar progressBar;
    // private Button btnSignup, btnLogin, btnReset;
    private Button btnSignup, btnLogin, btnReset;
    //Firebase
    private FirebaseDatabase  database;
    private DatabaseReference mDatabase ;
    private DatabaseReference dDatabase ;
    //private DatabaseReference mRef = FirebaseDatabase.getInstance().getReference().child("users");
    private String status;
    private static final String  AGENT = "Agent";
    private static final String  CuSTOMER = "Customer";

    int test = 0;



//    private FirebaseDatabase database = FirebaseDatabase.getInstance();
//    private DatabaseReference mDatabase = database.getReference("Users"); //Does Users -> Groups
//    private DatabaseReference mRef = FirebaseDatabase.getInstance().getReference().child("Users");
//
//    //String currentUserID = FirebaseAuth.getInstance().getCurrentUser().getUid();
//    String currentUserID;
    //DatabaseReference newRef ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();


//        currentUserID = auth.getUid();
//        newRef = mRef.child(currentUserID);

//        newRef.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                //Log.d(TAG, "Name: " + dataSnapshot.child("name").getValue());
//
//                final String status = String.valueOf(dataSnapshot.child("status").getValue());
//
//
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });


        // set the view now


        if (auth.getCurrentUser() != null) {

            try{
                mDatabase = database.getReference("Users").child("Agent").child(auth.getCurrentUser().getUid()).child("status");
                test = 1;
            }catch(Exception e){
                e.printStackTrace();
            }

            try{
                dDatabase = database.getReference("Users").child("Customer").child(auth.getCurrentUser().getUid()).child("status");

            }catch(Exception e){
                e.printStackTrace();
            }

            switch (test) {
                case 1 :
                    startActivity(new Intent(LoginActivity.this, AgentActivity.class));
                    finish();
                    break;

                case 0 :
                    startActivity(new Intent(LoginActivity.this, ClientActivity.class));
                    finish();
                    break;
            }

//            database = FirebaseDatabase.getInstance();
//            mDatabase = database.getReference("Users").child(auth.getCurrentUser().getUid()).getParent(); //Does Users -> Groups
//            //mRef = FirebaseDatabase.getInstance().getReference().child("users");
//            status = mDatabase.toString();
//
//            switch (status){
//                case "Agent":
//                    startActivity(new Intent(LoginActivity.this, AgentActivity.class));
//                    finish();
//                    break;
//
//                case "Customer":
//                    startActivity(new Intent(LoginActivity.this, ClientActivity.class));
//                    finish();
//                    break;
//            }
//
//            startActivity(new Intent(LoginActivity.this, AgentActivity.class));
//            finish();

//                    if(status == "Agent"){
//
//                    }

//                    switch (status){
//                        case "Agent":
//                            startActivity(new Intent(LoginActivity.this, OffererActivity.class));
//                            finish();
//                            break;
//                        case "Costumer":
//                            startActivity(new Intent(LoginActivity.this, ClientActivity.class));
//                            finish();
//                            break;
//
//                    }



        }

        setContentView(R.layout.activity_login);

        // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // setSupportActionBar(toolbar);

        inputEmail =  findViewById(R.id.email);
        inputPassword =  findViewById(R.id.password);
        progressBar =  findViewById(R.id.progressBar);
        btnSignup =  findViewById(R.id.btn_signup);
        btnLogin = findViewById(R.id.btn_login);
        btnReset =  findViewById(R.id.btn_reset_password);


        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, SignupActivity.class));
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, ResetPasswordActivity.class));
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = inputEmail.getText().toString();
                final String password = inputPassword.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                //authenticate user
                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                progressBar.setVisibility(View.GONE);
                                if (!task.isSuccessful()) {
                                    // there was an error
                                    if (password.length() < 6) {
                                        inputPassword.setError(getString(R.string.minimum_password));
                                    } else {
                                        Toast.makeText(LoginActivity.this, getString(R.string.auth_failed), Toast.LENGTH_LONG).show();
                                    }
                                } else {
                                    // Intent intent = new Intent(LoginActivity.this, ClientActivity.class);
                                    // startActivity(intent);
                                    //  finish();
                                    try{
                                        mDatabase = database.getReference("Users").child("Agent").child(auth.getCurrentUser().getUid()).child("status");
                                        test = 1;
                                    }catch(Exception e){
                                        e.printStackTrace();
                                    }

                                    try{
                                        dDatabase = database.getReference("Users").child("Customer").child(auth.getCurrentUser().getUid()).child("status");

                                    }catch(Exception e){
                                        e.printStackTrace();
                                    }

                                    switch (test) {
                                        case 1 :
                                            startActivity(new Intent(LoginActivity.this, AgentActivity.class));
                                            finish();
                                            break;

                                        case 0 :
                                            startActivity(new Intent(LoginActivity.this, ClientActivity.class));
                                            finish();
                                            break;
                                    }



                                }

                            }



                        });
            }
        });
    }
}
