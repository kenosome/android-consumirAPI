package com.sala4.uao.postget;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences pref =  PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

                String usuario= pref.getString("usuario","NO_USER");

                if(usuario.equals("NO_USER")){
                    //Si no estan guardados los datos del usuario vaya a Login
                    Intent i = new Intent(getApplicationContext(),Login.class);
                    startActivity(i);
                    finish();
                }else{
                    //Vaya a Contenido
                    Intent i = new Intent(getApplicationContext(),Contenido.class);
                    startActivity(i);
                    finish();
                }

            }
        }, 3000);
    }

}
