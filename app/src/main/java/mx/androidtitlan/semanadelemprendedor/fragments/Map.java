package mx.androidtitlan.semanadelemprendedor.fragments;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import mx.androidtitlan.semanadelemprendedor.MyActivity;
import mx.androidtitlan.semanadelemprendedor.NavigationDrawerFragment;
import mx.androidtitlan.semanadelemprendedor.R;
import mx.androidtitlan.semanadelemprendedor.util.PointStaff;

/**
 * Created by Jhordan on 04/08/14.
 */
public class Map extends Fragment {

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

//            getActivity().setPsrogressBarIndeterminateVisibility(true);
//            progressVisible = true;
//            encontrado = false;

            mapa = mapView.getMap();
            mapa.setMyLocationEnabled(true);

            mapa.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(19.4400144, -99.22433965), 17));

            new ObtenerPuntos().execute();
        }

        return view;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
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


    private class ObtenerPuntos extends AsyncTask<Void, PointStaff, Void> {

        private List<PointStaff> staffs;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            BufferedReader reader = null;

            if (getActivity() != null)
                reader = new BufferedReader(new InputStreamReader(
                        getResources().openRawResource(R.raw.points)));
            else
                return null;
            String cadena;
            int posición = 0;
            String split[];

            String nombre = "";
            String descripcion = "";
            double latitud = 0;
            double longitud = 0;
            int icon = 0;


            try {
                while ((cadena = reader.readLine()) != null) {

                    if (cadena.contains("<name>")) {
                        split = cadena.split(">");
                        split = split[1].split("<");
                        nombre = split[0];
                    }
                    if (cadena.contains("<description>")) {
                        split = cadena.split("\\[");
                        split = split[2].split("]");
                        descripcion = split[0];
                    }

                    if (cadena.contains("<styleUrl>")) {
                        if (cadena.contains("icon-503-DB4436")) {
                            icon = 1;
                        } else if (cadena.contains("icon-503-7C3592")) {
                            icon = 2;
                        } else if (cadena.contains("icon-503-FFFFFF")) {
                            icon = 3;
                        } else if (cadena.contains("icon-503-4186F0")) {
                            icon = 4;
                        } else if (cadena.contains("icon-503-009D57")) {
                            icon = 5;
                        } else if (cadena.contains("icon-503-EE9C96")) {
                            icon = 6;
                        } else if (cadena.contains("icon-503-3F5BA9")) {
                            icon = 7;
                        } else if (cadena.contains("icon-503-F4B400")) {
                            icon = 8;
                        } else if (cadena.contains("icon-503-FFDD5E")) {
                            icon = 9;
                        } else if (cadena.contains("icon-503-A61B4A")) {
                            icon = 10;
                        }
                    }

                    if (cadena.contains("<coordinates>")) {
                        split = cadena.split(">");
                        split = split[1].split("<");
                        split = split[0].split(",");
                        latitud = Double.parseDouble(split[0]);
                        longitud = Double.parseDouble(split[1]);

                        publishProgress(new PointStaff(nombre, descripcion, longitud, latitud, icon));
                        nombre = "";
                        descripcion = "";
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(PointStaff... values) {
            super.onProgressUpdate(values);

            int idicon = 1;
            if (getActivity() != null) {

                switch (values[0].getIdIcon()) {
                    case 1:
                        idicon = R.drawable.mark_01;
                        break;
                    case 2:
                        idicon = R.drawable.mark_02;
                        break;
                    case 3:
                        idicon = R.drawable.mark_03;
                        break;
                    case 4:
                        idicon = R.drawable.mark_04;
                        break;
                    case 5:
                        idicon = R.drawable.mark_05;
                        break;
                    case 6:
                        idicon = R.drawable.mark_06;
                        break;
                    case 7:
                        idicon = R.drawable.mark_07;
                        break;
                    case 8:
                        idicon = R.drawable.mark_08;
                        break;
                    case 9:
                        idicon = R.drawable.mark_09;
                        break;
                    case 10:
                        idicon = R.drawable.mark_10;
                        break;
                }

                mapa.addMarker(new MarkerOptions().title(values[0].getTitle()).snippet(values[0].getDescription()).
                        position(new LatLng(values[0].getLatitud(), values[0].getLongitud())).icon(BitmapDescriptorFactory.fromResource(idicon)));
            } else {
                cancel(true);
            }

        }

    }


}
