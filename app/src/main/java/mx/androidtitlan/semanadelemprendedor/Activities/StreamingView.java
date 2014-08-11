package mx.androidtitlan.semanadelemprendedor.Activities;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import mx.androidtitlan.semanadelemprendedor.R;

/**
 * Created by Jhordan on 10/08/14.
 */
public class StreamingView extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    String apikey = "AIzaSyDXLdUf0hQat1uFHqWw1GNSmS_wQda_RV4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.streaming_view);

        hideBar();


        if (isAppInstalled("com.google.android.youtube") == true) {

            try {
                YouTubePlayerView youTubeView = (YouTubePlayerView) findViewById(R.id.youtube_view);
                youTubeView.initialize(apikey, this);


            } catch (Exception e) {

                Toast.makeText(StreamingView.this, "Tenemos algunos Inconvenientes para cargar el video!", Toast.LENGTH_LONG).show();

            }

        } else {


            try {
                Toast.makeText(StreamingView.this, "Por favor descarga la aplicación de Youtube y vuelve a intentarlo", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.youtube&hl=es_419"));
                startActivity(intent);
            } catch (Exception e) {
                Toast.makeText(StreamingView.this, "Error en la Red Verifica tu conexión!", Toast.LENGTH_LONG).show();

            }


        }


    }


    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider,
                                        YouTubeInitializationResult result) {
        Toast.makeText(this,
                "Error " + result.toString(),
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player,
                                        boolean wasRestored) {
        if (!wasRestored) {

           // player.addFullscreenControlFlag(YouTubePlayer.FULLSCREEN_FLAG_CUSTOM_LAYOUT);
            // player.cueVideo("QkeTbVHjWWI");//carga al presionar el boton
            //   player.loadVideo("-CDlu4EVeNU");//carga automaticamente
           String conference= StreamingView.this.getIntent().getStringExtra("id_video");
            player.loadVideo(conference);
            //player.setFullscreen(true);



        }
    }

    private boolean isAppInstalled(String uri) {
        PackageManager pm = getPackageManager();
        boolean installed = false;
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            installed = true;
        } catch (PackageManager.NameNotFoundException e) {
            installed = false;
        }
        return installed;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void hideBar() {

        ActionBar bar = getActionBar();
        bar.hide();
    }


}
