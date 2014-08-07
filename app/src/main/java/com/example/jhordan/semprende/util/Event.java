package com.example.jhordan.semprende.util;

/**
 * Created by Petter on 04/08/2014.
 */
public class Event {

    String name;
    String date;
    String timeInit;
    String timeEnd;

    public String getTimeInit() {
        return timeInit;
    }

    public void setTimeInit(String timeInit) {
        this.timeInit = timeInit;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    String place;
    String category;

    public Event(String name, String date, String timeInit, String timeEnd, String place, String category) {
        this.name = name;
        this.date = date;
        this.place = place;
        this.category = category;
        this.timeInit = timeInit;
        this.timeEnd = timeEnd;
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
