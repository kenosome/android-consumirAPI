package com.sala4.uao.postget;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/**
 * Created by sala4 on 14/05/2016.
 */
public class Login extends Activity {




    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        setContentView(R.layout.login);
    }

    public void btnRegistrarse_onClick(View view) {
        Intent i = new Intent(this,Registro.class);
        startActivity(i);
    }
}
