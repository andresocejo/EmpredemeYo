package mx.androidtitlan.semanadelemprendedor.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.widget.ArrayAdapter;

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

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, getResources()
                .getStringArray(R.array.ecositemas)
        );

        builder.setTitle(R.string.fiter_ecosistemas).setIcon(R.drawable.ic_map)
                .setAdapter(arrayAdapter, new OnClickListener() {

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
                });

        return builder.create();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        actualizaMapa = null;
    }
}
