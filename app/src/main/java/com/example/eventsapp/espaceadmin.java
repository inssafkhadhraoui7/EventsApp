package com.example.eventsapp;

import static android.content.ContentValues.TAG;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class espaceadmin extends AppCompatActivity {
    Button btn;
    ListView list;
    FirebaseFirestore db=FirebaseFirestore.getInstance();
    ArrayList<String> array = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_espaceadmin);
        list=findViewById(R.id.list);
        btn=findViewById(R.id.btnA);
        db.collection("event").orderBy("date", Query.Direction.DESCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot DocumentSnapshot, @Nullable FirebaseFirestoreException error) {
                for (com.google.firebase.firestore.DocumentSnapshot snapshot : DocumentSnapshot){
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
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(espaceadmin.this, ajoutevent.class);
                startActivity(a);
            }
        });
    }
    private void ShowMyDialog(int pos) {
        Dialog dialog = new Dialog(espaceadmin.this);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialogadmin);
        TextView date = dialog.findViewById(R.id.dat);
        TextView lieu = dialog.findViewById(R.id.lieu);
        TextView dscr = dialog.findViewById(R.id.desc);
        Button remove = dialog.findViewById(R.id.sup);
        Button inscription = dialog.findViewById(R.id.inscr);
        db.collection("event").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult().getDocuments().get(pos);
                    lieu.setText(document.getString("lieu"));
                    date.setText(document.getString("date"));
                    dscr.setText(document.getString("description"));

                    remove.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            db.collection("event").document(document.getId()).delete()
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            Toast.makeText(espaceadmin.this, "event deleted!", Toast.LENGTH_SHORT).show();
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(espaceadmin.this, "failed to deleted event", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                            Intent i = new Intent(espaceadmin.this,espaceadmin.class);
                            startActivity(i);
                        }
                    });
                    inscription.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent i = new Intent(espaceadmin.this, etudinscri.class);
                            startActivity(i);
                        }
                    });
                } else {
                    Log.d(TAG, "Error getting documents: ", task.getException());
                }
            }
        });

        dialog.show();
    }
    @Override
    public void onBackPressed() {
        // Start the Student activity
        Intent intent = new Intent(espaceadmin.this, welcom.class);
        startActivity(intent);

        // Call the super method to allow the default back button behavior
        super.onBackPressed();
    }

}