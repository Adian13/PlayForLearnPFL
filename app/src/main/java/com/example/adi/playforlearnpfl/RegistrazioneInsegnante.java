package com.example.adi.playforlearnpfl;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class RegistrazioneInsegnante extends AppCompatActivity {

    private Button btRegistrati, btAnnulla;
    private CheckBox cbMateriaItaliano, cbMateriaMatematica, cbMateriaInglese, cbMateriaStoria, cbMateriaGeografia;
    private CheckBox cbPrima, cbSeconda, cbTerza, cbQuarta, cbQuinta;
    private TextInputEditText tietNome, tietCognome, tietUsername, tietPassword;
    private String nome, cognome, username, password;
    private ArrayList<String> classi, materie;
    public static final String SharedPrefName = "Shared";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrazione_insegnante);
        getSupportActionBar().setTitle("Registrazione insegnante");

        cbPrima = (CheckBox)findViewById(R.id.cbPrima);
        cbSeconda = (CheckBox)findViewById(R.id.cbSeconda);
        cbTerza = (CheckBox)findViewById(R.id.cbTerza);
        cbQuarta = (CheckBox)findViewById(R.id.cbQuarta);
        cbQuinta = (CheckBox)findViewById(R.id.cbQuinta);

        cbMateriaItaliano = (CheckBox)findViewById(R.id.cbMateriaItaliano);
        cbMateriaMatematica = (CheckBox)findViewById(R.id.cbMateriaMatematica);
        cbMateriaInglese = (CheckBox)findViewById(R.id.cbMateriaInglese);
        cbMateriaStoria = (CheckBox)findViewById(R.id.cbMateriaStoria);
        cbMateriaGeografia = (CheckBox)findViewById(R.id.cbMateriaGeografia);

        tietNome = (TextInputEditText)findViewById(R.id.tietNome);
        tietCognome = (TextInputEditText)findViewById(R.id.tietCognome);
        tietUsername = (TextInputEditText)findViewById(R.id.tietUsername);
        tietPassword = (TextInputEditText)findViewById(R.id.tietPassword);

        classi = new ArrayList<>(6);
        materie = new ArrayList<>(6);

        btAnnulla = (Button)findViewById(R.id.btAnnulla);
        btAnnulla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegistrazioneInsegnante.this, LoginActivity.class));
                finish();
            }
        });

        btRegistrati = (Button)findViewById(R.id.btRegistrati);
        btRegistrati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Controllo i campi
                tietNome.setError(null);
                tietCognome.setError(null);
                tietUsername.setError(null);
                tietPassword.setError(null);

                if(tietNome.length() != 0){
                    nome = tietNome.getText().toString();
                    if(nome.trim().length() == 0){
                        tietNome.setError("Non può essere vuoto");
                        return;
                    }
                }else{
                    tietNome.setError("Non può essere vuoto");
                    return;
                }

                if(tietCognome.length() != 0){
                    cognome = tietCognome.getText().toString();
                    if(cognome.trim().length() == 0){
                        tietCognome.setError("Non può essere vuoto");
                        return;
                    }
                }else{
                    tietCognome.setError("Non può essere vuoto");
                    return;
                }
                if(tietUsername.length() != 0){
                    username = tietUsername.getText().toString();
                    if(username.trim().length() == 0){
                        tietUsername.setError("Non può essere vuoto");
                        return;
                    }
                }else{
                    tietUsername.setError("Non può essere vuoto");
                    return;
                }
                if(tietPassword.length() != 0){
                    password = tietPassword.getText().toString();
                    if(password.trim().length() == 0){
                        tietPassword.setError("Non può essere vuoto");
                        return;
                    }
                }else{
                    tietPassword.setError("Non può essere vuoto");
                    return;
                }

                if(cbMateriaItaliano.isChecked())
                    materie.add("Italiano");
                if(cbMateriaMatematica.isChecked())
                    materie.add("Matematica");
                if(cbMateriaInglese.isChecked())
                    materie.add("Inglese");
                if(cbMateriaStoria.isChecked())
                    materie.add("Storia");
                if(cbMateriaGeografia.isChecked())
                    materie.add("Geografia");
                if(materie.isEmpty()){
                    Toast.makeText(RegistrazioneInsegnante.this, "Bisogna selezionare almeno una materia.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(cbPrima.isChecked())
                    classi.add("Prima");
                if(cbSeconda.isChecked())
                    classi.add("Seconda");
                if(cbTerza.isChecked())
                    classi.add("Terza");
                if(cbQuarta.isChecked())
                    classi.add("Quarta");
                if(cbQuinta.isChecked())
                    classi.add("Quinta");
                if(classi.isEmpty()){
                    Toast.makeText(RegistrazioneInsegnante.this, "Bisogna selezionare almeno una classe.", Toast.LENGTH_SHORT).show();
                    return;
                }

                new AsyncTask<Object,Object,Object>(){
                    @Override
                    protected Object doInBackground(Object... params) {
                        try {
                            String ip = LoginActivity.INDIRIZZO;
                            int porta = 80;
                            String nomeFile = "webservice/registrazioneUtente.php";
                            URL url = new URL("http",ip, porta, nomeFile);
                            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                            connection.setRequestMethod("POST");
                            connection.setChunkedStreamingMode(0);
                            connection.setDoInput(true);
                            connection.setDoOutput(true);

                            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
                            String toSend = "nome=" + nome + "&cognome=" + cognome + "&username=" + username +
                                    "&password=" + password + "&tipologia=insegnante&classi=";

                            for(String classe : classi){
                                toSend += classe;
                                if(classi.indexOf(classe) != classi.size()-1)
                                    toSend += "|";
                            }

                            toSend+="&materie=";
                            for(String materia : materie){
                                toSend += materia;
                                if(materie.indexOf(materia) != materie.size()-1)
                                    toSend+="|";
                            }

                            bufferedWriter.write(toSend);
                            bufferedWriter.flush();

                            Scanner scanner = new Scanner(connection.getInputStream());
                            String response = scanner.nextLine();
                            switch(response){
                                case " ":
                                case "":
                                    break;
                                case "Error:0":
                                    publishProgress("Errore nella creazione dell'utente. Già esistente?");
                                    break;
                                case "Error:2":
                                    publishProgress("Errore nella creazione dell'insegnante. Contattare l'admin");
                                    //  si dovrebbe eliminare l'utente
                                    break;
                                case "All done":
                                    publishProgress("Registrazione avvenuta con successo. Prego, effettuare il login.");
                                    startActivity(new Intent(RegistrazioneInsegnante.this, LoginActivity.class));
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
                        Toast.makeText(RegistrazioneInsegnante.this, values[0].toString(), Toast.LENGTH_SHORT).show();
                    }
                }.execute();
                SharedPreferences prefs = RegistrazioneInsegnante.this.getSharedPreferences("Shared", 0);
                SharedPreferences.Editor prefsEditor = prefs.edit();
                prefsEditor.putString("username", RegistrazioneInsegnante.this.tietUsername.getText().toString());
                prefsEditor.putString("nome", RegistrazioneInsegnante.this.tietNome.getText().toString());
                prefsEditor.putString("cognome", RegistrazioneInsegnante.this.tietCognome.getText().toString());
                prefsEditor.commit();
            }
        });
    }

    @Override
    public void onBackPressed() {
        btAnnulla.performClick();
    }
}
