package com.example.jhordan.semprende.main;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.jhordan.semprende.MyActivity;
import com.example.jhordan.semprende.R;

/**
 * Created by Jhordan on 04/08/14.
 */
public class Login_Screen extends Activity {

    private Button go;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);

        go = (Button) findViewById(R.id.button);
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intento = new Intent(Login_Screen.this, MyActivity.class);
                startActivity(intento);
            }
        });

    }
}
