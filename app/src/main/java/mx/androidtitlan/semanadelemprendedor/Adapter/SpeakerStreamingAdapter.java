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

import java.util.ArrayList;
import java.util.List;

import mx.androidtitlan.semanadelemprendedor.Model.SpeakerModelConference;
import mx.androidtitlan.semanadelemprendedor.R;

/**
 * Created by Jhordan on 06/08/14.
 */
public class SpeakerStreamingAdapter extends BaseAdapter {


    private List<SpeakerModelConference> speaker;
    private Context mContext;
    private LayoutInflater layoutInflater;
    private Integer[] images = {
            R.drawable.img_peter_diamandis,
            R.drawable.david,
            R.drawable.img_arjan_dijk,
            R.drawable.img_carlos_arguello,
            R.drawable.img_marc_vidal,
            R.drawable.img_ildefonso_guajardo,
            R.drawable.img_emilio_lozoya,
            R.drawable.img_luis_barrios,
            R.drawable.img_david_konzevik,
            R.drawable.img_renee_mauborgne,
            R.drawable.img_andy_cohen,
            R.drawable.img_blanca_trevino,
            R.drawable.img_andres_bustamante,
            R.drawable.arnoldo,
            R.drawable.img_christopher_gardner,
            R.drawable.emanuel,
            R.drawable.img_miguel_herrera

    };


    private String[] conferencias = {
            "La Creación de una Era de la Abundancia",
            "Tecnologías Creativas e Internet de las cosas",
            "Haz que todo el poder del Internet trabaje para tu Negocio",
            "Industrias Creativas para el Desarrollo en Latinoamérica",
            "Una hormiga en París",
            "Reformas Transformadoras",
            "Perspectivas de PEMEX en la industria Petrolera",
            "Convicción para emprender",
            "El emprendedor en un mundo globalizado",
            "La estrategia del Océano Azul",
            "El Poder de Pensar Diferente",
            "Tu futuro, tus fortalezas, tus decisiones",
            "La Creatividad en la visión de un Empresario",
            "El Sueño Mexicano",
            "Genética Espiritual y el Sueño Americano",
            "Cultura Ambiental: ¿Mito o realidad?",
            "Una Experiencia de Vida: Trabajo en Equipo"
    };


    private String[] speakerConferencia = {
            "Peter Diamandis",
            "David Cuartielles",
            "Arjan Dijk",
            "Carlos Argüello",
            "Marc Vidal",
            "Ildefonso G Villarreal",
            "Emilio Ricardo Loyola",
            "Luis Barrios",
            "David Konsevik",
            "Renée Mauborgne",
            "Andy Cohen",
            "Blanca Treviño",
            "Andrés Bustamante",
            "Arnoldo de la Rocha",
            "Christopher Gardner",
            "Emmanuel",
            "Piojo Herrera"
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
            streamingHolderView.initViewsFromIds(container, R.id.speaker_image, R.id.conference_title, R.id.hour_conference, R.id.speaker_color);
            container.setTag(streamingHolderView);


        } else {
            streamingHolderView = (ConferenceHolderView) container.getTag();
        }
        streamingHolderView.getTitleContainer().setText(conferencias[position]);
        streamingHolderView.getHourContainer().setText(speakerConferencia[position]);
        streamingHolderView.getSpeakerImageContainer().setImageResource(images[position]);


        if (position == 0 || position == 1) {

            streamingHolderView.getColor_conference().setBackgroundColor(Color.parseColor("#75378d3b"));
            streamingHolderView.getColor_conference().setText("Lunes");

        } else if (position == 2 || position == 3 || position == 4 || position == 5) {

            streamingHolderView.getColor_conference().setBackgroundColor(Color.parseColor("#75378d3b"));
            streamingHolderView.getColor_conference().setText("Martes");

        } else if (position == 6 || position == 7 || position == 8) {
            streamingHolderView.getColor_conference().setBackgroundColor(Color.parseColor("#75378d3b"));
            streamingHolderView.getColor_conference().setText("Miercoles");


        } else if (position == 9 || position == 10 || position == 11 || position == 12) {

            streamingHolderView.getColor_conference().setBackgroundColor(Color.parseColor("#75378d3b"));
            streamingHolderView.getColor_conference().setText("Jueves");

        }else if (position == 13 || position == 14 || position == 15 || position == 16 ) {

            streamingHolderView.getColor_conference().setBackgroundColor(Color.parseColor("#997d56C1"));
            streamingHolderView.getColor_conference().setText("Viernes");

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

        public void initViewsFromIds(View container, int speaker_photo, int title_conference, int hour_conference, int color_conference) {

            this.speaker_photo = (ImageView) container.findViewById(speaker_photo);
            this.title_conference = (TextView) container.findViewById(title_conference);
            this.hour_conference = (TextView) container.findViewById(hour_conference);
            this.color_conference = (TextView) container.findViewById(color_conference);


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

        public TextView getColor_conference() {
            return color_conference;
        }


    }
}
