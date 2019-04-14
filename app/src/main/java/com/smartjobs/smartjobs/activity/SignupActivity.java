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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.smartjobs.smartjobs.R;
import com.smartjobs.smartjobs.cloudatabasesclassmodels.Users;

public class SignupActivity extends AppCompatActivity {

    EditText inputNom,inputPrenom,inputEmail, inputPassword;
    private RadioButton rbMale, rbFemale, radioSexe;
    private RadioGroup rgSexe;
    private Button btnSignIn, btnSignUp, btnResetPassword;
    private  Button btnOffreur, btnClient;
    private ProgressBar progressBar;
    private FirebaseAuth auth;
    private DatabaseReference mDatabase;
    private String sexe;
    private String status;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getSupportActionBar().hide();
        //FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        //Get Firebase auth instance

        auth = FirebaseAuth.getInstance();
        inputNom = findViewById(R.id.nom);
        inputPrenom = findViewById(R.id.prenom);
       // inputSexe=findViewById(R.id.sexe);
        rbMale = findViewById(R.id.rbMale);
        rbFemale = findViewById(R.id.rbFemale);
        rgSexe = findViewById(R.id.rgSexe);
        inputEmail =  findViewById(R.id.email);
        inputPassword = findViewById(R.id.password);
        btnSignIn =  findViewById(R.id.sign_in_button);
        //btnSignUp = findViewById(R.id.sign_up_button);
        btnOffreur = findViewById(R.id.btnOffreur);
        btnClient = findViewById(R.id.btnClient);
        progressBar =  findViewById(R.id.progressBar);
        btnResetPassword =  findViewById(R.id.btn_reset_password);

        btnResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignupActivity.this, ResetPasswordActivity.class));
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignupActivity.this, LoginActivity.class));
                finish();

            }
        });

        btnClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = inputEmail.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();
                final String lastName = inputNom.getText().toString().trim();
                final String firstName= inputPrenom.getText().toString().trim();
                status= "Customer";
              //  final String sexe= inputSexe.getText().toString().trim();
                int selectedId = rgSexe.getCheckedRadioButtonId();

                try{
                    radioSexe = findViewById(selectedId);
                    final String sexe = radioSexe.getText().toString().trim();
                }catch (Exception e){
                    e.printStackTrace();
                }



                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);
                //create user
                auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(SignupActivity.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {
                                    Toast.makeText(SignupActivity.this, "Authentication failed." + task.getException(), Toast.LENGTH_SHORT).show();
                                } else {
                                    String currentuser = FirebaseAuth.getInstance().getUid();
                                    writeAgentOnCloudDatabase(currentuser,lastName,firstName,sexe, status);
                                    startActivity(new Intent(SignupActivity.this, ClientActivity.class));
                                    finish();
                                }
                            }
                        });

            }
        });


       btnOffreur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = inputEmail.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();
                final String lastName = inputNom.getText().toString().trim();
                final String firstName= inputPrenom.getText().toString().trim();
                status= "Agent";
                //  final String sexe= inputSexe.getText().toString().trim();
                int selectedId = rgSexe.getCheckedRadioButtonId();
                try{
                    radioSexe =  findViewById(selectedId);
                     sexe = radioSexe.getText().toString().trim();
                }catch (Exception e){
                    e.printStackTrace();
                }




                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);
                //create user
                auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(SignupActivity.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {
                                    Toast.makeText(SignupActivity.this, "Authentication failed." + task.getException(), Toast.LENGTH_SHORT).show();
                                } else {
                                    String currentuser = FirebaseAuth.getInstance().getUid();
                                    writeAgentOnCloudDatabase(currentuser,lastName,firstName,sexe, status);
                                    startActivity(new Intent(SignupActivity.this, AgentInformationActivity.class));
                                    finish();
                                }
                            }
                        });

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }

    private void writeAgentOnCloudDatabase(String userId, String nom, String prenom,String sexe, String status){
        Users users = new Users(nom, prenom,sexe,status);
        mDatabase.child("Users").child(status).child(userId).setValue(users);
    }
}