package mx.androidtitlan.semanadelemprendedor.Model;

/**
 * Created by Jhordan on 09/08/14.
 */
public class StreamingModel {

    private String title;
    private  int picture;
    private String state;

    public StreamingModel(){}

    public StreamingModel(String title,int picture,String state){

        this.title = title;
        this.picture = picture;
        this.state = state;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPicture() {
        return picture;
    }

    public void setPicture(int picture) {
        this.picture = picture;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
