package com.example.jhordan.semprende.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jhordan.semprende.R;
import com.example.jhordan.semprende.util.CircleTransform;
import com.squareup.picasso.Picasso;

/**
 *Este fragmento contiene la información de un ponente
 */
public class SpeakerFragment extends Fragment {

    ImageView photo;

    public static SpeakerFragment newInstance(Bundle speaker) {
        SpeakerFragment fragment = new SpeakerFragment();
        Bundle args = new Bundle();
        args.putBundle("speaker",speaker);
        fragment.setArguments(args);
        return fragment;
    }
    public SpeakerFragment() {
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
        Bundle speaker = getArguments().getBundle("speaker");

        photo = (ImageView)root.findViewById(R.id.speaker_photo);
        TextView speakerName = (TextView)root.findViewById(R.id.speaker_name);
        TextView speakerCompany = (TextView)root.findViewById(R.id.speaker_company);
        TextView speakerDescription = (TextView)root.findViewById(R.id.speaker_description);

        speakerName.setText(speaker.getString("name_speaker"));
        speakerCompany.setText(speaker.getString("dependency_speaker"));
        speakerDescription.setText(speaker.getString("cv"));

        if(speaker.getString("picture").isEmpty()){
            Picasso.with(getActivity()).load("http://www.codejobs.biz/www/lib/files/images/b312953ac30ff5d.png").transform(new CircleTransform()).into(photo);
        }

        else{
            Picasso.with(getActivity()).load(speaker.getString("picture")).transform(new CircleTransform()).into(photo);
        }



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
