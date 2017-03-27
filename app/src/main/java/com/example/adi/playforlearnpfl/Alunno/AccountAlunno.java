package com.example.adi.playforlearnpfl.Alunno;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.adi.playforlearnpfl.LoginActivity;
import com.example.adi.playforlearnpfl.R;
import com.example.adi.playforlearnpfl.RegistrazioneInsegnante;


public class AccountAlunno extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account);
        TextView txNome = (TextView)findViewById(R.id.textNome);
        TextView txcognome = (TextView)findViewById(R.id.textCognome);
        TextView txUsername = (TextView)findViewById(R.id.textUsername);
        TextView txClasse = (TextView)findViewById(R.id.textClasse);
       ImageButton btLogout= (ImageButton)findViewById(R.id.btLogout);
        btLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(i);
                finish();
            }
        });
        SharedPreferences prefs = getSharedPreferences(RegistrazioneInsegnante.SharedPrefName, 0);
        txUsername.setText(prefs.getString("username", "username non trovato"));
        txNome.setText(prefs.getString("nome", "nome non trovato"));
        txcognome.setText(prefs.getString("cognome", "cognome non trovato"));
        txClasse.setText(prefs.getString("classe", "classe non trovata"));

        getSupportActionBar().setTitle("Account Alunno");


    }
}
