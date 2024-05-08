package com.example.eventsapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class welcom extends AppCompatActivity {
Button btn,btn1;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcom);
        btn=findViewById(R.id.etu);
        btn1=findViewById(R.id.adm);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(welcom.this, espace.class);
                startActivity(intent);
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(welcom.this, espaceadmin.class);
                startActivity(intent);
            }
        });

    }
    @Override
    public void onBackPressed() {
        // Start the Student activity
        Intent intent = new Intent(welcom.this, MainActivity.class);
        startActivity(intent);

        // Call the super method to allow the default back button behavior
        super.onBackPressed();
    }
}