package com.example.jhordan.semprende.Model;

/**
 * Created by Jhordan on 07/08/14.
 */
public class SpinnerModelBar {

    private String sesiones;
    private int icon;

    public SpinnerModelBar(String sesiones, int icon) {
        this.sesiones = sesiones;
        this.icon = icon;
    }

    public String getSesiones() {
        return sesiones;
    }

    public void setSesiones(String sesiones) {
        this.sesiones = sesiones;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
