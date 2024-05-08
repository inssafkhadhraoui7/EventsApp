package com.example.eventsapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class ajoutevent extends AppCompatActivity {
    EditText date ,descri,titre,lieu;
    Button btn1;
    FirebaseFirestore db;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajoutevent);
        titre = findViewById(R.id.T);
        date = findViewById(R.id.D);
        descri = findViewById(R.id.Desc);
        lieu = findViewById(R.id.L);
        btn1 = findViewById(R.id.btn1);

        db = FirebaseFirestore.getInstance();
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Title = titre.getText()!= null ? titre.getText().toString():"";
                String Date = date.getText()!= null ?date.getText().toString():"";
                String Lieu = lieu.getText()!= null ?lieu.getText().toString():"";
                String Desc = descri.getText()!= null ?descri.getText().toString():"";
                Map<String, Object> event = new HashMap<>();
                event.put("title", Title);
                event.put("date", Date);
                event.put("lieu", Lieu);
                event.put("description", Desc);

                db.collection("event")
                        .add(event)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(ajoutevent.this, "add successfully", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(ajoutevent.this, "add failed", Toast.LENGTH_SHORT).show();
                            }
                        });
                Intent intent = new Intent(ajoutevent.this, espaceadmin.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public void onBackPressed() {
        // Start the Student activity
        Intent intent = new Intent(ajoutevent.this, espaceadmin.class);
        startActivity(intent);

        // Call the super method to allow the default back button behavior
        super.onBackPressed();
    }
}