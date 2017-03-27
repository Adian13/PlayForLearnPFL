package com.example.adi.playforlearnpfl;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioButton;


import com.example.adi.playforlearnpfl.Alunno.HomeAlunno;
import com.example.adi.playforlearnpfl.Giochi.FineGioco;
import com.example.adi.playforlearnpfl.Insegnante.HomeMaestra;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * ListaAlunni è una classe che rappresenta la lista degli alunni della classe "Prima" con i rispettivi record. La classe
 * viene gestita dal json che è un semplice formato per lo scambio di dati. Il json restituirà alla fine l'username e il
 * record del singolo utente.
 */


public class ListaAlunni extends AppCompatActivity {
    private static ArrayList<AlunnoInLista> alunni;
    public EditText edit;
    AlunnoInLista alunno;
    ArrayList<AlunnoInLista> alunniDaVisualizzare;
    AlunniInListaListAdapter customAdapter, cercati;
    AlertDialog.Builder miaAlert;
    private boolean trovato = false;
    ImageButton lente;
    String str = "", cognome, username, nome;
    FloatingActionButton floatingActionButtonSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_alunni);
        edit = (EditText) findViewById(R.id.edit);
        String str = edit.getText().toString();
        getSupportActionBar().setTitle("Lista Alunni");
        miaAlert = new AlertDialog.Builder(this);
        lente = (ImageButton) findViewById(R.id.lente);
        lente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ricerca(edit.getText().toString());
            }
        });
        popola();
    }

    public boolean ricerca(String str) {
        str = edit.getText().toString();
        int i = 0, k = 0;
        int j = str.length() + i;
        for (i = 0; i < str.length(); i++) {
            if (j < str.length()) {
                alunno = alunni.get(k);
                if (alunno.getNome().substring(i, j).compareToIgnoreCase(str) == 0) {
                    trovato = true;
                    alunniDaVisualizzare.add(alunni.get(i));
                    popola();
                } else trovato = false;
            }
            k++;
            j++;
            return trovato;
        }

        return trovato;
    }

    private void setTextLista() {
        ListView ll = (ListView) findViewById(R.id.lista);
        customAdapter = new AlunniInListaListAdapter(getApplicationContext(), R.layout.lista_row_alunni, alunni);
        ll.setAdapter(customAdapter);
        //Log.d("DEBUG_alunni", String.valueOf(alunni.size()));
    }


    private void popola() {
        new AsyncTask<Object, Object, Object>() {

            @Override
            protected void onPreExecute() {
                alunni = new ArrayList<AlunnoInLista>(10);
            }

            @Override
            protected Object doInBackground(Object... params) {
                String nome, record;
                try {
                    String ip = LoginActivity.INDIRIZZO;
                    int porta = 80;
                    String nomeFile = "webservice/getAlunniPerClasse.php";
                    URL url = new URL("http", ip, porta, nomeFile);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setDoInput(true);
                    connection.setDoOutput(true);
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
                    Log.d("DEBUG_list", getIntent().getStringExtra("classe"));
                    bufferedWriter.write("classe=" + getIntent().getStringExtra("classe"));
                    bufferedWriter.close();
                    Scanner scanner = new Scanner(connection.getInputStream());
                    String response = "";
                    JSONObject obj = null;
                    while (scanner.hasNext()) {
                        response = scanner.nextLine();
                        Log.d("DEBUG", response);
                        obj = new JSONObject(response);
                        Log.d("DEBUG_response", response);
                        nome = obj.getString("nome");
                        cognome = obj.getString("cognome");
                        username = obj.getString("username");
                        //record = obj.getString("record");
                        alunni.add(new AlunnoInLista(nome, cognome, username));
                        /* ,record*/

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                setTextLista();
            }
        }.execute();
    }

    public void onBackPressed() {
        //super.onBackPressed();
        Intent i = new Intent(getApplicationContext(), HomeMaestra.class);
        startActivity(i);
        finish();
    }

    /*View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(ListaAlunni.this);
            builder.setCancelable(true);
            builder.setMessage("Chi vuoi cercare?");
            builder.setView(R.layout.layout_search);
            final EditText edit = (EditText) findViewById(R.id.edit_name_search);
            final ImageButton btsrc= (ImageButton) findViewById(R.id.btsrc);
            builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ricerca(edit.getText().toString());

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
    };*/
    public class AlunnoInLista{
        String nome, cognome, username/*, record*/;
        private AlunnoInLista(String nome, String cognome, String username){
        /* String record*/
            this.nome= nome;
            this.cognome = cognome;
            this.username= username;
        //    this.record= record;
        }

      //  public String getRecord() {        return record;        }

      //  public void setRecord(String record) {            this.record = record;        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public String getCognome() {
            return cognome;
        }

        public void setCognome(String cognome) {
            this.cognome = cognome;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }
}