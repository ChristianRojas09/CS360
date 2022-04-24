package com.example.cs360christianrojasoptiontwoapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class EventAdapter extends ArrayAdapter<Event>{

    public EventAdapter(Context context, List<Event> events) {
        super(context,0,events);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Event event = getItem(position);
        if(convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.event_cell, parent, false);

            TextView name = convertView.findViewById(R.id.cellName);
            TextView desc = convertView.findViewById(R.id.cellDesc);

            name.setText(event.getName());
            desc.setText(event.getDescription());

            return convertView;
    }
}
