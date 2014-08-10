package mx.androidtitlan.semanadelemprendedor.Model;

import java.util.List;

import mx.androidtitlan.semanadelemprendedor.util.PointStaff;

/**
 * Created by SAIC on 10/08/2014.
 */
public class PointStaffModel{

    private List<PointStaff> pointStaffs;
    private int maker_map;

    public PointStaffModel(List<PointStaff> pointStaffs, int maker_map) {
        this.pointStaffs = pointStaffs;
        this.maker_map = maker_map;
    }

    public List<PointStaff> getPointStaffs() {
        return pointStaffs;
    }

    public int getMaker_map() {
        return maker_map;
    }
}
