package com.example.jhordan.semprende.util;

/**
 * Created by SAIC on 08/08/2014.
 */
public class PointStaff {

    private String title;
    private String description;
    private double latitud;
    private double longitud;
    private int idIcon;

    public PointStaff(String title, String description, double latitud, double longitud, int idIcon) {
        this.title = title;
        this.description = description;
        this.latitud = latitud;
        this.longitud = longitud;
        this.idIcon = idIcon;
    }

    public int getIdIcon() {
        return idIcon;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public double getLatitud() {
        return latitud;
    }

    public double getLongitud() {
        return longitud;
    }
}
