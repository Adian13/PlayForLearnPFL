/*
package com.example.adi.playforlearnpfl;

*/
/**
 * Created by Adi on 23/01/2017.
 *//*

        import android.net.wifi.WifiConfiguration;
        import android.os.AsyncTask;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.Toast;

        import org.json.JSONException;
        import org.json.JSONObject;

        import java.io.BufferedInputStream;
        import java.io.BufferedWriter;
        import java.io.IOException;
        import java.io.InputStream;
        import java.io.InputStreamReader;
        import java.io.OutputStreamWriter;
        import java.net.HttpURLConnection;
        import java.net.URL;
        import java.net.URLEncoder;
        import java.util.Scanner;
public class Connect extends AppCompatActivity {
    private Button btGetAllAlunni, btMakeQuery;
    private static String jsonResponse = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

      //  btGetAllAlunni = (Button)findViewById(R.id.btGetAllAlunni);
        //btMakeQuery = (Button)findViewById(R.id.btMakeQuery);

        btGetAllAlunni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this, "getAlunniPressed", Toast.LENGTH_SHORT).show();
                new AsyncTask<Object,Object,Object>(){
                    @Override
                    protected Object doInBackground(Object... params) {
                        try {

                            // TODO impostare l'ip del pc
                            String ip = "192.168.137.1";
                            // TODO impostare la porta (xamp usa la porta 80)
                            int porta = 80;
                            // TODO impostare il nome del file da chiamare
                            String nomeFile = "webservice/getAllAlunni.php";

                            URL url = new URL("http",ip, porta, nomeFile);
                            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                            Scanner scanner = new Scanner(connection.getInputStream());
                            jsonResponse = scanner.nextLine();
                            JSONObject jsonObject = new JSONObject(jsonResponse);
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        return null;
                    }
                }.execute();
            }
        });

        btMakeQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AsyncTask<Object,Object,Object>(){
                    @Override
                    protected Object doInBackground(Object... params) {
                        try {

                            // TODO impostare l'ip del pc
                            String ip = "192.168.137.1";
                            // TODO impostare la porta (xamp usa la porta 80)
                            int porta = 80;
                            // TODO impostare il nome del file da chiamare
                            String nomeFile = "webservice/makeQuery.php";

                            URL url = new URL("http", ip, porta,nomeFile);
                            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                            // Imposto il metodo POST
                            connection.setRequestMethod("POST");

                            // Abilito lettura e scrittura
                            connection.setDoInput(true);
                            connection.setDoOutput(true);

                            // Per scrivere i parametri POST
                            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
                            bufferedWriter.write("risposta=ciao");
                            bufferedWriter.flush();
                            bufferedWriter.close();

                            // Per leggere la risposta del php
                            Scanner scanner = new Scanner(connection.getInputStream());
                            jsonResponse = scanner.nextLine();

                            // Converte la stringa iin JSON
                            JSONObject jsonObject = new JSONObject(jsonResponse);
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        return null;
                    }
                }.execute();
            }
        });

    }
}
*/
