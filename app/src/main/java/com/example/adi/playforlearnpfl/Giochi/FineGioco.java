package com.example.adi.playforlearnpfl.Giochi;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.adi.playforlearnpfl.Alunno.HomeAlunno;
import com.example.adi.playforlearnpfl.Games;
import com.example.adi.playforlearnpfl.R;
import com.example.adi.playforlearnpfl.Record.RecordPersonali;

/**
 * Created by Adi on 18/01/2017.
 */
public class FineGioco extends AppCompatActivity{
   public TextView txErr, txRec;
    int errori;
    int record;
    Button rigioca;
    String activity, materia;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fine_gioco);
        RatingBar bar=(RatingBar)findViewById(R.id.ratingBar1);
        bar.setNumStars(7);
        bar.setStepSize(0.5f);
        Bundle b = getIntent().getExtras();
        int score= b.getInt("record");
        materia = getIntent().getStringExtra("MATERIA");
        errori= getIntent().getIntExtra("errori", 0);
        record= getIntent().getIntExtra("record", 0);
        bar.setRating(record);
         activity = getIntent().getStringExtra("activity");
        txErr= (TextView)findViewById(R.id.txerrori);
        //txErr.setText("errori");
        txErr.setText("Errori:"+errori);
        txRec= (TextView)findViewById(R.id.record);
        txRec.setText("Il tuo record di risposte esatte è: "+ record);
        RecordPersonali.aggiornaRecord(getIntent().getStringExtra("MATERIA"),record, getApplicationContext());
        rigioca= (Button) findViewById(R.id.rigioca);
        rigioca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (activity){
                    case "DolceMatematica":
                        Intent i= new Intent(getApplicationContext(), DolceMatematica.class);
                        startActivity(i);
                        finish();
                        break;
                    case "ActivityGiocoStoGioIng":
                        Intent intent= new Intent(getApplicationContext(), ActivityGiocoStoGioIng.class);
                        intent.putExtra("MATERIA", materia);
                        startActivity(intent);
                        finish();
                        break;
                    case "QuizActivityItaliano":
                        Intent intent1 = new Intent(getApplicationContext(), QuizActivityItaliano.class);
                        startActivity(intent1);
                        finish();
                        break;
                    default: Intent intent2= new Intent(getApplicationContext(), HomeAlunno.class);                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        Intent i= new Intent(getApplicationContext(), HomeAlunno.class);
        startActivity(i);
        finish();
    }
}
