package com.example.jhordan.semprende.fragments;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.jhordan.semprende.Adapter.SpeakerStreamingAdapter;
import com.example.jhordan.semprende.GridView;
import com.example.jhordan.semprende.Model.SpeakerModelConference;
import com.example.jhordan.semprende.MyActivity;
import com.example.jhordan.semprende.NavigationDrawerFragment;
import com.example.jhordan.semprende.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jhordan on 04/08/14.
 */
public class Streaming extends Fragment {

    private GridView list;
    private Layout rls;

    public static Streaming newInstance(int position) {
        Streaming myexplore = new Streaming();
        Bundle extraArguments = new Bundle();
        extraArguments.putInt(NavigationDrawerFragment.ARG_SECTION_NUMBER, position);
        myexplore.setArguments(extraArguments);
        return myexplore;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.streaming_fragment, container, false);

        list = (GridView) v.findViewById(R.id.list);


        List<SpeakerModelConference> ponente = new ArrayList<SpeakerModelConference>();
        SpeakerStreamingAdapter ponenteAdapter = new SpeakerStreamingAdapter(ponente, getActivity());

        //Esto es para probar el gridview con datos
        for (int index = 0; index < 10; index++) {
            SpeakerModelConference confe = new SpeakerModelConference();


            ponente.add(confe);

        }


        list.setAdapter(ponenteAdapter);


        View header = View.inflate(getActivity(), R.layout.speaker_gridtwo, null);
        list.addHeaderView(header);
        list.setNumColumns(2);


        try {   //detectar cual fue pinchada en el gridview

            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    int var = position + 1;

                    String num = Integer.toString(var);

                    Log.i("position ", num);

                    Toast.makeText(
                            getActivity(), "conference: " + num, Toast.LENGTH_SHORT).show();


                }
            });
        } catch (Exception e) {
            Log.i("Erro click item why:", e.toString());
        }


        return v;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MyActivity) activity).onSectionAttached(getArguments()
                .getInt(NavigationDrawerFragment.ARG_SECTION_NUMBER));
    }
}
