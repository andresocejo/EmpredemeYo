package mx.androidtitlan.semanadelemprendedor.fragments;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.viewpagerindicator.TitlePageIndicator;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import mx.androidtitlan.semanadelemprendedor.Adapter.PageAdapter;
import mx.androidtitlan.semanadelemprendedor.MyActivity;
import mx.androidtitlan.semanadelemprendedor.NavigationDrawerFragment;
import mx.androidtitlan.semanadelemprendedor.R;
import mx.androidtitlan.semanadelemprendedor.util.Event;
import mx.androidtitlan.semanadelemprendedor.util.Speaker;

/**
 * Created by Jhordan on 04/08/14.
 */
public class Explorer extends Fragment implements DialogFilterEvents.UpdateList{

    private String [] DAYS;

    private PagerAdapter mPagerAdapter;
    private ViewPager mviewPager;
    private List<Fragment> listaFragments;
    private TitlePageIndicator mIndicatores;

    public static ArrayList<Event> eventsMain;


    public static Explorer newInstance(int position) {
        Explorer myexplore = new Explorer();
        Bundle extraArguments = new Bundle();
        extraArguments.putInt(NavigationDrawerFragment.ARG_SECTION_NUMBER, position);
        myexplore.setArguments(extraArguments);
        return myexplore;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onResume() {
        super.onResume();
        if(eventsMain.isEmpty()){
            getAllEvents();
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       View v = inflater.inflate(R.layout.explorer_fragment,container,false);
        setHasOptionsMenu(true);

        DAYS = new String[]{"Lunes, 11 de Agosto","Martes, 12 de Agosto","Jueves, 14 de Agosto","Miércoles, 13 de Agosto","Viernes, 15 de Agosto"};

        eventsMain = new ArrayList<Event>();
        listaFragments = new ArrayList<Fragment>();

        for(int i = 0; i<5; i++){
            listaFragments.add(ListEvents.newInstance("Explorer",DAYS[i]));
        }

        // Creamos nuestro Adapter
        mPagerAdapter = new PageAdapter(getActivity().getSupportFragmentManager(),
                listaFragments);


        // Instanciamos nuestro ViewPager
        mviewPager = (ViewPager) v.findViewById(R.id.pager);
        mviewPager.setOffscreenPageLimit(5);


        // Establecemos el Adapter
        mviewPager.setAdapter(mPagerAdapter);

        mIndicatores = (TitlePageIndicator) v.findViewById(R.id.indicators);
        mIndicatores.setViewPager(mviewPager);

        mIndicatores.setFooterColor(Color.parseColor("#00e575"));

        return v;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MyActivity) activity).onSectionAttached(getArguments()
                .getInt(NavigationDrawerFragment.ARG_SECTION_NUMBER));

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_mapa, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.filter_map:
                DialogFilterEvents dialogFilterEvents = new DialogFilterEvents();
                dialogFilterEvents.show(getChildFragmentManager(), null);
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void recreateList(String eco, int id) {
        for (int i = 0; i<5; i++){
            ((ListEvents)listaFragments.get(i)).sortByEco(eco);
        }
    }

    public void getAllEvents() {

        Log.i("Getting","");

        RequestQueue queue = Volley.newRequestQueue(getActivity());
        final String url = "http://se.infoexpo.mx/2014/ae/web/utilerias/ws/google/conferencias";

        JsonObjectRequest getEvents = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject data = response.getJSONObject("data");
                            Iterator<?> keys = data.keys();

                            while (keys.hasNext()) {
                                String key = (String) keys.next();

                                if (data.get(key) instanceof JSONObject) {
                                    try {
                                        JSONObject event = data.getJSONObject(key);
                                        parseEventInfo(event);
                                    } catch (JSONException e) {
                                        Log.e("Error parse", e.toString());
                                    }
                                }
                            }
                            for (int i = 0; i<5; i++){
                                ((ListEvents)listaFragments.get(i)).updateAllConfferences();
                            }

                        } catch (Exception ex) {Log.i("Error response", ex.toString());}
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("Error on response", error.toString());
            }
        }
        );
        getEvents.setRetryPolicy(new DefaultRetryPolicy(22000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(getEvents);
    }

    public void parseEventInfo(JSONObject event) {
        try {
                Event temp = new Event();

                temp.setName(event.getString(getResources().getString(R.string.nombre_conferencia)));
                temp.setPlace(event.getString(getResources().getString(R.string.sala_conferencia)));
                temp.setCategory(event.getString(getResources().getString(R.string.tipo_evento)));
                temp.setDate(event.getString(getResources().getString(R.string.dia_evento)));
                temp.setTimeInit(event.getString(getResources().getString(R.string.hora_inicio)));
                temp.setTimeEnd(event.getString(getResources().getString(R.string.hora_fin)));
                temp.setEco(event.getString(getResources().getString(R.string.eco)));
                temp.setDescription(event.getString(getResources().getString(R.string.descripcion_conferencia)));

                ArrayList<Speaker> speakers = new ArrayList<Speaker>();
                JSONObject jsonSpeakers = event.getJSONObject(getResources().getString(R.string.ponente));
                Iterator<?> iterator = jsonSpeakers.keys();

                while (iterator.hasNext()) {
                    String key = (String) iterator.next();

                    JSONObject jsonSpeaker = jsonSpeakers.getJSONObject(key);
                    Speaker speaker = new Speaker();

                    speaker.setName(jsonSpeaker.getString(getResources().getString(R.string.nombre_ponente)));
                    speaker.setDependency(jsonSpeaker.getString(getResources().getString(R.string.dependencia_ponente)));
                    speaker.setCv(jsonSpeaker.getString(getResources().getString(R.string.cv_ponente)));

                    speaker.setUrl_photo(jsonSpeaker.getString(getResources().getString(R.string.foto_ponente)));

                    speakers.add(speaker);

                }

                temp.setSpeakers(speakers);

                eventsMain.add(temp);

        } catch (JSONException e) {
            Log.e("Json event error", e.toString());
        }
    }
}
