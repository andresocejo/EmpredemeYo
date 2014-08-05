package com.example.jhordan.semprende.main;


import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import com.example.jhordan.semprende.R;

import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by Jhordan on 04/08/14.
 */
public class Splash_Screen extends Activity {


    private static final long SPLASH_SCREEN_DELAY = 700;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        hideBar();
        goLogin();


    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void hideBar() {
        android.app.ActionBar bar = getActionBar();
        bar.hide();

    }

    private void goLogin() {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                // Start the next activity
                Intent intento = new Intent().setClass(Splash_Screen.this, Login_Screen.class);
                startActivity(intento);

                // Close the activity so the user won't able to go back this
                // activity pressing Back button
                finish();

            }
        };
        // Simulate a long loading process on application startup.
        Timer timer = new Timer();
        timer.schedule(task, SPLASH_SCREEN_DELAY);

    }
}
