package com.example.jhordan.semprende.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import java.util.Iterator;

import com.example.jhordan.semprende.Activities.DetailEventActivity;
import com.example.jhordan.semprende.Adapter.EventsAdapter;
import com.example.jhordan.semprende.NavigationDrawerFragment;
import com.example.jhordan.semprende.R;
import com.example.jhordan.semprende.util.Event;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class ListEvents extends ListFragment {

    private ArrayList<Event> events;
    private EventsAdapter adapter;

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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        events = new ArrayList<Event>();

        try{
            if(getArguments().getString("option").equals("Explorer") && savedInstanceState == null){
                getAllEvents();
            }

        }catch (Exception e){}

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
        Intent i = new Intent(getActivity(), DetailEventActivity.class);
        i.putExtra("category",((Event)l.getItemAtPosition(position)).getCategory());
        startActivity(i);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("created",true);
        //getSupportFragmentManager().putFragment(outState, "mContent", mContent);
        Log.i("Saving","");
    }

    public void getAllEvents (){

        RequestQueue queue = Volley.newRequestQueue(getActivity());
        final String url = getResources().getString(R.string.url_conferencias);

        JsonObjectRequest getEvents = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        int size = 0;
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

                            adapter = new EventsAdapter(events,getActivity());
                            setListAdapter(adapter);

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
            if(getArguments().getString("day").equals(event.getString(getResources().getString(R.string.dia_evento)))){
                Event temp = new Event();

                temp.setName(event.getString(getResources().getString(R.string.nombre_conferencia)));
                temp.setPlace(event.getString(getResources().getString(R.string.sala_conferencia)));
                temp.setCategory(event.getString(getResources().getString(R.string.tipo_evento)));
                temp.setDate(event.getString(getResources().getString(R.string.dia_evento)));
                temp.setTimeInit(event.getString(getResources().getString(R.string.hora_inicio)));
                temp.setTimeEnd(event.getString(getResources().getString(R.string.hora_fin)));

                events.add(temp);

            }

        }catch (JSONException e){
            Log.e("Json event error",e.toString());
        }
    }

}
