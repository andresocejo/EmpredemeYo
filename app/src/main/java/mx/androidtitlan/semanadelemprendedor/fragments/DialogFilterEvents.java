package mx.androidtitlan.semanadelemprendedor.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import mx.androidtitlan.semanadelemprendedor.Adapter.FilterItemEvenAdapter;
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

        builder.setTitle(R.string.fiter_ecosistemas).setIcon(R.drawable.ic_explore);

        FilterItemEvenAdapter filterItemMapAdapter = new FilterItemEvenAdapter(getActivity(), R.layout.item_filter_map, getResources()
                .getStringArray(R.array.eco_array));


        ListView view = (ListView) getActivity().getLayoutInflater().inflate(R.layout.listiview_dialog, null, false);
        view.setAdapter(filterItemMapAdapter);

        view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                explorador.recreateList(days[i], i);
                dismiss();

            }
        });

        builder.setView(view);

        return builder.create();
    }
}
