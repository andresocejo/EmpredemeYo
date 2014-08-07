package com.example.jhordan.semprende.fragments;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.jhordan.semprende.R;

public class DetailEvent extends Fragment {

    public static DetailEvent newInstance() {
        DetailEvent fragment = new DetailEvent();
        Bundle args = new Bundle();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_detail_event, container, false);

        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        LinearLayout linearLayout = (LinearLayout)root.findViewById(R.id.information);
        LinearLayout linearLayout1 = (LinearLayout)root.findViewById(R.id.container_general_info_event);

        String category =  getActivity().getIntent().getStringExtra("category");

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
        transaction.add(linearLayout.getId(),new Speaker(),"speaker");
        transaction.add(linearLayout.getId(),new Speaker(),"speaker2");
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
