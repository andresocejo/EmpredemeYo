package mx.androidtitlan.semanadelemprendedor.Adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import mx.androidtitlan.semanadelemprendedor.util.Event;
import mx.androidtitlan.semanadelemprendedor.util.MiscellaneousMethods;
import mx.androidtitlan.semanadelemprendedor.R;

import java.util.ArrayList;

/**
 * Created by Petter on 04/08/2014.
 * Adapter for the events list
 */
public class EventsAdapter extends ArrayAdapter<Event> {

    private ArrayList<Event> events;
    private Context context;
    private LayoutInflater layoutInflater;

    public EventsAdapter (ArrayList<Event> events, Context context) {

        super(context, R.layout.row_event,events);
        this.events = events;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        PropertyHolderView propertyHolderView;
        View container = convertView;

        if(container == null){
            propertyHolderView = new PropertyHolderView();
            container = layoutInflater.inflate(R.layout.row_event,parent,false);
            propertyHolderView.initViewsFromIds(container, R.id.event_name,R.id.event_place, R.id.event_date,R.id.container_info_event);
            container.setTag(propertyHolderView);

        }else{
            propertyHolderView = (PropertyHolderView)container.getTag();
        }

        /*Putting content into the widgets*/
        Event propertyModel = events.get(position);

        //Define event color
        if(propertyModel.getCategory().equals(context.getResources().getString(R.string.magistral))) {
            propertyHolderView.getContainerInfoEvent().
                    setBackground(new ColorDrawable(context.getResources().getColor(R.color.purple_event)));
        }
        else if(propertyModel.getCategory().equals(context.getResources().getString(R.string.conferencia))) {
            propertyHolderView.getContainerInfoEvent().
                    setBackground(new ColorDrawable(context.getResources().getColor(R.color.blue_event)));
        }
        else{
            propertyHolderView.getContainerInfoEvent().
                    setBackground(new ColorDrawable(context.getResources().getColor(R.color.green_event)));
        }

        propertyHolderView.getTxtEventName().
                setText( propertyModel.getName());

        propertyHolderView.getTxtEventPlace().
                setText(propertyModel.getPlace());

        propertyHolderView.getTxtEventDate().
                setText(MiscellaneousMethods.
                        concatTime(propertyModel.getTimeInit(), propertyModel.getTimeEnd()));

        return container;
    }



    private class PropertyHolderView{

        private TextView txtEventName;
        private TextView txtEventPlace;
        private TextView txtEventDate;

        private LinearLayout containerInfoEvent;

        public PropertyHolderView(){

        }

        public void initViewsFromIds(View container,int event_name ,int event_place ,int event_date, int container_event){

            this.txtEventName = (TextView)container.findViewById(event_name);
            this.txtEventPlace = (TextView)container.findViewById(event_place);
            this.txtEventDate = (TextView)container.findViewById(event_date);

            this.containerInfoEvent = (LinearLayout)container.findViewById(container_event);

        }

        public TextView getTxtEventName() {
            return txtEventName;
        }

        public TextView getTxtEventPlace() {
            return txtEventPlace;
        }

        public TextView getTxtEventDate() {
            return txtEventDate;
        }

        public LinearLayout getContainerInfoEvent() {
            return containerInfoEvent;
        }
    }

}
