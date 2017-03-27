/*
package com.example.adi.playforlearnpfl;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.adi.playforlearnpfl.Insegnante.HomeMaestra;

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

public class Notifica extends AppCompatActivity {
TextView tvNomeUtente;
    Button btAccetta, btRifiuta;
    ListView ll;
    String username;
    private static ArrayList<Notifica.Notify> notify;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
        setContentView(R.layout.content_notifica);
        getSupportActionBar().setTitle("Notifiche");
        popola();
    }

    private void setTextRecord() {
        ll = (ListView)findViewById(R.id.lvNotify);
        ll.setAdapter(new Notifica.NotifyListAdapter(getApplicationContext(),R.layout.lista_notifica_row,notify));
    }

    private void popola() {
        new AsyncTask<Object, Object, Object>() {

            @Override
            protected void onPreExecute() {
                notify = new ArrayList<Notifica.Notify>();
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
                        notify.add(new Notifica.Notify(nome));
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

    private class NotifyListAdapter extends ArrayAdapter {
        public NotifyListAdapter(Context context, int resource, List objects) {
            super(context, resource, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = getLayoutInflater().inflate(R.layout.lista_notifica_row, null);
            tvNomeUtente = (TextView)view.findViewById(R.id.txNome);
            btAccetta = (Button)findViewById(R.id.accetta);
            btRifiuta = (Button)findViewById(R.id.rifiuta);
            btAccetta.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    new AsyncTask<Object, Object, Object>() {

                        @Override
                        protected void onPreExecute() {
                            notify = new ArrayList<Notifica.Notify>();
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
                                    notify.add(new Notifica.Notify(nome));
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

            btRifiuta.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new AsyncTask<Object, Object, Object>() {

                        @Override
                        protected void onPreExecute() {
                            notify = new ArrayList<Notifica.Notify>();
                        }

                        @Override
                        protected Object doInBackground(Object... params) {
                            String nome;
                            try {
                                String ip = LoginActivity.INDIRIZZO;
                                int porta = 80;
                                String nomeFile = "webservice/rifiuto.php";
                                URL url = new URL("http", ip, porta, nomeFile);
                                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

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
                                    notify.add(new Notifica.Notify(nome));
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            publishProgress(null);
                            return null;
                        }

                        @Override
                        protected void onProgressUpdate(Object... values) {
                            ll.deferNotifyDataSetChanged();
                        }

                        @Override
                        protected void onPostExecute(Object o) {
                            setTextRecord();
                        }
                    }.execute();
                }
            });

            Notifica.Notify notify1  = (Notifica.Notify) this.getItem(position);
            tvNomeUtente.setText(notify1.getNomeUtente());
            return view;
        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(), HomeMaestra.class));
        finish();
    }

    private class Notify{
        String nomeUtente;
        public Notify(String nomeUtente) {
            this.nomeUtente = nomeUtente;
        }

        public String getNomeUtente() {
            return nomeUtente;
        }
    }


}
*/
