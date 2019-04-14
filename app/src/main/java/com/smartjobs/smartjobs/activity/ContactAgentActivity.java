package com.smartjobs.smartjobs.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

import com.smartjobs.smartjobs.R;



public class ContactAgentActivity extends AppCompatActivity {
    Button paie ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_agent);
        paie = findViewById(R.id.btn_paiement);

        paie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ContactAgentActivity.this,WebviewActivity.class);
                startActivity(i);

            }
        });
    }
}
