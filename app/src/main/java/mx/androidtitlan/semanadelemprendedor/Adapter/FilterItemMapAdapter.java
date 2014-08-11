package mx.androidtitlan.semanadelemprendedor.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.StateListDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import mx.androidtitlan.semanadelemprendedor.R;


/**
 * Created by Jhordan on 20/07/14.
 */
public class FilterItemMapAdapter extends ArrayAdapter<String> {

    private String[] values;
    private LayoutInflater layoutInflater;

    public FilterItemMapAdapter(Context context, int resource, String[] objects) {
        super(context, resource, objects);
        layoutInflater = LayoutInflater.from(context);
        this.values = objects;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        TextView textView = (TextView) layoutInflater.inflate(R.layout.item_filter_map, parent, false);

        int colorFill = 0;
        int colorStro = 0;


        switch (position) {
            case 0:
                textView.setTextColor(Color.BLACK);
                textView.setText(values[position]);
                return textView;
            case 1:
                colorFill = Color.rgb(221, 18, 69);
                colorStro = Color.rgb(171, 0, 19);
                break;
            case 2:
                colorFill = Color.rgb(144, 145, 192);
                colorStro = Color.rgb(94, 95, 142);

                break;
            case 3:
                colorFill = Color.rgb(65, 65, 66);
                colorStro = Color.rgb(15, 15, 16);

                break;
            case 4:
                colorFill = Color.rgb(78, 195, 199);
                colorStro = Color.rgb(28, 145, 149);
                break;
            case 5:
                colorFill = Color.rgb(233, 83, 80);
                colorStro = Color.rgb(183, 33, 30);
                break;
            case 6:
                colorFill = Color.rgb(187, 189, 191);
                colorStro = Color.rgb(137, 139, 141);
                break;
            case 7:
                colorFill = Color.rgb(108, 85, 164);
                colorStro = Color.rgb(58, 35, 114);
                break;
            case 8:
                colorFill = Color.rgb(162, 185, 98);
                colorStro = Color.rgb(112, 135, 48);
                break;
            case 9:
                colorFill = Color.rgb(243, 154, 69);
                colorStro = Color.rgb(193, 104, 19);
                break;
            case 10:
                colorFill = Color.rgb(232, 96, 75);
                colorStro = Color.rgb(182, 46, 25);
                break;
        }
        StateListDrawable states = new StateListDrawable();
        states.addState(new int[] {android.R.attr.state_pressed},
                new ColorDrawable(colorStro));
        states.addState(new int[] {android.R.attr.state_focused},
                new ColorDrawable(colorStro));
        states.addState(new int[] { },
                new ColorDrawable(colorFill));
        textView.setText(values[position]);
        textView.setBackground(states);

        return textView;
    }
}
