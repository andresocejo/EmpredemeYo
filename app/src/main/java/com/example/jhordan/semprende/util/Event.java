package com.example.jhordan.semprende.util;

/**
 * Created by Petter on 04/08/2014.
 */
public class Event {

    String name;
    String date;
    String place;

    public Event(String name, String date, String place) {
        this.name = name;
        this.date = date;
        this.place = place;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}
