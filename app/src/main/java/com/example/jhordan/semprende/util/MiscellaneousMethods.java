package com.example.jhordan.semprende.util;

import android.util.Log;

import java.util.Comparator;

/**
 * Created by Petter on 06/08/2014.
 */
public class MiscellaneousMethods {

    /*Concatena la hora inicial y la hora final de un evento*/
    public static String concatTime (String init, String end){
        String time =  init.substring(0,5);
        time += " a ";
        time += end.substring(0,5);
        return time;
    }

    public static class CustomComparator implements Comparator<Event> {
        @Override
        public int compare(Event ev1, Event ev2) {
            Log.i("Substring 1 ",ev1.getTimeInit().substring(3,5));
            Log.i("Substring 2 ",ev2.getTimeInit().substring(3,5));
             return Integer.parseInt(ev1.getTimeInit().substring(0,2) + ev1.getTimeInit().substring(3,5)) - Integer.parseInt(ev2.getTimeInit().substring(0,2) + (ev2.getTimeInit().substring(3,5)));
        }
    }
}
