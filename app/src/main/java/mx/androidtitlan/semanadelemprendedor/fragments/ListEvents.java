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

import java.util.ArrayList;
import java.util.Collections;

import mx.androidtitlan.semanadelemprendedor.Activities.DetailEventActivity;
import mx.androidtitlan.semanadelemprendedor.Adapter.EventsAdapter;
import mx.androidtitlan.semanadelemprendedor.util.Event;
import mx.androidtitlan.semanadelemprendedor.util.MiscellaneousMethods;
import mx.androidtitlan.semanadelemprendedor.util.Speaker;


public class ListEvents extends ListFragment{

    public ArrayList<Event> events;
    private EventsAdapter adapter;

    public  Boolean flag = false ;


    public static ListEvents newInstance(String option, String day) {
        ListEvents fragment = new ListEvents();
        Bundle args = new Bundle();
        args.putString("option", option);
        args.putString("day", day);
        fragment.setArguments(args);
        return fragment;
    }

    public ListEvents() {

    }

    public Boolean isUpdated(){
        return flag;
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i("Pause", "");
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        events = new ArrayList<Event>();

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null){
            flag = savedInstanceState.getBoolean("created");
        }
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
        try {
            Intent i = new Intent(getActivity(), DetailEventActivity.class);
            i.putExtra("event", buildBundleEvent((Event) l.getItemAtPosition(position)));
            startActivity(i);
        } catch (Exception e) {
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("created", true);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    public void updateAllConfferences(){

        if(flag == false){

            Log.i("Updating","");
            events = new ArrayList<Event>();
            fillEvents();
            if (events.isEmpty()) {
                String mensaje = "No hay eventos";
                ArrayList<String> temp = new ArrayList<String>();
                temp.add(mensaje);

                ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, temp);
                setListAdapter(adapter1);
            } else {
                Collections.sort(events, new MiscellaneousMethods.CustomComparator());
                adapter = new EventsAdapter(events, getActivity());
                setListAdapter(adapter);
            }

        }

        flag = true;

    }

    public void updateUserConfference(){

        if (flag == false){

            Log.i("Updating","");
            fillUserEvents();

            if (events.isEmpty()) {
                String mensaje = "No hay eventos";
                ArrayList<String> temp = new ArrayList<String>();
                temp.add(mensaje);

                ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, temp);
                setListAdapter(adapter1);
            } else {
                Collections.sort(events, new MiscellaneousMethods.CustomComparator());
                adapter = new EventsAdapter(events, getActivity());
                setListAdapter(adapter);
            }

            flag = true;

        }

    }

    public Bundle buildBundleEvent(Event event) {
        Bundle result = new Bundle();

        result.putString("name", event.getName());
        result.putString("place", event.getPlace());
        result.putString("date", event.getDate());
        result.putString("description", event.getDescription());
        result.putString("category", event.getCategory());

        int i;

        for (i = 0; i < event.getSpeakers().size(); i++) {
            Bundle bundle = new Bundle();
            Speaker speaker = event.getSpeakers().get(i);

            bundle.putString("name_speaker", speaker.getName());
            bundle.putString("dependency_speaker", speaker.getDependency());
            bundle.putString("cv", speaker.getCv());
            bundle.putString("picture", speaker.getUrl_photo());

            result.putBundle("speaker" + (i + 1), bundle);
        }

        result.putInt("number_speakers", i);

        return result;
    }

    public void fillEvents (){
        for (int i=0; i<Explorer.eventsMain.size(); i++ ){
            if (Explorer.eventsMain.get(i).getDate().equals(getArguments().getString("day"))){
                Event temp = Explorer.eventsMain.get(i);
                events.add(temp);
            }
        }
    }

    public void fillUserEvents (){
        for (int i=0; i<Schedule.eventsUser.size(); i++ ){
            if (Schedule.eventsUser.get(i).getDate().equals(getArguments().getString("day"))){
                Event temp = Schedule.eventsUser.get(i);
                events.add(temp);
            }
        }
    }

    public void sortByEco (String eco){
        ArrayList<Event> eventsEco = new ArrayList<Event>();

        if(!events.isEmpty()){

            if (eco.equals("Todos")){
                adapter = new EventsAdapter(events, getActivity());
                setListAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            else {
                for (int i = 0; i<events.size(); i++){
                    if(events.get(i).getEco().equals(eco)){
                        eventsEco.add(events.get(i));
                    }
                }

                adapter = new EventsAdapter(eventsEco, getActivity());
                setListAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        }

    }
}




