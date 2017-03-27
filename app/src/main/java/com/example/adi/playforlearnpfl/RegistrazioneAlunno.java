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
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class RegistrazioneAlunno extends AppCompatActivity {

    private TextInputEditText tietNome, tietCognome, tietUsername, tietPassword;
    private RadioButton rbPrima, rbSeconda, rbTerza, rbQuarta, rbQuinta;
    private RadioGroup radioGroup;
    private Button btRegistrati, btAnnulla;
    private String nome, cognome, username, password, classe, tipologia = "alunno";
    public static final String SharedPrefName = "Shared";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrazione_alunno);
        radioGroup = (RadioGroup)findViewById(R.id.radioGroup);

        tietNome = (TextInputEditText)findViewById(R.id.tietNome);
        tietCognome = (TextInputEditText)findViewById(R.id.tietCognome);
        tietUsername = (TextInputEditText)findViewById(R.id.tietUsername);
        tietPassword = (TextInputEditText)findViewById(R.id.tietPassword);
     //   tietClasse =(TextInputEditText)findViewById(R.id.tietClasse);

        btRegistrati = (Button)findViewById(R.id.btRegistrati);
        btRegistrati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


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
                try{
                    classe = ((RadioButton)findViewById(radioGroup.getCheckedRadioButtonId())).getText().toString();
                }catch (NullPointerException e){
                    Toast.makeText(RegistrazioneAlunno.this, "Bisogna selezionare almeno una classe", Toast.LENGTH_SHORT).show();
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
                                    "&password=" + password + "&tipologia=alunno&classe=" + classe;
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
                                case "Error:1":
                                    publishProgress("Errore nella creazione dell'alunno. Contattare l'admin");
                                    //  si dovrebbe eliminare l'utente
                                    break;
                                case "All done":
                                    publishProgress("Registrazione avvenuta con successo. Prego, effettuare il login.");
                                    startActivity(new Intent(RegistrazioneAlunno.this, LoginActivity.class));
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
                        Toast.makeText(RegistrazioneAlunno.this, values[0].toString(), Toast.LENGTH_SHORT).show();
                    }
                }.execute();
                SharedPreferences prefs = getApplicationContext().getSharedPreferences("Shared", 0);
                SharedPreferences.Editor prefsEditor = prefs.edit();
                prefsEditor.putString("username", RegistrazioneAlunno.this.tietUsername.getText().toString());
                prefsEditor.putString("nome", RegistrazioneAlunno.this.tietNome.getText().toString());
                prefsEditor.putString("cognome", RegistrazioneAlunno.this.tietCognome.getText().toString());
                Log.d("DEBUG_registrazione",(radioGroup.getCheckedRadioButtonId()+""));
                prefsEditor.putString("classe", radioGroup.getCheckedRadioButtonId()+"");
                prefsEditor.commit();

            }
        });

        btAnnulla = (Button)findViewById(R.id.btAnnulla);
        btAnnulla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegistrazioneAlunno.this, LoginActivity.class));
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        btAnnulla.performClick();
    }
}
