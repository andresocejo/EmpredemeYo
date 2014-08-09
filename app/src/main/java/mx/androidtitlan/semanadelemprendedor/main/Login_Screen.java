package mx.androidtitlan.semanadelemprendedor.main;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import mx.androidtitlan.semanadelemprendedor.MyActivity;
import mx.androidtitlan.semanadelemprendedor.R;

import org.json.JSONException;
import org.json.JSONObject;

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

        SharedPreferences prefs =
                getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);

        String user_email = prefs.getString("email_usr", "email");
        String user_gafete = prefs.getString("gafete_usr", "gafee");

       // Toast.makeText(this,user_email + user_gafete ,Toast.LENGTH_LONG).show();

        if (user_email == user_email && user_gafete == user_gafete) {
            goHome();
        }



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
        }  else {
            loginUser(edt_email.getText().toString(), edt_password.getText().toString());
        }

    }

    private void goHome() {

        email = edt_email.getText().toString();
        gafete = edt_password.getText().toString();

        Intent intento = new Intent(Login_Screen.this, MyActivity.class);

        intento.putExtra("email", email);
        intento.putExtra("gafete", gafete);

        startActivity(intento);

    }

    private boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void loginUser(final String email, final String gafete) {
        savePrefereces(email, gafete);
        // String gafete="010176";
        // String email= "javierr@infoexpo.com.mx";
        RequestQueue rq = Volley.newRequestQueue(this);
        String url = "http://se.infoexpo.mx/2014/ae/web/utilerias/ws/google/get_attendee?idVisitante=" + gafete + "&email=" + email;
        final ProgressDialog progressDialog = ProgressDialog.show(this, "Espera por Favor ...", "Validando datos ...", true);

        StringRequest postReq = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.i("Response Emprendedor", response);

                try {


                    JSONObject jsonObject = new JSONObject(response);
                    String estado = jsonObject.getString("status");
                    String datas = jsonObject.getString("data");


                    String jsonText = estado;
                    String dataText = datas;


                    //Toast.makeText(Login_Screen.this, jsonText + " " + dataText, Toast.LENGTH_SHORT).show();


                    if (jsonText.equals("true") && dataText.equals("[]")) {

                        Toast.makeText(Login_Screen.this, "Error password", Toast.LENGTH_SHORT).show();


                    } else {

                        goHome();


                    }


                } catch (JSONException e) {
                    Log.i("ERROR", "ERROR");
                }

                progressDialog.dismiss();
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


    private void savePrefereces(String email, String gafetess) {

        SharedPreferences prefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = prefs.edit();

        editor.putString("email_usr", email);
        editor.putString("gafete_usr", gafetess);
        editor.commit();

    }


}
