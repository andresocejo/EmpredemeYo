package mx.androidtitlan.semanadelemprendedor.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.ArrayAdapter;

import mx.androidtitlan.semanadelemprendedor.R;

/**
 * Created by Petter on 10/08/2014.
 */
public class DialogFilterEvents extends DialogFragment {

    public static final String ECOSISTEMA = "ecosistema";
    public String [] ecosistemas;
    private UpdateList explorador;

    public interface UpdateList {
        public void recreateList(String eco, int id);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        explorador = (UpdateList) getActivity().getSupportFragmentManager().findFragmentById(R.id.container);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final String [] days = getResources()
                .getStringArray(R.array.eco_array);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, getResources()
                .getStringArray(R.array.eco_array)
        );

        builder.setTitle(R.string.fiter_ecosistemas).setIcon(R.drawable.ic_explore)
                .setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        explorador.recreateList(days[which], which);
                    }
                });

        return builder.create();
    }
}
