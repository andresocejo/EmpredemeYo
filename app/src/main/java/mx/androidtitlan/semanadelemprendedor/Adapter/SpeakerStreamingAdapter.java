package mx.androidtitlan.semanadelemprendedor.Adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import mx.androidtitlan.semanadelemprendedor.Model.SpeakerModelConference;
import mx.androidtitlan.semanadelemprendedor.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jhordan on 06/08/14.
 */
public class SpeakerStreamingAdapter extends BaseAdapter {


    private List<SpeakerModelConference> speaker;
    private Context mContext;
    private LayoutInflater layoutInflater;
    private Integer[] images ={
            R.drawable.img_andres_bustamante,
            R.drawable.img_andy_cohen,
            R.drawable.img_arjan_dijk,
            R.drawable.img_blanca_trevino,
            R.drawable.img_carlos_arguello,
            R.drawable.img_christopher_gardner,
            R.drawable.img_david_konzevik,
            R.drawable.img_ildefonso_guajardo,
            R.drawable.img_luis_barrios,
            R.drawable.img_marc_vidal,
            R.drawable.img_peter_diamandis,
            R.drawable.img_ramon_munoz,
            R.drawable.img_renee_mauborgne,
            R.drawable.img_roberto_azevedo

    };



    public SpeakerStreamingAdapter(List<SpeakerModelConference> speaker, Context mContext) {
        this.speaker = speaker;
        this.mContext = mContext;
        layoutInflater = LayoutInflater.from(mContext);

    }

    public SpeakerStreamingAdapter(Context mContext) {
        this.mContext = mContext;
        this.speaker = new ArrayList<SpeakerModelConference>();
        layoutInflater = LayoutInflater.from(mContext);

    }


    @Override
    public int getCount() {
        return speaker.size();
    }

    @Override
    public Object getItem(int position) {
        return speaker.get(position);
    }

    @Override
    public long getItemId(int position) {
        //return speaker.indexOf(speaker.get(position));

        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        Log.i("Getting", "view");

        ConferenceHolderView streamingHolderView;
        View container = convertView;

        if (container == null) {
            streamingHolderView = new ConferenceHolderView();


            container = layoutInflater.inflate(R.layout.speaker_grid, parent, false);
            streamingHolderView.initViewsFromIds(container, R.id.speaker_image, R.id.conference_title, R.id.hour_conference);
            container.setTag(streamingHolderView);


        } else {
            streamingHolderView = (ConferenceHolderView) container.getTag();
        }
        streamingHolderView.getTitleContainer().setText("Una Interesante Conferencia");
        streamingHolderView.getHourContainer().setText("En vivo ahora");
        streamingHolderView.getSpeakerImageContainer().setImageResource(images[position]);

        String num = Integer.toString(position);

        Log.i("position ", num);
        return container;
    }


    private class ConferenceHolderView {

        private ImageView speaker_photo;
        private TextView title_conference;
        private TextView hour_conference;


        public ConferenceHolderView() {

        }

        public void initViewsFromIds(View container, int speaker_photo, int title_conference, int hour_conference) {

            this.speaker_photo = (ImageView) container.findViewById(speaker_photo);
            this.title_conference = (TextView) container.findViewById(title_conference);
            this.hour_conference = (TextView) container.findViewById(hour_conference);

        }

        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
        public ImageView getSpeakerImageContainer() {
            return speaker_photo;
        }

        public TextView getTitleContainer() {
            return title_conference;
        }

        public TextView getHourContainer() {
            return hour_conference;
        }

    }
}
