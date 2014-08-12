package mx.androidtitlan.semanadelemprendedor.Model;

/**
 * Created by Jhordan on 06/08/14.
 */
public class SpeakerModelConference {

    private String pictureSpeaker;
    private String titleConference;
    private String hourConference;
    private String colorConference;

    public SpeakerModelConference() {
    }

    public SpeakerModelConference(String pictureSpeaker, String titleConference, String hourConference,String colorConference) {

        this.pictureSpeaker = pictureSpeaker;
        this.titleConference = titleConference;
        this.hourConference = hourConference;
        this.colorConference = colorConference;
    }

    public String getPictureSpeaker() {
        return pictureSpeaker;
    }

    public void setPictureSpeaker(String pictureSpeaker) {
        this.pictureSpeaker = pictureSpeaker;
    }

    public String getTitleConference() {
        return titleConference;
    }

    public void setTitleConference(String titleConference) {
        this.titleConference = titleConference;
    }

    public String getHourConference() {
        return hourConference;
    }

    public void setHourConference(String hourConference) {
        this.hourConference = hourConference;
    }

    public String getColorConference() {
        return colorConference;
    }

    public void setColorConference(String colorConference) {
        this.colorConference = colorConference;
    }
}
