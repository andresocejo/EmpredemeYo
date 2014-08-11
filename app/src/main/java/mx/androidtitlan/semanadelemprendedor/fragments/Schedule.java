package mx.androidtitlan.semanadelemprendedor.fragments;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.viewpagerindicator.TitlePageIndicator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import mx.androidtitlan.semanadelemprendedor.Adapter.PageAdapter;
import mx.androidtitlan.semanadelemprendedor.Adapter.SpinnerAdapterBar;
import mx.androidtitlan.semanadelemprendedor.Model.SpinnerModelBar;
import mx.androidtitlan.semanadelemprendedor.MyActivity;
import mx.androidtitlan.semanadelemprendedor.NavigationDrawerFragment;
import mx.androidtitlan.semanadelemprendedor.R;
import mx.androidtitlan.semanadelemprendedor.util.Event;
import mx.androidtitlan.semanadelemprendedor.util.Speaker;


/**
 * Created by Jhordan on 04/08/14.
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class Schedule extends android.support.v4.app.Fragment implements android.app.ActionBar.OnNavigationListener {

    private ArrayList<SpinnerModelBar> navSpinner;
    private String [] DAYS;

    public static ArrayList<Event> eventsUser;

    // Navigation adapter
    private SpinnerAdapterBar adapter;

    private PagerAdapter mPagerAdapter;
    private ViewPager mviewPager;
    private List<Fragment> listaFragments;
    private TitlePageIndicator mIndicatores;

    private JsonObjectRequest getEvents;


    public static Schedule newInstance(int position) {
        Schedule myschedule = new Schedule();
        Bundle extraArguments = new Bundle();
        extraArguments.putInt(NavigationDrawerFragment.ARG_SECTION_NUMBER, position);
        myschedule.setArguments(extraArguments);
        return myschedule;
    }


    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.schedule_fragment, container, false);
        SharedPreferences prefs = getActivity().getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        eventsUser = new ArrayList<Event>();

        listaFragments = new ArrayList<Fragment>();
        listaFragments.add(ListEvents.newInstance("Schedule",DAYS[0]));
        listaFragments.add(ListEvents.newInstance("Schedule",DAYS[1]));
        listaFragments.add(ListEvents.newInstance("Schedule",DAYS[2]));
        listaFragments.add(ListEvents.newInstance("Schedule",DAYS[3]));
        listaFragments.add(ListEvents.newInstance("Schedule",DAYS[4]));


        // Creamos nuestro Adapter
        mPagerAdapter = new PageAdapter(getActivity().getSupportFragmentManager(),
                listaFragments);


        // Instanciamos nuestro ViewPager
        mviewPager = (ViewPager) v.findViewById(R.id.pager);


        // Establecemos el Adapter
        mviewPager.setAdapter(mPagerAdapter);
        mviewPager.setOffscreenPageLimit(5);

        //mIndicator = (TabPageIndicator) v.findViewById(R.id.indicator);
        //mIndicator.setViewPager(mviewPager);

        mIndicatores = (TitlePageIndicator) v.findViewById(R.id.indicators);
        mIndicatores.setViewPager(mviewPager);

        mIndicatores.setFooterColor(Color.parseColor("#00e575"));

        if(eventsUser.isEmpty()){
            try {
                getUserEvents(prefs.getString("email_usr", ""),prefs.getString("gafete_usr", ""));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return v;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void spinnerBar() {

        android.app.ActionBar bar;

        bar = getActivity().getActionBar();


        bar.setDisplayShowTitleEnabled(false);

        bar.setTitle("");


        bar.setNavigationMode(android.app.ActionBar.NAVIGATION_MODE_LIST);

        navSpinner = new ArrayList<SpinnerModelBar>();
        navSpinner.add(new SpinnerModelBar("Conference", 0));
        navSpinner.add(new SpinnerModelBar("Conference", R.drawable.ic_launcher));
        navSpinner.add(new SpinnerModelBar("Conference", R.drawable.ic_launcher));
        navSpinner.add(new SpinnerModelBar("Conference", R.drawable.ic_launcher));


        // title drop down adapter
        adapter = new SpinnerAdapterBar(getActivity().getApplicationContext(), navSpinner);


        // assigning the spinner navigation
        bar.setListNavigationCallbacks(adapter, new android.app.ActionBar.OnNavigationListener() {
            @Override
            public boolean onNavigationItemSelected(int itemPosition, long itemId) {

                String hola = Integer.toString(itemPosition);

                Toast.makeText(getActivity(), hola, Toast.LENGTH_SHORT).show();
                return false;
            }
        });


    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MyActivity) activity).onSectionAttached(getArguments()
                .getInt(NavigationDrawerFragment.ARG_SECTION_NUMBER));

        DAYS = getResources().getStringArray(R.array.days_events);

    }

    public void getUserEvents(final String email, final String gafete) throws JSONException {

        Log.i("Getting","");

        RequestQueue queue = Volley.newRequestQueue(getActivity());
        final String url = "http://se.infoexpo.mx/2014/ae/web/utilerias/ws/google/get_attendee?idVisitante=" + gafete + "&email=" + email;
        getEvents = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONObject data = response.getJSONObject("data");
                            JSONArray agenda = data.getJSONArray("Agenda");

                            for (int i = 0; i < agenda.length(); i++) {
                                JSONObject object = agenda.getJSONObject(i);
                                parseEventInfo(object);

                            }

                            for (int i = 0; i<5; i++){
                                ((ListEvents)listaFragments.get(i)).updateUserConfference();
                            }

                        } catch (JSONException ex) {
                            Log.e("Json error", ex.toString());
                        } catch (NullPointerException e) {
                            Log.e("Json error", e.toString());

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), "Parece que hay un problema con el servidor, intenta más tarde", Toast.LENGTH_SHORT).show();
                    }
                }
        );

        getEvents.setRetryPolicy(new DefaultRetryPolicy(30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        queue.add(getEvents);
    }

    @Override
    public void onStop() {
        super.onStop();

        if (getEvents != null) {
            getEvents.cancel();
        }
    }

    public void parseEventInfo(JSONObject event) {
        try {

            if (event.length()==0)
                return;
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

            eventsUser.add(temp);

        } catch (JSONException ex) {
            Log.e("Json error", ex.toString());
        } catch (NullPointerException e) {
            Log.e("Json error", e.toString());

        }
    }


    @Override
    public boolean onNavigationItemSelected(int itemPosition, long itemId) {
        return false;
    }
}
