package com.example.adi.playforlearnpfl;

import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.adi.playforlearnpfl.Record.RecordTop10;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.example.adi.playforlearnpfl.R.id.notify;
import static com.example.adi.playforlearnpfl.R.id.txNome;

public class NotificaActivity extends AppCompatActivity {
    private static ArrayList<NotificaActivity.Notify> notifiche;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifica2);
        getSupportActionBar().setTitle("Notifiche");
        popola();
    }
    private void setTextRecord() {
        ListView ll = (ListView)findViewById(R.id.lvNotify);
        ll.setAdapter(new NotificheListAdapter(NotificaActivity.this, R.layout.lista_notifica_row,notifiche));
    }
    private void popola() {
        new AsyncTask<Object, Object, Object>() {

            @Override
            protected void onPreExecute() {
                notifiche = new ArrayList<Notify>();
            }

            @Override
            protected Object doInBackground(Object... params) {
                String nome;
                try {
                    String ip = LoginActivity.INDIRIZZO;
                    int porta = 80;
                    String nomeFile = "webservice/getUtentiNonAccettati.php";
                    URL url = new URL("http", ip, porta, nomeFile);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    Scanner scanner = new Scanner(connection.getInputStream());
                    String response = "";
                    JSONObject obj = null;
                    while (scanner.hasNext()) {
                        response = scanner.nextLine();
                        obj = new JSONObject(response);
                        nome = obj.getString("nome");
                        notifiche.add(new NotificaActivity.Notify(nome));
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
                setTextRecord();
            }
        }.execute();
    }
    private class NotificheListAdapter extends ArrayAdapter {
        public NotificheListAdapter(Context context, int resource, List objects) {
            super(context, resource, objects);
        }

        TextView tvNomeAlunno = null;
        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = getLayoutInflater().inflate(R.layout.lista_notifica_row,null);
            tvNomeAlunno = (TextView)view.findViewById(R.id.txNome);
            Button  btAccetta = (Button)view.findViewById(R.id.accetta);
            Button btRifiuta =(Button)view.findViewById(R.id.rifiuta);
            btAccetta.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    final String username= "username= "+ tvNomeAlunno.getText().toString();
                    new AsyncTask<Object, Object, Object>() {

                        @Override
                        protected void onPreExecute() {
                            notifiche = new ArrayList<NotificaActivity.Notify>();
                        }

                        @Override
                        protected Object doInBackground(Object... params) {
                            String nome;
                            try {
                                String ip = LoginActivity.INDIRIZZO;
                                int porta = 80;
                                String nomeFile = "webservice/accetta.php";
                                URL url = new URL("http", ip, porta, nomeFile);
                                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                                connection.setRequestMethod("POST");
                                connection.setChunkedStreamingMode(0);
                                connection.setDoInput(true);
                                connection.setDoOutput(true);

                                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));

                                bufferedWriter.write(username);
                                bufferedWriter.close();
                                Scanner scanner = new Scanner(connection.getInputStream());
                                String response = "";
                                JSONObject obj = null;
                                while (scanner.hasNext()) {
                                    response = scanner.nextLine();
                                    Log.d("DEBUG","response=" + response);
                                    /*obj = new JSONObject(response);
                                    nome = obj.getString("nome");
                                    notifiche.add(new NotificaActivity.Notify(nome));*/
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            } /*catch (JSONException e) {
                                e.printStackTrace();
                            }*/
                            return null;
                        }

                        @Override
                        protected void onPostExecute(Object o) {
                            setTextRecord();
                        }
                    }.execute();
                }
            });

            btRifiuta.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   final String username= "username= "+ tvNomeAlunno.getText().toString();
                    new AsyncTask<Object, Object, Object>() {

                        @Override
                        protected void onPreExecute() {
                            notifiche = new ArrayList<NotificaActivity.Notify>();
                        }

                        @Override
                        protected Object doInBackground(Object... params) {
                            String nome;
                            try {
                                String ip = LoginActivity.INDIRIZZO;
                                int porta = 80;
                                String nomeFile = "webservice/rifiuta.php";
                                URL url = new URL("http", ip, porta, nomeFile);
                                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                                connection.setRequestMethod("POST");
                                connection.setChunkedStreamingMode(0);
                                connection.setDoInput(true);
                                connection.setDoOutput(true);

                                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));

                                bufferedWriter.write(username);
                                bufferedWriter.close();
                                Scanner scanner = new Scanner(connection.getInputStream());
                                String response = "";
                                JSONObject obj = null;
                                while (scanner.hasNext()) {
                                    response = scanner.nextLine();
                                    obj = new JSONObject(response);
                                    nome = obj.getString("nome");
                                    notifiche.add(new NotificaActivity.Notify(nome));
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
                            setTextRecord();
                        }
                    }.execute();
                }
            });


            Notify notify  = (Notify)this.getItem(position);
            tvNomeAlunno.setText(notify.getNome());
            return view;
        }
    }
    private class Notify{
        String nomeUtente;
        public Notify(String nomeUtente) {
            this.nomeUtente = nomeUtente;

        }
        public String getNome() {
            return nomeUtente;
        }
    }

}
