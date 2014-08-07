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
import com.example.jhordan.semprende.R;
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

        Event event = new Event("PANEL Liderazgo: ¿Y si todo sale mal?","08/14/2014","16:30:00","18:00:00","Palacio Valparaiso 1", getResources().getString(R.string.conferencia));
        events.add(event);

        Event event2 = new Event("La Creatividad en la visión de un Empresario","08/14/2014","18:00:00","19:30:00","Palacio Valparaiso 2", getResources().getString(R.string.magistral));
        events.add(event2);

        Event event3 = new Event("Panel: Emprendimiento Social de la Filantropía a los Negocios Sociales","08/14/2014","18:00:00","19:30:00","Palacio Valparaiso 1", getResources().getString(R.string.taller));
        events.add(event3);

        EventsAdapter eventsAdapter = new EventsAdapter(events,getActivity());
        setListAdapter(eventsAdapter);
    }

    public void fillListAgenda(){


    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Intent i = new Intent(getActivity(), DetailEventActivity.class);
        i.putExtra("category",((Event)l.getItemAtPosition(position)).getCategory());
        startActivity(i);
    }

}
