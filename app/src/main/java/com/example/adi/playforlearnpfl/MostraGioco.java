package com.example.adi.playforlearnpfl;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by raffaeledellaporta on 08/01/2017.
 */
public class MostraGioco extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mostra_gioco);

        }
    public void mostraGioco(View v){
        Intent i= new Intent(getApplicationContext(),QuizActivity.class);
        startActivity(i);
        finish();
    }
    }