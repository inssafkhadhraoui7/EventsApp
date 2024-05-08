package com.example.eventsapp;
import static com.example.eventsapp.R.*;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class adapter extends ArrayAdapter<etud> {


    public adapter(@NonNull Context context, ArrayList<etud> etudiantList) {
        super(context,0,etudiantList);

    }
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listitemView = convertView;
        if (listitemView == null) {
            listitemView = LayoutInflater.from(getContext()).inflate(layout.list, parent, false);
        }
        etud etuds = getItem(position);
        TextView title = listitemView.findViewById(id.title);
        TextView date = listitemView.findViewById(id.date);
        title.setText(etuds.getTitle());
        date.setText(etuds.getDate());

        return listitemView;

    }
}