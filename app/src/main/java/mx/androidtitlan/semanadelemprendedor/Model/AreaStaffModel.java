package mx.androidtitlan.semanadelemprendedor.Model;

import java.util.List;

/**
 * Created by SAIC on 10/08/2014.
 */
public class AreaStaffModel {

    private List<List<Double>> areaStaffs;
    private int  color;


    public AreaStaffModel(List<List<Double>> pointStaffs, int color) {
        this.areaStaffs = pointStaffs;
        this.color = color;
    }


    public List<List<Double>> getAreaStaffs() {
        return areaStaffs;
    }

    public int getColor() {
        return color;
    }
}
