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

public class postulate extends AppCompatActivity {
    EditText n,p,c,e,f;
    Button btn;
    FirebaseFirestore db;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postulate);
        n=findViewById(R.id.nom);
        p=findViewById(R.id.prenom);
        c=findViewById(R.id.i);
        e=findViewById(R.id.mail);
        f=findViewById(R.id.f);
        btn=findViewById(R.id.btn1);

        db = FirebaseFirestore.getInstance();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Cin= c.getText().toString();
                String Nom = n.getText().toString();
                String Prenom = p.getText().toString();
                String Email = e.getText().toString();
                String Number = f.getText().toString();
                Map<String, Object> user = new HashMap<>();
               user.put("Cin", Cin);
                user.put("Nom", Nom);
                user.put("Prenom", Prenom);
                user.put("Email", Email);
                user.put("Number", Number);

                db.collection("users")
                        .add(user)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(postulate.this, " successfully postulated", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(postulate.this, "add failed", Toast.LENGTH_SHORT).show();
                            }


                        });

                Intent intent = new Intent(postulate.this, espace.class);
                startActivity(intent);


            }
        });
    }
    @Override
    public void onBackPressed() {
        // Start the Student activity
        Intent intent = new Intent(postulate.this, espace.class);
        startActivity(intent);

        // Call the super method to allow the default back button behavior
        super.onBackPressed();
    }}