package com.example.adi.playforlearnpfl;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class SezioneClassi extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_classi);

        getSupportActionBar().setTitle("Sezione Classi");
    }

    public void listaAlunni(View v){
        Intent i = new Intent(getApplicationContext(),ListaAlunni.class);
       String strVal= ((Button)v).getText().toString();
        Log.d("DEBUG_list", strVal);
        i.putExtra("classe", ((Button)v).getText());
        startActivity(i);
        finish();
    }
}

//todo javadoc