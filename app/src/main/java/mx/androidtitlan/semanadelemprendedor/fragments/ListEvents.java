package mx.androidtitlan.semanadelemprendedor.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import mx.androidtitlan.semanadelemprendedor.Activities.DetailEventActivity;
import mx.androidtitlan.semanadelemprendedor.Adapter.EventsAdapter;
import mx.androidtitlan.semanadelemprendedor.R;
import mx.androidtitlan.semanadelemprendedor.util.Event;
import mx.androidtitlan.semanadelemprendedor.util.MiscellaneousMethods;
import mx.androidtitlan.semanadelemprendedor.util.Speaker;


public class ListEvents extends ListFragment {

    private ArrayList<Event> events;
    private EventsAdapter adapter;
    private String day;
    private Boolean listCompleted = false;

    public static ListEvents newInstance(String option, String day) {
        ListEvents fragment = new ListEvents();
        Bundle args = new Bundle();
        args.putString("option",option);
        args.putString("day",day);
        fragment.setArguments(args);
        return fragment;
    }

    public ListEvents (){

    }


    @Override
    public void onPause() {
        super.onPause();
        Log.i("Pause","");
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (getActivity()!=null){
            try {
                if (getArguments().getString("option").equals("Explorer") && savedInstanceState == null) {
                    Log.i("Creating explorer","");
                    events = new ArrayList<Event>();
                    getAllEvents();
                } else if (getArguments().getString("option").equals("Schedule") && savedInstanceState == null) {
                    Log.i("Creating schedule","");
                    events = new ArrayList<Event>();
                    getAllEventsSchedule(getActivity().getIntent().getStringExtra("email"), getActivity().getIntent().getStringExtra("gafete"));
                }

            } catch (Exception e) {
                Log.e("Error", e.toString());
            }}

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        try{
            Intent i = new Intent(getActivity(), DetailEventActivity.class);
            i.putExtra("event",buildBundleEvent((Event)l.getItemAtPosition(position)));
            startActivity(i);
        }catch (Exception e){}
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("created", true);
        //getSupportFragmentManager().putFragment(outState, "mContent", mContent);
        Log.i("Saving", "");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i("Destroy view","");
    }

    public Bundle buildBundleEvent (Event event){
        Bundle result = new Bundle();

        result.putString("name",event.getName());
        result.putString("place",event.getPlace());
        result.putString("date",event.getDate());
        result.putString("description",event.getDescription());
        result.putString("category",event.getCategory());

        int i;

        for( i = 0 ; i<event.getSpeakers().size(); i++){
            Bundle bundle = new Bundle();
            Speaker speaker = event.getSpeakers().get(i);

            bundle.putString("name_speaker",speaker.getName());
            bundle.putString("dependency_speaker",speaker.getDependency());
            bundle.putString("cv",speaker.getCv());
            bundle.putString("picture",speaker.getUrl_photo());

            result.putBundle("speaker"+(i+1),bundle);
        }

        result.putInt("number_speakers",i);

        return result;
    }

    public void getAllEvents (){

        RequestQueue queue = Volley.newRequestQueue(getActivity());
        final String url = getResources().getString(R.string.url_conferencias);

        JsonObjectRequest getEvents = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try{
                            JSONObject data = response.getJSONObject("data");
                            Iterator <?> keys = data.keys();

                            while (keys.hasNext()){
                                String key = (String)keys.next();

                                if (data.get(key) instanceof JSONObject){
                                    try {
                                        JSONObject event = data.getJSONObject(key);
                                        parseEventInfo(event);
                                    }catch (JSONException e){
                                        Log.e("Error parse",e.toString());
                                    }
                                }
                            }
                            Collections.sort(events, new MiscellaneousMethods.CustomComparator());
                            adapter = new EventsAdapter(events,getActivity());
                            setListAdapter(adapter);
                            listCompleted = true;

                        }catch (JSONException ex){
                            Log.e("Json error",ex.toString());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("Error",error.toString());
                    }
                });

        queue.add(getEvents);
    }

    public void getAllEventsSchedule (final String email, final String gafete){

        RequestQueue queue = Volley.newRequestQueue(getActivity());
        final String url = "http://se.infoexpo.mx/2014/ae/web/utilerias/ws/google/get_attendee?idVisitante=" + gafete + "&email=" + email;
        JsonObjectRequest getEvents = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try{
                            JSONObject data = response.getJSONObject("data");

                            JSONArray array = data.getJSONArray("Agenda");

                            for(int i = 0; i<array.length(); i++ ){
                                JSONObject object = array.getJSONObject(i);
                                parseEventInfo(object);
                            }

                            if(events.isEmpty()){
                                String mensaje = "No hay eventos";
                                ArrayList<String> temp = new ArrayList<String>();
                                temp.add(mensaje);

                                ArrayAdapter<String> adapter1 = new ArrayAdapter<String>( getActivity(), android.R.layout.simple_list_item_1, temp );
                                setListAdapter(adapter1);
                            }

                            else{
                                Collections.sort(events, new MiscellaneousMethods.CustomComparator());
                                adapter = new EventsAdapter(events,getActivity());
                                setListAdapter(adapter);
                                listCompleted = true;
                            }

                        }catch (JSONException ex){
                            Log.e("Json error",ex.toString());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("Error",error.toString());
                    }
                });

        queue.add(getEvents);
    }
    public void parseEventInfo (JSONObject event){

        try{

            //Detectar el dia del evento para asignarle un arraylist/

            if(getArguments().getString("day").equals(event.getString(getResources().getString(R.string.dia_evento)))) {
                Event temp = new Event();

                temp.setName(event.getString(getResources().getString(R.string.nombre_conferencia)));
                temp.setPlace(event.getString(getResources().getString(R.string.sala_conferencia)));
                temp.setCategory(event.getString(getResources().getString(R.string.tipo_evento)));
                temp.setDate(event.getString(getResources().getString(R.string.dia_evento)));
                temp.setTimeInit(event.getString(getResources().getString(R.string.hora_inicio)));
                temp.setTimeEnd(event.getString(getResources().getString(R.string.hora_fin)));
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

                events.add(temp);

            }
        }catch (JSONException e){
            Log.e("Json event error",e.toString());
        }
    }

}
