package mx.androidtitlan.semanadelemprendedor.Adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
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
            R.drawable.img_peter_diamandis,
            R.drawable.david,

            R.drawable.img_arjan_dijk,
            R.drawable.img_carlos_arguello,
            R.drawable.img_marc_vidal,
            R.drawable.img_ildefonso_guajardo,

            R.drawable.ema,
            R.drawable.img_luis_barrios,
            R.drawable.img_renee_mauborgne,
            R.drawable.img_andy_cohen,

            R.drawable.img_blanca_trevino,
            R.drawable.logo_sde,
            R.drawable.img_christopher_gardner,
            R.drawable.img_david_konzevik,

            R.drawable.img_ramon_munoz,

            R.drawable.img_roberto_azevedo

    };


    private String [] conferencias = {

            "La Creación de una Era de la Abundancia",
            "Tecnologías Creativas e Internet de las cosas",

            "Haz que todo el poder del Internet trabaje para tu Negocio",
            "Industrias Creativas para el Desarrollo en Latinoamérica",
            "Una hormiga en París",
            "Reformas Transformadoras",
            "Perspectivas de Pemex en la Industria Petrolera después de la Reforma Energética",
            "Convicción para Emprender",
            "La estrategia del Océano Azul: Cómo hacer que la competencia se vuelva irrelevante",
            "El poder de pensar diferente",
            "Tu futuro, tus fortalezas, tus decisiones",
            "La Creatividad en la visión de un Empresario"
           };




    private String [] speakerConferencia = {"Peter Diamandis",
            "David Cuartielles",
            "Arjan Dijk",
            "Carlos Argüello",
            "Marc Vidal",
            "Ildefonso G Villarreal",
            "Mtro. Emilio Ricardo Lozoya",
            "Luis Barrios Sánchez",
            "Renée Mauborgne",
            "Andy Cohen",
            "Blanca Treviño",
            "Andres Bustamante"};



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
            streamingHolderView.initViewsFromIds(container, R.id.speaker_image, R.id.conference_title, R.id.hour_conference,R.id.speaker_color);
            container.setTag(streamingHolderView);


        } else {
            streamingHolderView = (ConferenceHolderView) container.getTag();
        }
        streamingHolderView.getTitleContainer().setText(conferencias[position]);
        streamingHolderView.getHourContainer().setText(speakerConferencia[position]);
        streamingHolderView.getSpeakerImageContainer().setImageResource(images[position]);

        if (position == 0 || position == 1){

            streamingHolderView.getColor_conference().setBackgroundColor(Color.parseColor("#75378d3b"));
            streamingHolderView.getColor_conference().setText("Lunes");

        }else if(position == 2 || position == 3 || position == 4 || position == 5){

            streamingHolderView.getColor_conference().setBackgroundColor(Color.parseColor("#7063b4f5"));
            streamingHolderView.getColor_conference().setText("Martes");

        }else if(position == 6 || position == 7){
            streamingHolderView.getColor_conference().setBackgroundColor(Color.parseColor("#70E8604B"));
            streamingHolderView.getColor_conference().setText("Miercoles");


        }else if(position == 8 || position == 9 || position == 10 || position == 11){

            streamingHolderView.getColor_conference().setBackgroundColor(Color.parseColor("#707d56C1"));
            streamingHolderView.getColor_conference().setText("Jueves");

        }


        String num = Integer.toString(position);

        Log.i("position ", num);
        return container;
    }


    private class ConferenceHolderView {

        private ImageView speaker_photo;
        private TextView title_conference;
        private TextView hour_conference;
        private TextView color_conference;





        public ConferenceHolderView() {

        }

        public void initViewsFromIds(View container, int speaker_photo, int title_conference, int hour_conference , int color_conference) {

            this.speaker_photo = (ImageView) container.findViewById(speaker_photo);
            this.title_conference = (TextView) container.findViewById(title_conference);
            this.hour_conference = (TextView) container.findViewById(hour_conference);
            this.color_conference = (TextView)container.findViewById(color_conference);



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

        public TextView getColor_conference(){return  color_conference;}



    }
}
