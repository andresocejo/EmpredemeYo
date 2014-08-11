package mx.androidtitlan.semanadelemprendedor.main;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.crashlytics.android.Crashlytics;

import org.json.JSONException;
import org.json.JSONObject;

import mx.androidtitlan.semanadelemprendedor.MyActivity;
import mx.androidtitlan.semanadelemprendedor.R;

/**
 * Created by Jhordan on 04/08/14.
 */
public class Login_Screen extends Activity {

    private Button go;
    private EditText edt_email, edt_password;
    private TextView register, about;
    final Context context = this;


    String email;
    String gafete;
    String user_email;
    String user_gafete;
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Crashlytics.start(this);
        setContentView(R.layout.login_screen);
        hideBar();
        aboutShowDialog();

        edt_email = (EditText) findViewById(R.id.edt_email);
        edt_password = (EditText) findViewById(R.id.edt_password);
        prefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        user_email = prefs.getString("email_usr", "");
        user_gafete = prefs.getString("gafete_usr", "");

        registerCount();

        // Toast.makeText(this,user_email + user_gafete ,Toast.LENGTH_LONG).show();

        if (user_email != "" && user_gafete != "") {
            goHome();
            //Toast.makeText(Login_Screen.this, user_email + user_gafete, Toast.LENGTH_SHORT).show();
        } else {


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

            Toast.makeText(Login_Screen.this, "Por favor verifica Campos vacios", Toast.LENGTH_SHORT).show();

        } else if (valida_mail.equals("false")) {
            Toast.makeText(Login_Screen.this, "Email invalido!", Toast.LENGTH_SHORT).show();
        } else {
            loginUser(edt_email.getText().toString(), edt_password.getText().toString());
        }

    }

    private void goHome() {


        Intent intento = new Intent(Login_Screen.this, MyActivity.class);

        intento.putExtra("email", prefs.getString("email_usr", ""));
        intento.putExtra("gafete", prefs.getString("gafete_usr", ""));

        startActivity(intento);

        finish();

    }

    private boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void loginUser(final String email, final String gafete) {
        // savePrefereces(email, gafete);
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

                        Toast.makeText(Login_Screen.this, "Password o email invalido!", Toast.LENGTH_SHORT).show();


                    } else {

                        savePrefereces(email, gafete);


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

        postReq.setRetryPolicy(new DefaultRetryPolicy(17000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        rq.add(postReq);


    }


    private void savePrefereces(String email, String gafetess) {

        SharedPreferences prefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = prefs.edit();

        editor.putString("email_usr", email);
        editor.putString("gafete_usr", gafetess);
        editor.commit();

    }


    private void registerCount() {
        register = (TextView) findViewById(R.id.register_acount);
        register.setTypeface(null, Typeface.BOLD);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    goPagePrincipal();
                } catch (Exception e) {
                    Toast.makeText(Login_Screen.this, "Error en la Red Verifica tu conexión!", Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    private void goPagePrincipal() {
        String url = "http://semanadelemprendedor.gob.mx/";
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);


    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void hideBar() {

        ActionBar bar = getActionBar();
        bar.hide();
    }

    private void aboutShowDialog() {

        about = (TextView) findViewById(R.id.aboutLogo);

        about.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.HONEYCOMB)
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(context);

                dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialoghelp);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();


            }
        });


    }


}
