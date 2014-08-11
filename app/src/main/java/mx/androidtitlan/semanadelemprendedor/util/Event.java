package mx.androidtitlan.semanadelemprendedor.util;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Petter on 04/08/2014.
 */
public class Event implements Parcelable {

    String name;
    String date;
    String timeInit;
    String timeEnd;
    String place;
    String category;
    String description;
    String eco;

    int idEco;

    public String getEco() {
        return eco;
    }

    public int getIdEco() {
        return idEco;
    }

    public void setIdEco() {
        if(eco.equals("Red de Apoyo al Emprendedor")){
            idEco = 0;
        }
        else if(eco.equals("Semillero Emprendedor")){
            idEco = 1;
        }
        else if(eco.equals("Alto Impacto y Capital")){
            idEco = 2;
        }
        else if(eco.equals("Distrito Financiero")){
            idEco = 3;
        }
        else if(eco.equals("Fábrica de Empresas")){
            idEco = 4;
        }
        else if(eco.equals("Laboratorio de Innovación")){
            idEco = 5;
        }
        else if(eco.equals("Empresa Digital")){
            idEco = 6;
        }
        else if(eco.equals("Industrias Creativas")){
            idEco = 7;
        }
        else if(eco.equals("MIPYMES en Movimiento")){
            idEco = 8;
        }
        else if (eco.equals("Desarrollo Regional")){
            idEco = 9;
        }
    }

    public void setEco(String eco) {
        this.eco = eco;
        setIdEco();

    }

    ArrayList <Speaker> speakers;

    public Event(Parcel in ){
        String[] data= new String[7];
        speakers = new ArrayList<Speaker>();

        in.readStringArray(data);
        this.name = data[0];
        this.date = data[1];
        this.place = data[2];
        this.timeInit = data[3];
        this.timeEnd = data[4];
        this.category = data[5];
        this.description = data[6];
        in.readList(speakers,null);
    }

    public Event(String name, String date, String timeInit, String timeEnd, String place, String category) {
        this.name = name;
        this.date = date;
        this.place = place;
        this.category = category;
        this.timeInit = timeInit;
        this.timeEnd = timeEnd;

    }

    public Event(){
        speakers = new ArrayList<Speaker>();

    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSpeakers(ArrayList<Speaker> speakers) {
        this.speakers = speakers;
    }

    public ArrayList<Speaker> getSpeakers() {
        return speakers;
    }

    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeStringArray(new String[] {this.name,this.date
                                              ,this.place,this.timeInit,
                                              this.timeEnd,this.category,
                                              this.description});
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Event createFromParcel(Parcel in) {
            return new Event(in);
        }

        public Event[] newArray(int size) {
            return new Event[size];
        }
    };
}
