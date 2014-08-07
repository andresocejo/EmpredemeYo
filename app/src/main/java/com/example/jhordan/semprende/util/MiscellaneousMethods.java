package com.example.jhordan.semprende.util;

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
}
