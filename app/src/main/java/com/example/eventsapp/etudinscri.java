package com.example.eventsapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class etudinscri extends AppCompatActivity {

    ListView list ;
    FirebaseFirestore db;
    ArrayList<String> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etudinscri);
        list= findViewById(R.id.list);
        db=FirebaseFirestore.getInstance();
        db.collection("users").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot DocumentSnapshot, @Nullable FirebaseFirestoreException error) {
                for (com.google.firebase.firestore.DocumentSnapshot snapshot : DocumentSnapshot ){
                    arrayList.add(snapshot.getString("Nom"));
                }
                ArrayAdapter adp = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_selectable_list_item,arrayList);
                adp.notifyDataSetChanged();
                list.setAdapter(adp);
            }
        });


    }
    @Override
    public void onBackPressed() {
        // Start the Student activity
        Intent intent = new Intent(etudinscri.this, espaceadmin.class);
        startActivity(intent);

        // Call the super method to allow the default back button behavior
        super.onBackPressed();
    }
}