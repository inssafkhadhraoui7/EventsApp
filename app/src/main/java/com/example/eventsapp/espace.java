package com.example.eventsapp;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


public class espace extends AppCompatActivity {

    ListView list;
    FirebaseFirestore db;
    ArrayList<String> array = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_espace);
        list=findViewById(R.id.list);
        db=FirebaseFirestore.getInstance();
        db.collection("event").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot DocumentSnapshot, @Nullable FirebaseFirestoreException error) {
                for (com.google.firebase.firestore.DocumentSnapshot snapshot : DocumentSnapshot) {
                    array.add(snapshot.getString("title"));
                }

                ArrayAdapter ad = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_selectable_list_item,array);
                ad.notifyDataSetChanged();
                list.setAdapter(ad);
                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        System.out.println(position);
                        ShowMyDialog(position);
                    }
                });
            }
        });

    }

    private void ShowMyDialog(int pos) {
        Dialog dialog = new Dialog(espace.this);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialog);
        TextView dte = dialog.findViewById(R.id.dat);
        TextView l = dialog.findViewById(R.id.lieu);
        TextView descr = dialog.findViewById(R.id.desc);
        Button insc = dialog.findViewById(R.id.p);

        db.collection("event").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult().getDocuments().get(pos);
                    l.setText(document.getString("lieu"));
                    dte.setText(document.getString("date"));
                    descr.setText(document.getString("description"));
                    insc.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent i = new Intent(espace.this, postulate.class);
                            startActivity(i);
                        }
                    });
                    dialog.show();
                }

            } });}
    @Override
    public void onBackPressed() {
        // Start the Student activity
        Intent intent = new Intent(espace.this, welcom.class);
        startActivity(intent);

        // Call the super method to allow the default back button behavior
        super.onBackPressed();
    }}