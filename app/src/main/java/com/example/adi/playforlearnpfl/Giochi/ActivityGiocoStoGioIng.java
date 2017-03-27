package com.example.adi.playforlearnpfl.Giochi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.adi.playforlearnpfl.Alunno.HomeAlunno;
import com.example.adi.playforlearnpfl.LoginActivity;
import com.example.adi.playforlearnpfl.R;
import com.example.adi.playforlearnpfl.Record.RecordPersonali;
import com.example.adi.playforlearnpfl.RegistrazioneAlunno;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import static com.example.adi.playforlearnpfl.Alunno.HomeAlunno.GEOGRAFIA;
import static com.example.adi.playforlearnpfl.Alunno.HomeAlunno.INGLESE;
import static com.example.adi.playforlearnpfl.Alunno.HomeAlunno.ITALIANO;
import static com.example.adi.playforlearnpfl.Alunno.HomeAlunno.MATEMATICA;
import static com.example.adi.playforlearnpfl.Alunno.HomeAlunno.MATERIA;
import static com.example.adi.playforlearnpfl.Alunno.HomeAlunno.STORIA;

/**
 *Giochi suddivisi per materia
 */

public class ActivityGiocoStoGioIng extends AppCompatActivity implements Serializable {
    private String nome, materia, classe;
    int errori, numEs, record, idGioco;
    ArrayList<Gioco_StoGeoIng> giochiDaLanciare;
    private Drawable icona;
    Integer immagine;
    Bundle utenteBundle;
    Gioco_StoGeoIng gioco;
    public Button bt1, bt2, bt3, bt4;

    public String soluzione, opzione1, opzione2, opzione3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        utenteBundle= getIntent().getBundleExtra("utente");
        numEs = getIntent().getIntExtra("Indice", 0);
        errori = getIntent().getIntExtra("errori", errori);
        record = getIntent().getIntExtra("record", errori);
        idGioco = getIntent().getIntExtra("idGioco", 0);
        //String username=
        setContentView(R.layout.activity_gioco);
        bt2 = (Button) findViewById(R.id.bt2);
        bt1 = (Button) findViewById(R.id.bt1);
        bt3 = (Button) findViewById(R.id.bt3);
        bt4 = (Button) findViewById(R.id.bt4);
        giochiDaLanciare = new ArrayList<Gioco_StoGeoIng>();
        materia = getIntent().getStringExtra("MATERIA");
        Log.d("DEBUG", materia);
        //Gioco_StoGeoIng gioco= new Gioco_StoGeoIng(icona,  nome,  materia, classe, errori, immagine, soluzione,  opzione1, opzione2,  opzione3);

        if (materia.compareTo(STORIA) == 0) {
            giochiDaLanciare.add(new Gioco_StoGeoIng(3, R.drawable.darenome_casa_lampada, "Lampada", "Letto", "Divano", "Sedia"));
            giochiDaLanciare.add(new Gioco_StoGeoIng(3, R.drawable.darenome_casa_letto, "Letto", "Tavolo", "Divano", "Sedia"));
            giochiDaLanciare.add(new Gioco_StoGeoIng(3, R.drawable.darenome_casa_padella, "Padella", "Piatto", "Tavolo", "Cucchiaio"));
            giochiDaLanciare.add(new Gioco_StoGeoIng(3, R.drawable.darenome_casa_vasca, "Vasca", "Water", "Sedia", "Scrivania"));
            giochiDaLanciare.add(new Gioco_StoGeoIng(3, R.drawable.cane, "Cane", "Pizza", "Colore", "Cosa"));
            giochiDaLanciare.add(new Gioco_StoGeoIng(3, R.drawable.darenome_casa_vasca, "Vasca", "Water", "Sedia", "Scrivania"));
            giochiDaLanciare.add(new Gioco_StoGeoIng(3, R.drawable.cane, "Cane", "Pizza", "Colore", "Cosa"));
            giochiDaLanciare.add(new Gioco_StoGeoIng(3, R.drawable.graffiti, "Graffiti", "fiume", "computer", "fiore"));
            giochiDaLanciare.add(new Gioco_StoGeoIng(3, R.drawable.piramide, "Piramide", "Pappagallo", "Nonno", "Barca"));
            giochiDaLanciare.add(new Gioco_StoGeoIng(3, R.drawable.casa, "Casa", "Piscina", "Cortile", "Pallone"));
        } else if (materia.compareTo(GEOGRAFIA) == 0) {
            giochiDaLanciare.add(new Gioco_StoGeoIng(4, R.drawable.piramide, "Piramide", "Pappagallo", "Nonno", "Barca"));
            giochiDaLanciare.add(new Gioco_StoGeoIng(4, R.drawable.prato, "Prato", "animale", "penna", "pingu"));
            giochiDaLanciare.add(new Gioco_StoGeoIng(4, R.drawable.sole, "Sole", "Sardegna", "Sardina", "mamma"));
            giochiDaLanciare.add(new Gioco_StoGeoIng(4, R.drawable.piramide, "Piramide", "Pappagallo", "Nonno", "Barca"));
            giochiDaLanciare.add(new Gioco_StoGeoIng(4, R.drawable.prato, "Prato", "animale", "penna", "pingu"));
            giochiDaLanciare.add(new Gioco_StoGeoIng(4, R.drawable.stelle, "Stelle", "mappa", "cappa", "mamma"));
            giochiDaLanciare.add(new Gioco_StoGeoIng(4, R.drawable.mare, "Mare", "pesce", "carne", "posto"));
            giochiDaLanciare.add(new Gioco_StoGeoIng(4, R.drawable.pietra, "pietra", "animale", "penna", "pingu"));
            giochiDaLanciare.add(new Gioco_StoGeoIng(4, R.drawable.montagna, "Montagna", "heidi", "capre", "fiume"));
        } else if (materia.compareTo(INGLESE) == 0) {
            giochiDaLanciare.add(new Gioco_StoGeoIng(4, R.drawable.mare, "sea", "pesce", "carne", "posto"));
            giochiDaLanciare.add(new Gioco_StoGeoIng(4, R.drawable.pietra, "stone", "animale", "penna", "pingu"));
            giochiDaLanciare.add(new Gioco_StoGeoIng(4, R.drawable.montagna, "mountain", "heidi", "capre", "fiume"));
            giochiDaLanciare.add(new Gioco_StoGeoIng(5, R.drawable.piramide, "pyramid", "Pappagallo", "Nonno", "Barca"));
            giochiDaLanciare.add(new Gioco_StoGeoIng(5, R.drawable.prato, "Prato", "animale", "penna", "pingu"));
            giochiDaLanciare.add(new Gioco_StoGeoIng(5, R.drawable.piramide, "Piramide", "Pappagallo", "Nonno", "Barca"));
            giochiDaLanciare.add(new Gioco_StoGeoIng(5, R.drawable.prato, "flowers", "animale", "penna", "pingu"));
            giochiDaLanciare.add(new Gioco_StoGeoIng(5, R.drawable.sole, "Sun", "Sardegna", "Sardina", "mamma"));
            giochiDaLanciare.add(new Gioco_StoGeoIng(5, R.drawable.stelle, "Stars", "mappa", "cappa", "mamma"));

        } else if (materia.compareTo(MATEMATICA) == 0) {
            giochiDaLanciare.add(new Gioco_StoGeoIng(2, R.drawable.images, "15", "a", "d", "casa"));
            giochiDaLanciare.add(new Gioco_StoGeoIng(2, R.drawable.calcoli, "55", "125", "60", "kiwi"));
            giochiDaLanciare.add(new Gioco_StoGeoIng(2, R.drawable.ombra_ciclisti, "C G F", "125", "nessuna", "kiwi"));
            giochiDaLanciare.add(new Gioco_StoGeoIng(2, R.drawable.ombra_sciatori, "A B H", "125", "nessuna", "kiwi"));
            giochiDaLanciare.add(new Gioco_StoGeoIng(2, R.drawable.ombra_sciatori, "A B H", "125", "nessuna", "kiwi"));
            giochiDaLanciare.add(new Gioco_StoGeoIng(2, R.drawable.ombra_cavallo, "C I J", "ciuccio", "galoppiamo", "nessuno"));
            giochiDaLanciare.add(new Gioco_StoGeoIng(2, R.drawable.sfere, "7", "appari drago", "1", "5"));

        }

        gioco = giochiDaLanciare.get(numEs);
        ArrayList<String> opzioni = new ArrayList<String>(4);
        opzioni.add(gioco.getSoluzione());
        opzioni.add(gioco.getOpzione1());
        opzioni.add(gioco.getOpzione2());
        opzioni.add(gioco.getOpzione3());
        ArrayList<Button> pulsanti = new ArrayList<Button>(4);
        pulsanti.add(bt1);
        pulsanti.add(bt2);
        pulsanti.add(bt3);
        pulsanti.add(bt4);
        setOptions(opzioni, pulsanti);

        //   esercizi.add(esercizi di storia);
        //}
        makeBind();
    }

    private void setOptions(ArrayList<String> opzioni, ArrayList<Button> pulsanti) {
        int p = 0, o = 0;
        for (int i = 0; i < 4; i++) {
            p = getRandomInt(pulsanti.size());
            o = getRandomInt(opzioni.size());
            pulsanti.get(p).setText(opzioni.get(o));
            pulsanti.remove(p);
            opzioni.remove(o);
        }
    }

    private void makeBind() {
        ImageView imageView = (ImageView) findViewById(R.id.ivImmagine);
        imageView.setImageResource((giochiDaLanciare.get(numEs)).getImmagine());
        //imageView.refreshDrawableState();

        MyOnClick myOnClick = new MyOnClick();
        bt1.setOnClickListener(myOnClick);
        bt2.setOnClickListener(myOnClick);
        bt3.setOnClickListener(myOnClick);
        bt4.setOnClickListener(myOnClick);
    }

    public class MyOnClick implements View.OnClickListener {
        public void onClick(View v) {
            Button bt = (Button) v;
            checkSoluzione(giochiDaLanciare.get(numEs), bt.getText().toString());
            // numEs++;
            avviaSuccessivoOFine();
        }
    }

    //TODO controllo su quale layout caricare

    /**
     * controlla se la soluzione è corretta
     * @param gioco oggetto da controllare
     * @param opzione parametro contenente la risposta
     * @return true se è corretta false altrimenti
     */
    public boolean checkSoluzione(Gioco_StoGeoIng gioco, String opzione) {
        if (opzione.compareTo(gioco.getSoluzione()) == 0) {
            record++;
            avviaSuccessivoOFine();
            return true;
        } else {
            errori++;
            avviaSuccessivoOFine();
            return false;
        }
    }

    /**
     * cambia la domanda del gioco
     */
    public void avviaSuccessivoOFine() {
        utenteBundle= getIntent().getBundleExtra("utente");
        if (numEs <= 7) {
            Intent i = new Intent(this, ActivityGiocoStoGioIng.class);
            numEs++;
            i.putExtra("Indice", numEs);
            i.putExtra(MATERIA, materia);
            i.putExtra("errori", errori);
            i.putExtra("record", record);
            i.putExtra("utente", utenteBundle);
            startActivity(i);
            finish();
        } else if (numEs > 7) {
            salvaRecord(record);
            Intent i = new Intent(getApplicationContext(), FineGioco.class);
            i.putExtra("errori", errori);
            i.putExtra("utente", utenteBundle);
            i.putExtra("record", record);
            i.putExtra("MATERIA", materia);
            i.putExtra("activity", "ActivityGiocoStoGioIng");
            salvaRecord(record);
            i.putExtra("idGioco", idGioco);
            startActivity(i);
            finish();
        }
    }


    private Integer getRandomInt(int max) {
        int n = ((int) Math.round(Math.random() * max) % max);
        if (n >= 0) Log.d("DEBUG", "Il numero casuale scelto è " + n);
        return n >= 0 ? n : max - 1;
    }

    /**
     * permette di tornare alla schermata precedente
     */
    @Override
    public void onBackPressed() {
        utenteBundle= getIntent().getBundleExtra("utente");
        super.onBackPressed();
        Intent i = new Intent(getApplicationContext(), HomeAlunno.class);
        i.putExtra("utente", utenteBundle);
        startActivity(i);
        finish();
    }

    /**
     * salva il record nel database
     * @param record
     */
    public void salvaRecord(final int record) {
        new AsyncTask<Object, Object, Object>() {
            @Override
            protected Object doInBackground(Object... params) {
                try {
                    String ip = LoginActivity.INDIRIZZO;
                    int porta = 80;
                    String nomeFile = "webservice/salvataggio.php";
                    URL url = new URL("http", ip, porta, nomeFile);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setChunkedStreamingMode(0);
                    connection.setDoInput(true);
                    connection.setDoOutput(true);

                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
                    bufferedWriter.write("record="+record);
                    bufferedWriter.write("idGioco="+idGioco);
                    bufferedWriter.write("username="+ utenteBundle);

                    bufferedWriter.flush();

                    Scanner scanner = new Scanner(connection.getInputStream());
                    String response = scanner.nextLine();
                    switch (response) {
                        case " ":
                        case "":
                            break;
                        case "Error:00":
                            publishProgress("Errore nella creazione del record. Già esistente?");
                            break;
                        case "All done":
                            publishProgress("Salvataggio avvenuto con successo.");
                            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                            finish();
                            break;
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onProgressUpdate(Object... values) {
                Toast.makeText(getApplicationContext(), values[0].toString(), Toast.LENGTH_SHORT).show();
            }
        }.execute();
        /**
         * salvo il valore del record alla fine della giocata e lo salvo sul db
         */
        /*switch (materia){
            case ITALIANO:
                RecordPersonali.recorditaliano.add(record);
                break;
            case MATEMATICA:
                RecordPersonali.recordmatematica.add(record);
                break;
            case  INGLESE:
                RecordPersonali.recordinglese.add(record);
                break;
            case STORIA:
                RecordPersonali.recordstoria.add(record);
                break;
            case GEOGRAFIA:
                RecordPersonali.recordgeografia.add(record);
                break;
        }*/

    }
}
