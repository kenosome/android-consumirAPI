package com.sala4.uao.postget;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import Util.WebUtilDomi;

/**
 * Created by sala4 on 14/05/2016.
 */
public class Registro extends Activity{

    EditText txtNombre, txtUsuario, txtPassword;
    Button btnRegistrarse;
    TextView txtvwSalida;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.registro);

        txtNombre=(EditText) findViewById(R.id.txtNombre);
        txtUsuario=(EditText) findViewById(R.id.txtUsuario);
        txtPassword=(EditText) findViewById(R.id.txtPassword);
        btnRegistrarse=(Button) findViewById(R.id.btnRegistrarse);
        txtvwSalida=(TextView) findViewById(R.id.txtvwSalida);

        txtvwSalida.setMovementMethod(new ScrollingMovementMethod());


    }

    public void btnRegistrarse_onClick(View view) {

        HttpThread hilo=new HttpThread();
        hilo.execute(txtNombre.getText().toString(),
                     txtUsuario.getText().toString(),
                     txtPassword.getText().toString());

    }

    public class HttpThread extends AsyncTask<String,String,String>{

        @Override
        protected String doInBackground(String... params) {

            try {

                Uri.Builder uribuilder= new Uri.Builder();
                uribuilder.appendQueryParameter("nombre",params[0])
                            .appendQueryParameter("usuario", params[1])
                            .appendQueryParameter("password", params[2]);
                //String response= WebUtilDomi.GETrequest("http://www.google.com.co");
                String response= WebUtilDomi.POSTrequest( "http://192.168.173.1:8080/WebService/webresources/service/crear_usuario" ,uribuilder);
                return response;
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            txtvwSalida.setText(s);

            if(s.equals("SUCCESS")){
                Intent i= new Intent(getApplicationContext(),Login.class);
                startActivity(i);
            }else{
                Toast.makeText(getApplicationContext(),"El usuario ya existe", Toast.LENGTH_LONG).show();
            }

        }
    }

}
