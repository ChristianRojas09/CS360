package com.example.cs360christianrojasoptiontwoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Date;

public class newEvent extends AppCompatActivity
{
    private EditText nameEditText, descEditText;
    private Button deleteButton, cancelEventButton;
    private Event selectedEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_event);
        initWidgets();
        checkForEditEvent();

        //cancel button listener
        cancelEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeActivity();

            }
        });
    }

    private void initWidgets()
    {
        nameEditText = findViewById(R.id.nameEditText);
        descEditText = findViewById(R.id.descEditText);
        deleteButton = findViewById(R.id.deleteEventButton);
        cancelEventButton = findViewById(R.id.cancelEventButton);
    }

    //method to call MainActivity when pressing the save button
    private void changeActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void checkForEditEvent()
    {
        Intent previousIntent = getIntent();

        int passedEventID = previousIntent.getIntExtra(Event.EVENT_EDIT_EXTRA, -1);
        selectedEvent = Event.getEventForID(passedEventID);

        if (selectedEvent != null)
        {
            nameEditText.setText(selectedEvent.getName());
            descEditText.setText(selectedEvent.getDescription());
        }
        else
        {
            deleteButton.setVisibility(View.INVISIBLE);
        }
    }

    //method to take data from EditText fields and save to db
    public void saveEvent(View view)
    {
        SQLiteManager sqLiteManager = SQLiteManager.instanceOfDatabase(this);
        String title = String.valueOf(nameEditText.getText());
        String desc = String.valueOf(descEditText.getText());

        if(selectedEvent == null)
        {
            int id = Event.eventArrayList.size();
            Event newEvent = new Event(id, title, desc);
            Event.eventArrayList.add(newEvent);
            sqLiteManager.addEventToDatabase(newEvent);
        }
        else
        {
            selectedEvent.setName(title);
            selectedEvent.setDescription(desc);
            sqLiteManager.updateEventInDB(selectedEvent);
        }

        finish();
    }

    //method to delete an event from the db
    public void deleteEvent(View view)
    {
        selectedEvent.setDeleted(new Date());
        SQLiteManager sqLiteManager = SQLiteManager.instanceOfDatabase(this);
        sqLiteManager.updateEventInDB(selectedEvent);
        finish();
    }


}