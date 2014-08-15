package mx.androidtitlan.semanadelemprendedor.fragments;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
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

import java.util.ArrayList;
import java.util.List;

import mx.androidtitlan.semanadelemprendedor.Activities.StreamingView;
import mx.androidtitlan.semanadelemprendedor.Adapter.SpeakerStreamingAdapter;
import mx.androidtitlan.semanadelemprendedor.Model.SpeakerModelConference;
import mx.androidtitlan.semanadelemprendedor.MyActivity;
import mx.androidtitlan.semanadelemprendedor.NavigationDrawerFragment;
import mx.androidtitlan.semanadelemprendedor.R;

/**
 * Created by Jhordan on 04/08/14.
 */
public class Streaming extends Fragment {

    GridView grid;
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
        for (int index = 0; index < 16; index++) {
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

                    //   Toast.makeText(
                    //         getActivity(), "conference: " + num, Toast.LENGTH_SHORT).show();

                    String streamings[] = {
                            "U_uxHf4WcgU",
                            "LuFittKgr4Q",
                            "-QKs0ulI7Z8",
                            "gJpubrrt05k",
                            "urZY6zvxlcA",
                            "OfBPdWcRfuY",
                            "oKbzoMjv2Pk",
                            "uKlhSHYGy1w",
                            "Lky8LGtTKoQ",
                            "Bk_2t0-OELE",
                            "t6-pFLwBhl0",
                            "jrKSzCEkL2k",
                            "FbYNYe2lqfo",
                            "ZVgpjb2a-pU",
                            "CB6hKzGpG3Q",
                            "GaEa0PLxSc0"
                    };

                    //  String video = "LuFittKgr4Q";//tecnologias creativas e internet de las cosas

                    //  String videoConference = "U_uxHf4WcgU" ; La Creación de una Era de Abundancia


                    Intent intento = new Intent(getActivity(), StreamingView.class);
                    intento.putExtra("id_video", streamings[position]);
                    startActivity(intento);


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
