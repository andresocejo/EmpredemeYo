package com.example.jhordan.semprende.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.jhordan.semprende.R;
import com.squareup.picasso.Picasso;

/**
 *Este fragmento contiene la información de un ponente
 */
public class Speaker extends Fragment {

    ImageView photo;

    public static Speaker newInstance() {
        Speaker fragment = new Speaker();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }
    public Speaker() {
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
        View root = inflater.inflate(R.layout.fragment_speaker, container, false);
        photo = (ImageView)root.findViewById(R.id.speaker_photo);

        Picasso.with(getActivity()).load("http://www.codejobs.biz/www/lib/files/images/b312953ac30ff5d.png").into(photo);
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
