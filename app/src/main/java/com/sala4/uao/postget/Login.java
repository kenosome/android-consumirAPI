package com.sala4.uao.postget;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by sala4 on 14/05/2016.
 */
public class Login extends Activity {

    EditText txtUsuario, txtPassword;
    Button btnEntrar;



    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        setContentView(R.layout.login);

        txtUsuario=(EditText) findViewById(R.id.txtUsuario);
        txtPassword=(EditText) findViewById(R.id.txtUsuario);
        btnEntrar=(Button) findViewById(R.id.btnEntrar);

    }

    public void btnRegistrarse_onClick(View view) {
        Intent i = new Intent(this,Registro.class);
        startActivity(i);
    }

    public void btnEntrar_onClick(View view) {

        HttpThread hilo= new HttpThread();
        hilo.execute(txtUsuario.getText().toString(),txtPassword.getText().toString());

    }

    public class HttpThread extends AsyncTask<String,String,String>{

        @Override
        protected String doInBackground(String... params) {


            return null;
        }
    }

}
