package com.example.jhordan.semprende.util;

/**
 * Created by Petter on 07/08/2014.
 */
public class Speaker {

    String name;
    String dependency;
    String cv;
    String url_photo;

    public String getUrl_photo() {
        return url_photo;
    }

    public void setUrl_photo(String url_photo) {
        this.url_photo = url_photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDependency() {
        return dependency;
    }

    public void setDependency(String dependency) {
        this.dependency = dependency;
    }

    public String getCv() {
        return cv;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }

    public Speaker() {

    }
}
