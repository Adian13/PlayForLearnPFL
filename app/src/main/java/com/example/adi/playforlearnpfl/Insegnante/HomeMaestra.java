package com.example.adi.playforlearnpfl.Insegnante;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.example.adi.playforlearnpfl.Alunno.HomeAlunno;
import com.example.adi.playforlearnpfl.Insegnante.AccountMaestra;
import com.example.adi.playforlearnpfl.LoginActivity;
import com.example.adi.playforlearnpfl.NotificaActivity;
import com.example.adi.playforlearnpfl.R;
import com.example.adi.playforlearnpfl.Record.RecordInsegnante;
import com.example.adi.playforlearnpfl.Record.RecordTop10;
import com.example.adi.playforlearnpfl.SezioneClassi;

/**HomeMaestra è la classe che si interfaccia con l'intero sistema PlayForLearn. Permette la visualizzazione delle varie materie
 * di un insegnante e un pulsante per la visualizzazione della top10 degli alunni.
 */

public class HomeMaestra extends AppCompatActivity {

    ImageButton account, notifica, top10, classi;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_maestra);
        account = (ImageButton)findViewById(R.id.account);
        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getApplicationContext(),AccountMaestra.class);
                startActivity(i);
                finish();
            }
        });
        getSupportActionBar().setTitle("Home Maestra");
        notifica= (ImageButton)findViewById(R.id.notify);
        notifica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), NotificaActivity.class);
                startActivity(i);
                finish();
            }
        });
        top10=(ImageButton)findViewById(R.id.top10);
        top10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), RecordInsegnante.class);
                startActivity(i);
                finish();
            }
        });
        classi= (ImageButton)findViewById(R.id.classe);
        classi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),SezioneClassi.class);
                startActivity(i);
                finish();
            }
        });
    }


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
        int id=item.getItemId();
        switch(id)
        {
            case R.id.info:
                Intent i= new Intent(getApplicationContext(), HomeAlunno.class);
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
   /* public void italiano(View v){
        Intent i = new Intent(getApplicationContext(),SezioneClassi.class);
        startActivity(i);
        finish();
    }

    public void matematica(View v){
        Intent i = new Intent(getApplicationContext(),SezioneClassi.class);
        startActivity(i);
        finish();
    }

    public void inglese(View v){
        Intent i = new Intent(getApplicationContext(),SezioneClassi.class);
        startActivity(i);
        finish();
    }

    public void storia(View v){
        Intent i = new Intent(getApplicationContext(),SezioneClassi.class);
        startActivity(i);
        finish();
    }

    public void geografia(View v){
        Intent i = new Intent(getApplicationContext(),SezioneClassi.class);
        startActivity(i);
        finish();
    }

    public void account(View v){
        Intent i = new Intent(getApplicationContext(),  AccountMaestra.class);
        startActivity(i);
        finish();
    }

    public void top10(View v){
        Intent i = new Intent(getApplicationContext(), RecordInsegnante.class);

        startActivity(i);
        finish();
    }*/

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        AlertDialog.Builder builder = new AlertDialog.Builder(HomeMaestra.this);
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
}
