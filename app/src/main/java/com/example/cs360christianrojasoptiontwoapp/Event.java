package com.example.cs360christianrojasoptiontwoapp;

import java.util.ArrayList;
import java.util.Date;

public class Event {

    public static ArrayList<Event> eventArrayList = new ArrayList<>();
    public static String EVENT_EDIT_EXTRA = "eventEdit";

    private int id;
    private String name;
    private String desc;
    private Date deleted;

    //constructors
    public Event(int id, String name, String desc, Date deleted)
    {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.deleted = deleted;
    }

    public Event(int id, String name, String desc)
    {
        this.id = id;
        this.name = name;
        this.desc = desc;
    }

    public static Event getEventForID(int passedEventID)
    {
        for (Event event : eventArrayList)
        {
            if(event.getId() == passedEventID)
                return event;
        }

        return null;
    }

    public static ArrayList<Event> nonDeletedEvents()
    {
        ArrayList<Event> nonDeleted = new ArrayList<>();
        for(Event event : eventArrayList)
        {
            if(event.getDeleted() == null)
                nonDeleted.add(event);
        }

        return nonDeleted;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    //setters and getters
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDescription()
    {
        return desc;
    }

    public void setDescription(String description)
    {
        this.desc = description;
    }

    public Date getDeleted()
    {
        return deleted;
    }

    public void setDeleted(Date deleted)
    {
        this.deleted = deleted;
    }
}
