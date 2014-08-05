package com.example.jhordan.semprende.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListView;

import com.example.jhordan.semprende.Activities.DetailEventActivity;
import com.example.jhordan.semprende.Adapter.EventsAdapter;
import com.example.jhordan.semprende.NavigationDrawerFragment;
import com.example.jhordan.semprende.util.Event;

import java.util.ArrayList;


public class ListEvents extends ListFragment {

    ArrayList<Event> events;

    public static ListEvents newInstance(int position) {
        ListEvents fragment = new ListEvents();
        Bundle args = new Bundle();
        args.putInt(NavigationDrawerFragment.ARG_SECTION_NUMBER, position);
        fragment.setArguments(args);
        return fragment;
    }
    public ListEvents() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        events =  new ArrayList<Event>();
        fillList();

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    /*Solo para prueba*/
    public void fillList(){

        for(int i = 0; i<9; i++){
            Event event = new Event("Un gran nombre para una gran conferencia","00:00 a 00:00","Escenario XYZ");
            events.add(event);
        }

        EventsAdapter eventsAdapter = new EventsAdapter(events,getActivity());
        setListAdapter(eventsAdapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Intent i = new Intent(getActivity(), DetailEventActivity.class);
        startActivity(i);
    }

}
