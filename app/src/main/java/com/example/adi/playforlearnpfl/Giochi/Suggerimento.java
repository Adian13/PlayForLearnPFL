package com.example.adi.playforlearnpfl.Giochi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.adi.playforlearnpfl.R;

/**
 * Created by raffaeledellaporta on 20/12/2016.
 */
public class Suggerimento extends AppCompatActivity {
    private TextView textViewQuesito;
    private TextView textViewRisposta;
    private String quesito;
    private boolean risposta;
    private boolean rispostaMostrata = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_suggerimento);

        textViewQuesito = (TextView) findViewById(R.id.testoQuesito);
        textViewRisposta = (TextView) findViewById(R.id.testoRisposta);

        Intent i = getIntent();
        quesito = i.getStringExtra("TESTO_QUESITO");
        risposta = i.getBooleanExtra("RISPOSTA_QUESITO", risposta);

        textViewQuesito.setText(quesito);
        setReturnIntent();
    }

    private void setReturnIntent() {
        Intent data = new Intent();
        data.putExtra("RISPOSTA_MOSTRATA", rispostaMostrata);
        setResult(RESULT_OK,data);
    }

    public void onClickMostra(View v) {
        textViewRisposta.setText(""+risposta);
        //rispostaMostrata = true;
        setReturnIntent();
    }

    public void onClickTorna(View v) {
        Intent i = new Intent(getApplicationContext(), DolceMatematica.class);
        startActivity(i);
        finish();
    }
}

