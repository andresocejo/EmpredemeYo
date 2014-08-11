package mx.androidtitlan.semanadelemprendedor.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import mx.androidtitlan.semanadelemprendedor.Adapter.FilterItemMapAdapter;
import mx.androidtitlan.semanadelemprendedor.R;

public class DialogFilterMap extends DialogFragment {

    public static final String ECOSISTEMA = "ecosistema";
    public static final String FIRTER_MAP = "fiter_map";
    private UpdateMap actualizaMapa;

    public interface UpdateMap {
        public void recreateMap();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);


        actualizaMapa = (UpdateMap) getActivity().getSupportFragmentManager().findFragmentById(R.id.container);

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());


        FilterItemMapAdapter filterItemMapAdapter = new FilterItemMapAdapter(getActivity(), R.layout.item_filter_map, getResources()
                .getStringArray(R.array.ecositemas));


        builder.setTitle(R.string.fiter_ecosistemas).setIcon(R.drawable.ic_map)
 /*               .setAdapter(filterItemMapAdapter, new OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        SharedPreferences preferences = getActivity()
                                .getSharedPreferences(FIRTER_MAP,
                                        Context.MODE_PRIVATE);

                        if (which != preferences.getInt(ECOSISTEMA, 0)) {
                            SharedPreferences.Editor editor = preferences
                                    .edit();
                            editor.putInt(ECOSISTEMA, which);
                            editor.commit();
                            actualizaMapa.recreateMap();
                        }
                    }
                })*/;


        ListView view = (ListView) getActivity().getLayoutInflater().inflate(R.layout.listiview_dialog, null, false);
        view.setAdapter(filterItemMapAdapter);

        view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                SharedPreferences preferences = getActivity()
                        .getSharedPreferences(FIRTER_MAP,
                                Context.MODE_PRIVATE);

                if (i != preferences.getInt(ECOSISTEMA, 0)) {
                    SharedPreferences.Editor editor = preferences
                            .edit();
                    editor.putInt(ECOSISTEMA, i);
                    editor.commit();
                    actualizaMapa.recreateMap();
                }
                dismiss();

            }
        });

        builder.setView(view);


        return builder.create();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        actualizaMapa = null;
    }
}
