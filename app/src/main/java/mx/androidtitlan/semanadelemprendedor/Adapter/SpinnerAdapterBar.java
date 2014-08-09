package mx.androidtitlan.semanadelemprendedor.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import mx.androidtitlan.semanadelemprendedor.Model.SpinnerModelBar;
import mx.androidtitlan.semanadelemprendedor.R;

import java.util.ArrayList;

/**
 * Created by Jhordan on 07/08/14.
 */
public class SpinnerAdapterBar extends BaseAdapter {

    private ImageView imgIcon;
    private TextView txtTitle;
    private ArrayList<SpinnerModelBar> spinnerNavItem;
    private Context context;
    private LayoutInflater layoutInflater;

    public SpinnerAdapterBar(Context context, ArrayList<SpinnerModelBar> spinnerNavItem) {

        this.spinnerNavItem = spinnerNavItem;
        this.context = context;
       layoutInflater = LayoutInflater.from(context);


    }

    @Override
    public int getCount() {
        return spinnerNavItem.size();
    }

    @Override
    public Object getItem(int position) {
        return spinnerNavItem.get(position);
    }

    @Override
    public long getItemId(int position) {


        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        View container = convertView;

        if (container == null) {

            LayoutInflater mInflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.spinner_item_bar, null);
        }

        imgIcon = (ImageView) convertView.findViewById(R.id.imgIcon);
        txtTitle = (TextView) convertView.findViewById(R.id.txtTitle);

        imgIcon.setImageResource(spinnerNavItem.get(position).getIcon());
        imgIcon.setVisibility(View.GONE);
        txtTitle.setText(spinnerNavItem.get(position).getSesiones());
        return convertView;


    }


    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View container = convertView;

        if (container == null) {

            LayoutInflater mInflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.spinner_item_bar, null);
        }

        imgIcon = (ImageView) convertView.findViewById(R.id.imgIcon);
        txtTitle = (TextView) convertView.findViewById(R.id.txtTitle);

        imgIcon.setImageResource(spinnerNavItem.get(position).getIcon());
        imgIcon.setVisibility(View.GONE);
        txtTitle.setText(spinnerNavItem.get(position).getSesiones());
        return convertView;
    }
}
