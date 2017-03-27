package com.example.adi.playforlearnpfl.Giochi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.Surface;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adi.playforlearnpfl.R;

/**
 *  DolceMatematica è la classe che contiene un gioco di matematica del sistema PlayForLearn. E' formato da una serie di
 *  domande che verranno poste all'utente, e dovrà rispondere correttamente premendo se la risposta è vera o falsa!
 */
public class DolceMatematica extends AppCompatActivity {

    private TextView numeroQuesito;
    private TextView testoQuesito;
    private TextView risposteCorretteValide;
    private TextView risposteErrate;
    private TextView risposteTotali;

    private int quesito_corrente = 0;
    private int valid_correct_answers = 0;
    private int non_valid_correct_answers = 0;
    private int total_answers = 0;

    private Quiz[] arrayDomande = new Quiz[] {
            new Quiz("Il risultato di 20:2 = 10", true),
            new Quiz("Il risultato di 1+1 = 11", false),
            new Quiz("Il risultato di 200-100 = 100", true),
            new Quiz("Il risultato di 2x10 = 105", false),
            new Quiz("Il risultato di 40*4 = 160", true),
            new Quiz("Il risultato di 18*3 = 54", true),
            new Quiz("Il risultato di 40-18 = 22", true),
            new Quiz("Il risultato di 25:5 = 4", false),
    };

    private final int NUM_QUESITI = arrayDomande.length;

    private boolean[] suggVisto = new boolean[NUM_QUESITI];

    private void aggiornaQuesito() {
        int n = quesito_corrente+1;
        testoQuesito.setText(arrayDomande[quesito_corrente].getTesto());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.dolce_matematica);

        getSupportActionBar().setTitle("Dolce Matematica");
        Display display = getWindowManager().getDefaultDisplay();


        int rotation = display.getRotation();
        switch(rotation) {
            case Surface.ROTATION_90:
                setContentView(R.layout.activity_dolce_matematica);
                break;
            // case Surface.ROTATION_0:

            //   break;
            /*case Surface.ROTATION_180:
                Intent c= new Intent(getApplicationContext(),DolceMatematica1.class);
                startActivity(c);
                finish();
                break;
            case Surface.ROTATION_270:
                Intent b= new Intent(getApplicationContext(),DolceMatematica1.class);
                startActivity(b);
                finish();
                //break;
*/
        }


        numeroQuesito = (TextView) findViewById(R.id.numeroQuesito);
        testoQuesito = (TextView) findViewById(R.id.testoQuesito);
        aggiornaQuesito();
    }

    /**
     * Metodo onClickAltroQuesito effettua lo spostamento da un quesito all'altro con un semplice click sui due
     * pulsante Prev e Succ.
     * @param v è il pulsante riferito a prev o succ.
     * @throws Exception
     */
    public void onClickAltroQuesito(View v) throws Exception {
        Button b = (Button) v;
        switch(b.getId()) {
            case R.id.buttonPrev: if(quesito_corrente==0) quesito_corrente=+8; else
                quesito_corrente--; break;
            case R.id.buttonSucc: quesito_corrente++; break;
            default: throw new Exception("Should not be here (prev/succ)");
        }
        quesito_corrente = quesito_corrente % NUM_QUESITI;
        aggiornaQuesito();
    }

    /**
     * Metodo onClickRisposta rappresenta la risposta data dall'utente e la confronta con la risposta salvata. Se sono uguali
     * restituisce:"Giusto", altrimenti restituisce:"Sbagliato".
     * @param v rappresenta il pulsante premuto, uno dei due pulsanti Vero o also.
     * @throws Exception viene lanciata una eccezione controllata se non viene date una risposta
     */

    public void onClickRisposta(View v) throws Exception {
        total_answers++;
        Button b = (Button) v;
        Quiz q = arrayDomande[quesito_corrente];
        boolean risposta_corretta = q.getRisposta();
        boolean risposta;
        String str;
        switch (b.getId()) {
            case R.id.buttonTrue:
                risposta = true;
                break;
            case R.id.buttonFalse:
                risposta = false;
                break;
            default:
                throw new Exception("Should not be here (true/false)");
        }
        str = (risposta == risposta_corretta) ? "Giusto!!!" : "Sbagliato.";
        Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();

        if (risposta == risposta_corretta) {

            valid_correct_answers++;
        } else {
            non_valid_correct_answers++;
        }
        aggiornaQuesito();

        quesito_corrente++;
        quesito_corrente = quesito_corrente % NUM_QUESITI;

        if (quesito_corrente < 7) {
            Log.d("DEBUG_num", String.valueOf(quesito_corrente));
            Log.d("DEBUG_valori", String.valueOf(arrayDomande.length));
        }
        else{
            Intent d = new Intent(getApplicationContext(), FineGioco.class);
            d.putExtra("record",valid_correct_answers);
            d.putExtra("activity","DolceMatematica");
            startActivity(d);
            finish();
        }

        aggiornaQuesito();
    }

    /**
     *Metodo onClickSuggerimento rappresenta una chiamata ad una nuova classe : "Suggerimento.class" che in base alla domanda che
     * l'utente deve rispondere, può usufluire di un suggerimento.
     * @param v pulsante :"Suggerimento".
     */
    public void onClickSuggerimento(View v) {
        Intent i = new Intent(getApplicationContext(),Suggerimento.class);
        i.putExtra("TESTO_QUESITO", arrayDomande[quesito_corrente].getTesto());
        i.putExtra("RISPOSTA_QUESITO", arrayDomande[quesito_corrente].getRisposta());
        startActivity(i);
        finish();

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode != 0) return;
        if (resultCode != Activity.RESULT_OK) return;
        if (data == null) return;
        suggVisto[quesito_corrente] = data.getBooleanExtra("RISPOSTA_MOSTRATA", false);
        // Toast.makeText(getApplicationContext(), "Visto: "+suggVisto[quesito_corrente], Toast.LENGTH_LONG).show();
    }
}
