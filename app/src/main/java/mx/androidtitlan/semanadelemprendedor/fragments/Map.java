package mx.androidtitlan.semanadelemprendedor.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import mx.androidtitlan.semanadelemprendedor.Model.AreaStaffModel;
import mx.androidtitlan.semanadelemprendedor.Model.PointStaffModel;
import mx.androidtitlan.semanadelemprendedor.MyActivity;
import mx.androidtitlan.semanadelemprendedor.NavigationDrawerFragment;
import mx.androidtitlan.semanadelemprendedor.R;
import mx.androidtitlan.semanadelemprendedor.util.FragPropertyInterface;
import mx.androidtitlan.semanadelemprendedor.util.PointStaff;

/**
 * Created by Jhordan on 04/08/14.
 */
public class Map extends Fragment implements DialogFilterMap.UpdateMap, GoogleMap.OnCameraChangeListener, FragPropertyInterface {


    public static Map newInstance(int position) {
        Map mymap = new Map();
        Bundle extraArguments = new Bundle();
        extraArguments.putInt(NavigationDrawerFragment.ARG_SECTION_NUMBER, position);
        mymap.setArguments(extraArguments);
        return mymap;
    }

    private View view;
    private GoogleMap mapa;
    private MapView mapView;
    private boolean recreaMap;
    private List<Marker> markers;

    private boolean isDrawerOpen;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setRetainInstance(true);

        setHasOptionsMenu(true);

        if (view != null) {
            ViewGroup group = (ViewGroup) view.getParent();

            if (group != null) {
                group.removeView(view);
            }

            mapView = (MapView) view.findViewById(R.id.mapview);

        } else {
            view = inflater.inflate(R.layout.map_fragment, container, false);
            mapView = (MapView) view.findViewById(R.id.mapview);
            mapView.onCreate(savedInstanceState);
            MapsInitializer.initialize(getActivity());
        }

        if (mapa == null) {

            recreaMap = false;
            mapa = mapView.getMap();
            mapa.setMyLocationEnabled(true);
            new ObtenerAreas().execute();

            mapa.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(19.4400144, -99.22433965), 17));

            mapa.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
                @Override
                public void onMapLoaded() {
                    recreateMap();
                }
            });

            mapa.setOnCameraChangeListener(this);
        }

        return view;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        if (!isDrawerOpen) {
            inflater.inflate(R.menu.menu_mapa, menu);
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.filter_map:
                DialogFilterMap dialogFilterMap = new DialogFilterMap();
                dialogFilterMap.show(getChildFragmentManager(), null);

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }


    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MyActivity) activity).onSectionAttached(getArguments()
                .getInt(NavigationDrawerFragment.ARG_SECTION_NUMBER));
    }

    @Override
    public void recreateMap() {

        recreaMap = true;
        SharedPreferences preferences = getActivity()
                .getSharedPreferences(DialogFilterMap.FIRTER_MAP,
                        Context.MODE_PRIVATE);
        centerMap(preferences.getInt(DialogFilterMap.ECOSISTEMA, 0));


    }


    private void centerMap(int typeCente) {
        LatLng latLngNor = null;
        LatLng latLngSur = null;

        switch (typeCente) {
            case 0:
                latLngNor = new LatLng(19.4409373, -99.2226025);
                latLngSur = new LatLng(19.4391162, -99.2260666);
                break;
            case 1:
                latLngNor = new LatLng(19.4390807, -99.2246452);
                latLngSur = new LatLng(19.4384116, -99.2253989);
                break;
            case 2:
                latLngNor = new LatLng(19.4391326, -99.2251106);
                latLngSur = new LatLng(19.4387506, -99.2256349);
                break;
            case 3:
                latLngNor = new LatLng(19.439278, -99.2255759);
                latLngSur = new LatLng(19.4390731, -99.2257503);
                break;
            case 4:
                latLngNor = new LatLng(19.4396372, -99.2249241);
                latLngSur = new LatLng(19.4389732, -99.2255102);
                break;
            case 5:
                latLngNor = new LatLng(19.4396928, -99.2247551);
                latLngSur = new LatLng(19.4392135, -99.2252688);
                break;
            case 6:
                latLngNor = new LatLng(19.4398281, -99.2246439);
                latLngSur = new LatLng(19.4394525, -99.2249992);
                break;
            case 7:
                latLngNor = new LatLng(19.4402316, -99.2242255);
                latLngSur = new LatLng(19.4395322, -99.2249242);
                break;
            case 8:
                latLngNor = new LatLng(19.4403163, -99.2239693);
                latLngSur = new LatLng(19.4397902, -99.2245647);
                break;
            case 9:
                latLngNor = new LatLng(19.4408449, -99.2233416);
                latLngSur = new LatLng(19.4399976, -99.2241021);
                break;
            case 10:
                latLngNor = new LatLng(19.4412609, -99.2228791);
                latLngSur = new LatLng(19.4406059, -99.22353);
                break;
        }

        mapa.animateCamera(CameraUpdateFactory.newLatLngBounds(new LatLngBounds(latLngSur, latLngNor), 50));
    }

    @Override
    public void onCameraChange(CameraPosition cameraPosition) {


        if (recreaMap) {

            if (markers != null) {
                for (Marker marker : markers) {
                    marker.remove();
                }
            }
            markers = new ArrayList<Marker>();
            SharedPreferences preferences = getActivity()
                    .getSharedPreferences(DialogFilterMap.FIRTER_MAP,
                            Context.MODE_PRIVATE);
            Integer num_eco = preferences.getInt(DialogFilterMap.ECOSISTEMA, 0);


            if (num_eco == 0) {
                new ObtenerPuntos().execute(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
            } else {
                new ObtenerPuntos().execute(num_eco);
            }

            recreaMap = false;
        }
        ;

    }

    @Override
    public void setDrawerOpen(boolean isDrawerOpen) {

        this.isDrawerOpen = isDrawerOpen;
    }

    private class ObtenerPuntos extends AsyncTask<Integer, PointStaffModel, Void> {


        @Override
        protected Void doInBackground(Integer... integers) {

            BufferedReader reader = null;
            Gson gson = new Gson();
            List<PointStaff> pointStaffs = null;
            int maker_map = 0;


            for (int i = 0; i < integers.length; i++) {

                if (getActivity() == null) {
                    return null;
                }

                switch (integers[i]) {
                    case 1:
                        reader = new BufferedReader(new InputStreamReader(
                                getResources().openRawResource(R.raw.ecosistema_1)));
                        maker_map = R.drawable.mark_01;
                        break;
                    case 2:
                        reader = new BufferedReader(new InputStreamReader(
                                getResources().openRawResource(R.raw.ecosistema_2)));
                        maker_map = R.drawable.mark_02;
                        break;
                    case 3:
                        reader = new BufferedReader(new InputStreamReader(
                                getResources().openRawResource(R.raw.ecosistema_3)));
                        maker_map = R.drawable.mark_03;
                        break;
                    case 4:
                        reader = new BufferedReader(new InputStreamReader(
                                getResources().openRawResource(R.raw.ecosistema_4)));
                        maker_map = R.drawable.mark_04;
                        break;
                    case 5:
                        reader = new BufferedReader(new InputStreamReader(
                                getResources().openRawResource(R.raw.ecosistema_5)));
                        maker_map = R.drawable.mark_05;
                        break;
                    case 6:
                        reader = new BufferedReader(new InputStreamReader(
                                getResources().openRawResource(R.raw.ecosistema_6)));
                        maker_map = R.drawable.mark_06;
                        break;
                    case 7:
                        reader = new BufferedReader(new InputStreamReader(
                                getResources().openRawResource(R.raw.ecosistema_7)));
                        maker_map = R.drawable.mark_07;
                        break;
                    case 8:
                        reader = new BufferedReader(new InputStreamReader(
                                getResources().openRawResource(R.raw.ecosistema_8)));
                        maker_map = R.drawable.mark_08;
                        break;
                    case 9:
                        reader = new BufferedReader(new InputStreamReader(
                                getResources().openRawResource(R.raw.ecosistema_9)));
                        maker_map = R.drawable.mark_09;
                        break;
                    case 10:
                        reader = new BufferedReader(new InputStreamReader(
                                getResources().openRawResource(R.raw.ecosistema_10)));
                        maker_map = R.drawable.mark_10;
                        break;
                }

                pointStaffs = gson.fromJson(reader, new TypeToken<List<PointStaff>>() {
                }.getType());

                publishProgress(new PointStaffModel(pointStaffs, maker_map));

            }

            return null;
        }


        @Override
        protected void onProgressUpdate(PointStaffModel... values) {
            super.onProgressUpdate(values);

            if (getActivity() == null || values == null || values.length == 0 || values[0] == null) {
                return;
            }

            int maker_map = values[0].getMaker_map();
            for (PointStaff pointStaff : values[0].getPointStaffs()) {
                markers.add(
                        mapa.addMarker(new MarkerOptions().title(pointStaff.getTitle()).snippet(pointStaff.getDescription())
                                .position(new LatLng(pointStaff.getLatitud(), pointStaff.getLongitud())).icon(BitmapDescriptorFactory.fromResource(maker_map)))
                );
                ;
            }

        }
    }


    private class ObtenerAreas extends AsyncTask<Void, AreaStaffModel, Void> {
        @Override
        protected Void doInBackground(Void... voids) {

            BufferedReader reader = null;
            Gson gson = new Gson();
            List<List<Double>> areaStaffs;
            int color_staff = 0;

            for (int i = 0; i < 10; i++) {
                if (getActivity() == null) {
                    return null;
                }
                switch (i) {
                    case 0:
                        reader = new BufferedReader(new InputStreamReader(
                                getResources().openRawResource(R.raw.ecosistema_area_1)));
                        color_staff = 1;
                        break;
                    case 1:
                        reader = new BufferedReader(new InputStreamReader(
                                getResources().openRawResource(R.raw.ecosistema_area_2)));
                        color_staff = 2;
                        break;
                    case 2:
                        reader = new BufferedReader(new InputStreamReader(
                                getResources().openRawResource(R.raw.ecosistema_area_3)));
                        color_staff = 3;
                        break;
                    case 3:
                        reader = new BufferedReader(new InputStreamReader(
                                getResources().openRawResource(R.raw.ecosistema_area_4)));
                        color_staff = 4;
                        break;
                    case 4:
                        reader = new BufferedReader(new InputStreamReader(
                                getResources().openRawResource(R.raw.ecosistema_area_5)));
                        color_staff = 5;
                        break;
                    case 5:
                        reader = new BufferedReader(new InputStreamReader(
                                getResources().openRawResource(R.raw.ecosistema_area_6)));
                        color_staff = 6;
                        break;
                    case 6:
                        reader = new BufferedReader(new InputStreamReader(
                                getResources().openRawResource(R.raw.ecosistema_area_7)));
                        color_staff = 7;
                        break;
                    case 7:
                        reader = new BufferedReader(new InputStreamReader(
                                getResources().openRawResource(R.raw.ecosistema_area_8)));
                        color_staff = 8;
                        break;
                    case 8:
                        reader = new BufferedReader(new InputStreamReader(
                                getResources().openRawResource(R.raw.ecosistema_area_9)));
                        color_staff = 9;
                        break;
                    case 9:
                        reader = new BufferedReader(new InputStreamReader(
                                getResources().openRawResource(R.raw.ecosistema_area_10)));
                        color_staff = 10;
                        break;
                }

                areaStaffs = gson.fromJson(reader, new TypeToken<List<List<Double>>>() {
                }.getType());


                publishProgress(new AreaStaffModel(areaStaffs, color_staff));

            }

            return null;
        }


        @Override
        protected void onProgressUpdate(AreaStaffModel... values) {
            super.onProgressUpdate(values);


            if (getActivity() == null || values == null || values.length == 0 || values[0] == null) {
                return;
            }

            int idColor = values[0].getColor();
            int colorStroke = 0;
            int colorFill = 0;


            switch (idColor) {
                case 1:
                    colorFill = Color.rgb(221, 18, 69);
                    colorStroke = Color.argb(127, 221, 18, 69);
                    break;
                case 2:
                    colorFill = Color.rgb(144, 145, 192);
                    colorStroke = Color.argb(127, 144, 145, 192);
                    break;
                case 3:
                    colorFill = Color.rgb(65, 65, 66);
                    colorStroke = Color.argb(127, 65, 65, 66);
                    break;
                case 4:
                    colorFill = Color.rgb(78, 195, 199);
                    colorStroke = Color.argb(127, 78, 195, 199);
                    break;
                case 5:
                    colorFill = Color.rgb(233, 83, 80);
                    colorStroke = Color.argb(127, 233, 83, 80);
                    break;
                case 6:
                    colorFill = Color.rgb(187, 189, 191);
                    colorStroke = Color.argb(127, 187, 189, 191);
                    break;
                case 7:
                    colorFill = Color.rgb(108, 85, 164);
                    colorStroke = Color.argb(127, 108, 85, 164);
                    break;
                case 8:
                    colorFill = Color.rgb(162, 185, 98);
                    colorStroke = Color.argb(127, 162, 185, 98);
                    break;
                case 9:
                    colorFill = Color.rgb(243, 154, 69);
                    colorStroke = Color.argb(127, 243, 154, 69);
                    break;
                case 10:
                    colorFill = Color.rgb(232, 96, 75);
                    colorStroke = Color.argb(127, 232, 96, 75);
                    break;
            }


            for (List<Double> pointStaff : values[0].getAreaStaffs()) {
                PolygonOptions polygonOptions = new PolygonOptions();
                for (int i = 0; i < pointStaff.size(); i += 2) {
                    polygonOptions.add(new LatLng(pointStaff.get(i + 1), pointStaff.get(i)));
                }
                polygonOptions.strokeColor(colorFill).fillColor(colorStroke);


                mapa.addPolygon(polygonOptions);
            }


        }

    }


}
