package com.example.jhordan.semprende.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.jhordan.semprende.util.Event;
import com.example.jhordan.semprende.R;

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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        PropertyHolderView propertyHolderView;
        View container = convertView;

        if(container == null){
            propertyHolderView = new PropertyHolderView();
            container = layoutInflater.inflate(R.layout.row_event,parent,false);
            propertyHolderView.initViewsFromIds(container, R.id.event_name,R.id.event_place, R.id.event_date);
            container.setTag(propertyHolderView);

        }else{
            propertyHolderView = (PropertyHolderView)container.getTag();
        }

        /*Putting content into the widgets*/
        Event propertyModel = events.get(position);

        propertyHolderView.getTxtEventName().
                setText( propertyModel.getName());

        propertyHolderView.getTxtEventPlace().
                setText(propertyModel.getPlace());

        propertyHolderView.getTxtEventDate().
                setText(propertyModel.getDate());

        return container;
    }



    private class PropertyHolderView{

        private TextView txtEventName;
        private TextView txtEventPlace;
        private TextView txtEventDate;


        public PropertyHolderView(){

        }

        public void initViewsFromIds(View container,int event_name ,int event_place ,int event_date){

            this.txtEventName = (TextView)container.findViewById(event_name);
            this.txtEventPlace = (TextView)container.findViewById(event_place);
            this.txtEventDate = (TextView)container.findViewById(event_date);

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
    }

}
