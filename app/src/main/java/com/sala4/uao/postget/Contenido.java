package com.sala4.uao.postget;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import Util.WebUtilDomi;

/**
 * Created by sala4 on 14/05/2016.
 */
public class Contenido extends Activity{

    TextView salida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.contenido);

        salida=(TextView) findViewById( R.id.salida);
        salida.setMovementMethod(new ScrollingMovementMethod());

    }

    public void btnPedirUsuarios_onClick(View view) {
        PedirUsuarios hilo= new PedirUsuarios();
        hilo.execute();
    }

    public void btnPedirNoticias_onClick(View view) {
        SharedPreferences pref= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String usuario=pref.getString("usuario","NO_USER");
        String password= pref.getString("password","NO_PASS");

        PedirNoticias hilo= new PedirNoticias();
        hilo.execute(usuario,password);

    }

    public void btnCerrarSesion_onClick(View view) {
        SharedPreferences pref= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        pref.edit().clear().commit();
        Intent i =new Intent(this,Login.class);
        startActivity(i);
        finish();

    }

    public class PedirUsuarios extends AsyncTask<String,String,String>{


        @Override
        protected String doInBackground(String... params) {
            try {

                String response= WebUtilDomi.GETrequest("http://192.168.173.1:8080/WebService/webresources/service/getallusers");
                return response;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            salida.setText(s);

        }
    }

    public class PedirNoticias extends AsyncTask<String,String,String>{


        @Override
        protected String doInBackground(String... params) {
            try {

                Uri.Builder uribuilder= new Uri.Builder();
                uribuilder.appendQueryParameter("usuario", params[0])
                        .appendQueryParameter("password", params[1]);
                String response= WebUtilDomi.POSTrequest("http://192.168.173.1:8080/WebService/webresources/service/getallnoticias", uribuilder);
                return response;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            salida.setText(s);

        }
    }
}
