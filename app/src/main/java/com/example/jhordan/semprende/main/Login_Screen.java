package com.example.jhordan.semprende.main;

import android.app.ActionBar;
import android.app.Activity;
import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.jhordan.semprende.MyActivity;
import com.example.jhordan.semprende.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jhordan on 04/08/14.
 */
public class Login_Screen extends Activity {

    private Button go;
    private EditText edt_email, edt_password;
    String email;
    String gafete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);

        edt_email = (EditText) findViewById(R.id.edt_email);
        edt_password = (EditText) findViewById(R.id.edt_password);


        go = (Button) findViewById(R.id.btn_login);
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                validaEditTex();

            }
        });

    }

    private void validaEditTex() {

        boolean mailvalido = isEmailValid(edt_email.getText().toString());
        String valida_mail = Boolean.toString(mailvalido);

        if (edt_email.getText().toString().equals("") || edt_password.getText().toString().equals("")) {

            Toast.makeText(Login_Screen.this, "Campos vacios", Toast.LENGTH_SHORT).show();

        } else if (valida_mail.equals("false")) {
            Toast.makeText(Login_Screen.this, "email invalido!", Toast.LENGTH_SHORT).show();
        } else {
            loginUser(edt_email.getText().toString(), edt_password.getText().toString());
        }

    }

    private void goHome() {

        Intent intento = new Intent(Login_Screen.this, MyActivity.class);
        startActivity(intento);

    }

    private boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void loginUser(final String email, final String gafete) {

        // String gafete="010176";
        // String email= "javierr@infoexpo.com.mx";
        RequestQueue rq = Volley.newRequestQueue(this);
        String url = "http://se.infoexpo.mx/2014/ae/web/utilerias/ws/google/get_attendee?idVisitante=" + gafete + "&email=" + email;

        StringRequest postReq = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.i("Response Emprendedor", response);

                try {


                    JSONObject jsonObject = new JSONObject(response);
                    String estado = jsonObject.getString("status");
                    String data = jsonObject.getString("data");

                    String jsonText = estado;
                    String dataText = data;
                    //  Toast.makeText(Login_Screen.this, jsonText + " " + dataText, Toast.LENGTH_SHORT).show();

                    if (jsonText.equals("true") && dataText.equals("[]")) {

                        Toast.makeText(Login_Screen.this, "Error password", Toast.LENGTH_SHORT).show();


                    } else {
                        goHome();
                    }


                } catch (JSONException e) {
                    Log.i("ERROR", "ERROR");
                }


            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Error [" + error + "]");

            }
        })

        {
            /*@Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", "javierr@infoexpo.com.mx");
                params.put("idVisitante", "010176");

                return params;
            }*/


        };

        rq.add(postReq);


    }

}
