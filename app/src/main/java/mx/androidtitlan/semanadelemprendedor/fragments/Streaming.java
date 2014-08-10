package mx.androidtitlan.semanadelemprendedor.fragments;

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
import android.widget.GridView;
import android.widget.Toast;

import mx.androidtitlan.semanadelemprendedor.Adapter.SpeakerStreamingAdapter;
import mx.androidtitlan.semanadelemprendedor.Model.SpeakerModelConference;
import mx.androidtitlan.semanadelemprendedor.MyActivity;
import mx.androidtitlan.semanadelemprendedor.NavigationDrawerFragment;
import mx.androidtitlan.semanadelemprendedor.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jhordan on 04/08/14.
 */
public class Streaming extends Fragment {

    GridView grid ;
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

        grid = (GridView) v.findViewById(R.id.mygrid);





        List<SpeakerModelConference> ponente = new ArrayList<SpeakerModelConference>();
        SpeakerStreamingAdapter ponenteAdapter = new SpeakerStreamingAdapter(ponente, getActivity());

        //Esto es para probar el gridview con datos
        for (int index = 0; index < 13; index++) {
            SpeakerModelConference confe = new SpeakerModelConference();


            ponente.add(confe);

        }


        grid.setAdapter(ponenteAdapter);


      //  View header = View.inflate(getActivity(), R.layout.speaker_gridtwo, null);
        //list.addHeaderView(header);
       // list.setNumColumns(2);


        try {   //detectar cual fue pinchada en el gridview

            grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
