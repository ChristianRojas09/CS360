package com.example.cs360christianrojasoptiontwoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity
{
    //instantiate widgets
    private ListView eventListView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWidgets();
        loadFromDBToMemory();
        setEventAdapter();
        setOnClickListener();

        //call the current date and display it in upper left corner
        String myDate_str = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()).format(new Date());
        TextView dateTimeDisplay = findViewById(R.id.numericDate);
        dateTimeDisplay.setText(myDate_str);
    }


    //method to initialize widgets
    private void initWidgets()
    {
        eventListView = findViewById(R.id.eventListView);
    }

    //method to load data from the database
    private void loadFromDBToMemory()
    {
        SQLiteManager sqLiteManager = SQLiteManager.instanceOfDatabase(this);
        sqLiteManager.populateEventListArray();
    }

    private void setEventAdapter()
    {
        EventAdapter eventAdapter = new EventAdapter(getApplicationContext(), Event.nonDeletedEvents());
        eventListView.setAdapter(eventAdapter);
    }


    private void setOnClickListener()
    {
        //onclick listener for
        eventListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l)
            {
                Event selectedEvent = (Event) eventListView.getItemAtPosition(position);
                Intent editEventIntent = new Intent(getApplicationContext(), newEvent.class);
                editEventIntent.putExtra(Event.EVENT_EDIT_EXTRA, selectedEvent.getId());
                startActivity(editEventIntent);
            }
        });
    }


    //method to initiate the '+' button and call the new event page
    public void newEvent(View view)
    {
        Intent newEventIntent = new Intent(this, newEvent.class);
        startActivity(newEventIntent);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        setEventAdapter();
    }
}