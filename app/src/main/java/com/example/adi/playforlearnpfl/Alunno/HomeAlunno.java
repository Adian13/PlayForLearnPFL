package com.example.adi.playforlearnpfl.Alunno;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.adi.playforlearnpfl.Games;
import com.example.adi.playforlearnpfl.LoginActivity;
import com.example.adi.playforlearnpfl.R;
import com.example.adi.playforlearnpfl.Record.RecordPersonali;
import com.example.adi.playforlearnpfl.Record.RecordTop10;


//import static com.example.adi.playforlearn10.Creatore.giochiDaLanciare;

public class HomeAlunno extends AppCompatActivity {
    //  credo che queste variabili debbano stare in un'altra classe
    public static final String PRIMA = "PRIMA";
    public static final String ITALIANO = "ITALIANO";
    public static final String STORIA = "STORIA";
    public static final String GEOGRAFIA = "GEOGRAFIA";
    public static final String INGLESE = "INGLESE";
    public static final String MATEMATICA = "MATEMATICA";
    public static final String MATERIA = "MATERIA";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_alunno);
        getSupportActionBar().setTitle("Home alunno");

        final Bundle utenteBundle =getIntent().getBundleExtra("utente");

        // Bind buttons
        ImageButton account = (ImageButton) findViewById(R.id.btAccount);
        Button btItaliano= (Button)findViewById(R.id.btItaliano);
        Button btStoria =(Button)findViewById(R.id.btStoria);
        Button btMatematica = (Button)findViewById(R.id.btMatematica);
        Button btGeografia= (Button)findViewById(R.id.btGeografia);
        Button btInglese= (Button)findViewById(R.id.btInglese);

        // Bind image buttons
        ImageButton btMyRecord =(ImageButton) findViewById(R.id.myRecord);
        ImageButton btTop10 = (ImageButton) findViewById(R.id.btTop10);

        btTop10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), RecordTop10.class);
                i.putExtra("utente", utenteBundle);
                startActivity(i);
                finish();
            }
        });

        btMyRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), RecordPersonali.class);
                i.putExtra("utente", utenteBundle);
                startActivity(i);
                finish();
            }
        });

        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), AccountAlunno.class);
                i.putExtra("utente", utenteBundle);
                startActivity(i);
                finish();
            }
        });

        OnMateriaButtonClick onMateriaButtonClick = new OnMateriaButtonClick();
        btItaliano.setOnClickListener(onMateriaButtonClick);
        btStoria.setOnClickListener(onMateriaButtonClick);
        btMatematica.setOnClickListener(onMateriaButtonClick);
        btGeografia.setOnClickListener(onMateriaButtonClick);
        btInglese.setOnClickListener(onMateriaButtonClick);

    }
    /*public ArrayList<Gioco> caricaGioco(Button bt) {
        if (bt.getText().equals(ITALIANO)) {
            giochiDaLanciare.addAll(Creatore.giochiItaliano);
        } else if (bt.getText().equals(STORIA)) {
            giochiDaLanciare.addAll(Creatore.giochiStoria);
        } else if (bt.getText().equals(MATEMATICA)) {
            giochiDaLanciare.addAll(Creatore.giochiMatematica);
        } else if (bt.getText().equals(GEOGRAFIA)) {
            giochiDaLanciare.addAll(Creatore.giochiGeografia);
        } else if (bt.getText().equals(INGLESE)) {
            giochiDaLanciare.addAll(Creatore.giochiInglese);
        }
        return  giochiDaLanciare;
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        Bundle utenteBundle= getIntent().getBundleExtra("utente");
        int id=item.getItemId();
        switch(id)
        {
            case R.id.info:
                Intent i= new Intent(getApplicationContext(), HomeAlunno.class);
                i.putExtra("utente", utenteBundle);
                startActivity(i);
                finish();
            /*
                Codice di gestione della voce MENU_1
             */
                break;
            case R.id.impostazioni:
            /*
                Codice di gestione della voce MENU_2
             */
        }
        return false;
    }
    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(HomeAlunno.this);
        builder.setCancelable(true);
        builder.setMessage("Uscire?");
        builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    /**
     * Listener per i pulsanti delle materie
     */
    private class OnMateriaButtonClick implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Bundle utenteBundle= getIntent().getBundleExtra("utente");
            Intent i = new Intent(getApplicationContext(),Games.class);
            String materia = ((Button)v).getText().toString();
            materia = materia.toUpperCase();
            i.putExtra(MATERIA,materia);
            i.putExtra("utente", utenteBundle);
            startActivity(i);
            finish();
        }
    }
}
