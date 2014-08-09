package com.example.jhordan.semprende.fragments;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jhordan.semprende.R;

public class DetailEvent extends Fragment {

    public static DetailEvent newInstance(Bundle event) {
        DetailEvent fragment = new DetailEvent();
        Bundle args = new Bundle();
        args.putBundle("event",event);
        fragment.setArguments(args);
        return fragment;
    }
    public DetailEvent() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_detail_event, container, false);
        Bundle event = getArguments().getBundle("event");

        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        LinearLayout linearLayout = (LinearLayout)root.findViewById(R.id.information);
        LinearLayout linearLayout1 = (LinearLayout)root.findViewById(R.id.container_general_info_event);

        TextView title = (TextView)root.findViewById(R.id.event_name_detail);
        TextView place = (TextView)root.findViewById(R.id.event_place_detail);
        TextView date = (TextView)root.findViewById(R.id.event_date_detail);
        TextView description = (TextView)root.findViewById(R.id.event_description);

        title.setText(event.getString("name"));
        place.setText(event.getString("place"));
        date.setText(event.getString("date"));
        description.setText(event.getString("description"));

        String category =  event.getString("category");

        //Define actionbar and linear layout color

        if(category.equals(getResources().getString(R.string.magistral))){
            ((ActionBarActivity)getActivity()).
                    getSupportActionBar().
                    setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.purple_event)));

            linearLayout1.setBackground(new ColorDrawable(getResources().getColor(R.color.purple_event)));
        }

        else if(category.equals(getResources().getString(R.string.conferencia))){
            ((ActionBarActivity)getActivity()).
                    getSupportActionBar().
                    setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.blue_event)));

            linearLayout1.setBackground(new ColorDrawable(getResources().getColor(R.color.blue_event)));
        }

        else{
            ((ActionBarActivity)getActivity()).
                    getSupportActionBar().
                    setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.green_event)));

            linearLayout1.setBackground(new ColorDrawable(getResources().getColor(R.color.green_event)));
        }

        /*Prueba para agregar fragmentos del perfil de un ponente*/

        for(int i = 0; i<(event.getInt("number_speakers")); i++){
           transaction.add(linearLayout.getId(),SpeakerFragment.newInstance(event.getBundle("speaker"+(i+1))),"speaker");
        }

        transaction.commit();

        return root;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
