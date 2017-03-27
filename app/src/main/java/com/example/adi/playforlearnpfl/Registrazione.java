
package com.example.adi.playforlearnpfl;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.adi.playforlearnpfl.Alunno.HomeAlunno;
import com.example.adi.playforlearnpfl.Insegnante.HomeMaestra;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * Created by Adi on 17/02/2017.
 */

public class Registrazione extends AppCompatActivity {
    /* String tipologia;
     Utente utenteLoggato;
     RadioGroup radioGroup;
     RadioButton radioButton1, radioButton2,radioButton3,radioButton4,radioButton5;
     private TextInputEditText us,pw,name,surname;
     ArrayList<String> materie;
     CheckBox ch1, ch2, ch3, ch4, ch5, chItaliano, chinglese, chstoria, chgeografia, chmatematica;
     Button registra, annulla;
     private ArrayList<String> classi;
     private static String toSend;*/
     public static final String SharedPrefName = "Shared";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registrazione);
        Button alunno = (Button) findViewById(R.id.alunno);
        alunno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), RegistrazioneAlunno.class);
                i.putExtra("tipologia", "alunno");
                startActivity(i);
                finish();
            }
        });
        Button insegnante = (Button) findViewById(R.id.insegnante);
        insegnante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), RegistrazioneInsegnante.class);
                i.putExtra("tipologia", "insegnate");
                startActivity(i);
                finish();
            }
        });
    }
}

     /*  tipologia= getIntent().getStringExtra("tipologia");
        us=(TextInputEditText)findViewById(R.id.username);
        pw=(TextInputEditText)findViewById(R.id.password);
        name=(TextInputEditText)findViewById(R.id.nome);
        surname=(TextInputEditText)findViewById(R.id.cognome);
        annulla = (Button) findViewById(R.id.btAnnulla);
        annulla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent i =new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(i);
                    finish();
            }
        });
        utenteOInsegnante(tipologia);
        radioGroup= (RadioGroup)findViewById(R.id.radio);
        Log.d("DEBUG", radioGroup.toString());
        chItaliano= (CheckBox) findViewById(R.id.italiano);
       chmatematica= (CheckBox) findViewById(R.id.matematica);
        chinglese= (CheckBox) findViewById(R.id.inglese);
        chstoria= (CheckBox) findViewById(R.id.storia);
        chgeografia= (CheckBox) findViewById(R.id.geografia);
        ch1= (CheckBox) findViewById(R.id.ch1);
        ch2= (CheckBox) findViewById(R.id.ch2);
         ch3= (CheckBox) findViewById(R.id.ch3);
         ch4= (CheckBox) findViewById(R.id.ch4);
         ch5= (CheckBox) findViewById(R.id.ch5);
         radioButton1 = (RadioButton) findViewById(R.id.radioButton1);
         radioButton2 = (RadioButton) findViewById(R.id.radioButton2);
         radioButton3 = (RadioButton) findViewById(R.id.radioButton3);
         radioButton4 = (RadioButton) findViewById(R.id.radioButton4);
         radioButton5 = (RadioButton) findViewById(R.id.radioButton5);
        registra = (Button) findViewById(R.id.btOK);
        registra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(tipologia){
                    case "alunno":
                        if (!send(creaStringaJSAlunno())) {
                            Toast.makeText(getApplicationContext(), "Operazione non riuscita", Toast.LENGTH_LONG).show();
                        }else{
                            SharedPreferences prefs = Registrazione.this.getSharedPreferences("Shared", 0);
                            SharedPreferences.Editor prefsEditor = prefs.edit();
                            prefsEditor.putString("username", Registrazione.this.us.getText().toString());
                            prefsEditor.putString("password", Registrazione.this.pw.getText().toString());
                            prefsEditor.putString("nome", Registrazione.this.name.getText().toString());
                            prefsEditor.putString("cognome", Registrazione.this.surname.getText().toString());
                            prefsEditor.commit();
                        }
                        break;
                    case "insegnante":
                        if(!send(creaStringaJSInsegnante())) {
                            Toast.makeText(getApplicationContext(), "Operazione non riuscita", Toast.LENGTH_LONG).show();
                        }else{ SharedPreferences prefs = Registrazione.this.getSharedPreferences("Shared", 0);
                            SharedPreferences.Editor prefsEditor = prefs.edit();
                            prefsEditor.putString("username", Registrazione.this.us.getText().toString());
                            prefsEditor.putString("password", Registrazione.this.pw.getText().toString());
                            prefsEditor.putString("nome", Registrazione.this.name.getText().toString());
                            prefsEditor.putString("cognome", Registrazione.this.surname.getText().toString());
                            prefsEditor.commit();
                        }
                            break;
                    default:
                        Toast.makeText(getApplicationContext(), "caso estremo", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    public void utenteOInsegnante(String tipologia){
        if(tipologia.compareToIgnoreCase("alunno")==0) {
            LinearLayout linear_insegnante = (LinearLayout) findViewById(R.id.linear_insegnante);
            linear_insegnante.setVisibility(View.INVISIBLE);

        }else if(tipologia.compareToIgnoreCase("insegnante")==0){
                LinearLayout linear_alunno=(LinearLayout)findViewById(R.id.linear_utente);
                linear_alunno.setVisibility(View.INVISIBLE);

        }
    }

    public String creaStringaJSAlunno(){
        //LinearLayout linear_alunno = (LinearLayout)findViewById(R.id.linear_utente);

        Log.d("DEBUG", "id radio button: "+ radioButton1);

        toSend = "username = "+us.getText().toString()+"|"+
                "password = "+pw.getText().toString()+"|"+
                "nome = "+ name.getText().toString()+"|"+
                "cognome = "+ surname.getText().toString()+"|classe = ";


        int rd = radioGroup.getCheckedRadioButtonId();
        int childCount = radioGroup.getChildCount();
        RadioButton rbTemp;
        boolean find = false;
        for(int i = 0; i < childCount; i++){
            if((rbTemp = (RadioButton)radioGroup.getChildAt(i)).getId() == rd){
                toSend += rbTemp.getText();
                find = true;
            }
        }

        if(!find){
            toSend += ((RadioButton)radioGroup.getChildAt(0)).getText();
        }

        return toSend;
    }
    public String creaStringaJSInsegnante(){
        LinearLayout linear_insegnante = (LinearLayout)findViewById(R.id.linear_insegnante);

        if(ch1.isChecked()) classi.add(ch1.getText().toString());
        if(ch2.isChecked()) classi.add(ch2.getText().toString());
        if(ch3.isChecked()) classi.add(ch3.getText().toString());
        if(ch4.isChecked()) classi.add(ch4.getText().toString());
        if(ch5.isChecked()) classi.add(ch5.getText().toString());


        if(chItaliano.isChecked()) materie.add(chItaliano.getText().toString());
        if(chmatematica.isChecked()) materie.add(chmatematica.getText().toString());
        if(chinglese.isChecked()) materie.add(chinglese.getText().toString());
        if(chstoria.isChecked()) materie.add(chstoria.getText().toString());
        if(chgeografia.isChecked()) materie.add(chgeografia.getText().toString());

        toSend = "username = "+us.getText().toString()+"|"+
                "password = "+pw.getText().toString()+"|"+
                "nome = "+ name.getText().toString()+"|"+
                "cognome = "+ surname.getText().toString()+"|"+
                "materia = "+materie.toString()+"|"+
                "classe = "+classi.toString()+" ";
        return toSend;
    }
    private void loginSuccess(){
        if(tipologia.compareTo("alunno")==0) {
            Intent intent = new Intent(getApplicationContext(), HomeAlunno.class);
            intent.putExtra("utenteLoggato", utenteLoggato);
            startActivity(intent);
            finish();
        }else if(tipologia.compareTo("insegnante")==0){
            Intent intent = new Intent(getApplicationContext(), HomeMaestra.class);
            intent.putExtra("utenteLoggato", utenteLoggato);
            startActivity(intent);
            finish();
        }
    }

    private void loginFailed(){
        //Mettere qualcosa di più sfizioso
        Toast.makeText(this, "Credenziali non valide", Toast.LENGTH_SHORT).show();
    }
    public boolean send(final String toSend){
        new AsyncTask<Object,Boolean,Object>(){

            @Override
            protected void onProgressUpdate(Boolean... values) {
                if(values[0]){
                    loginSuccess();
                }else{
                    loginFailed();
                }
            }


  Scanner scanner = new Scanner(connection.getInputStream());
                    String line = scanner.nextLine();
                    if(line.length() < 2){
                        publishProgress(false);
                        this.cancel(true);
                    }else{
                        JSONArray jsonObject = new JSONArray(line);
                          18/02/2017 Attenzione ci sono dei campi che non ha utente quindi si dovrebbe modificare o la query o il db
                        non lo so fare
                        // la stringa è formattata nel seguente modo: username, nome, cognome, password
                       // utenteLoggato = new Utente("",jsonObject.getString(1),jsonObject.getString(2),jsonObject.getString(3), jsonObject.getString(0));
                        publishProgress(true);
                    }
                    *//*

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute();
        return false;
    }

}
*/
