package com.example.adi.playforlearnpfl.Record;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.example.adi.playforlearnpfl.Alunno.HomeAlunno;
import com.example.adi.playforlearnpfl.R;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Created by Adi on 22/01/2017.
 */

public class RecordPersonali extends AppCompatActivity {

    private static Record record;
    private TextView tvRecordItaliano, tvRecordMatematica, tvRecordStoria, tvRecordGeografia, tvRecordInglese;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.record_personali);
        record = caricaRecordDaFile(getApplicationContext());
        tvRecordItaliano = (TextView)findViewById(R.id.tvRecordItaliano);
        tvRecordMatematica = (TextView)findViewById(R.id.tvRecordMatematica);
        tvRecordStoria = (TextView)findViewById(R.id.tvRecordStoria);
        tvRecordGeografia = (TextView)findViewById(R.id.tvRecordGeografia);
        tvRecordInglese = (TextView)findViewById(R.id.tvRecordInglese);
        aggiornaLayout();
    }

    private void aggiornaLayout(){
        tvRecordItaliano.setText(tvRecordItaliano.getText() + " " + record.getRecordItaliano());
        tvRecordGeografia.setText(tvRecordGeografia.getText() + " " + record.getRecordGeografia());
        tvRecordMatematica.setText(tvRecordMatematica.getText() + " " + record.getRecordMatematica());
        tvRecordStoria.setText(tvRecordStoria.getText() + " " + record.getRecordStoria());
        tvRecordInglese.setText(tvRecordInglese.getText() + " " + record.getRecordInglese());
    }

    public static void aggiornaRecord(String materia, int newRecord, Context context){
        if(record == null)
            record = caricaRecordDaFile(context);
        switch (materia.toUpperCase()){
            case HomeAlunno.ITALIANO:
                if(record.getRecordItaliano() < newRecord){
                    record.setRecordItaliano(newRecord);
                    salvaRecordSuFile(record, context);
                }
                break;
            case HomeAlunno.GEOGRAFIA:
                if(record.getRecordGeografia() < newRecord){
                    record.setRecordGeografia(newRecord);
                    salvaRecordSuFile(record, context);
                }
                break;
            case HomeAlunno.INGLESE:
                if(record.getRecordInglese() < newRecord){
                    record.setRecordInglese(newRecord);
                    salvaRecordSuFile(record, context);
                }
                break;
            case HomeAlunno.MATEMATICA:
                if(record.getRecordMatematica() < newRecord){
                    record.setRecordMatematica(newRecord);
                    salvaRecordSuFile(record, context);
                }
                break;
            case HomeAlunno.STORIA:
                if(record.getRecordStoria() < newRecord){
                    record.setRecordStoria(newRecord);
                    salvaRecordSuFile(record, context);
                }
                break;
        }
    }

    private static Record caricaRecordDaFile(Context context){
        File file = new File(context.getFilesDir(), "recordPersonali");
        if(!file.exists()){

            record = new Record();
            salvaRecordSuFile(record, context);
            return record;
        }

        try {
            FileInputStream fileInputStream =  new FileInputStream(file);
            ObjectInputStream fileObjectInputStream = new ObjectInputStream(fileInputStream);
            record = (Record)fileObjectInputStream.readObject();
            fileInputStream.close();
            Log.d("DEBUG", "[RecordPersonali] Ho salvato il record");
            return record;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void salvaRecordSuFile(Record record, Context context){
        File file = new File(context.getFilesDir(), "recordPersonali");

        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try{
            FileOutputStream fileOutputStream= new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(record);
            objectOutputStream.close();
            Log.d("DEBUG", "[RecordPersonali] Ho salvato il record");
        }catch (FileNotFoundException e){
            e.printStackTrace();
            Log.e("DEBUG","[RecordPersonali] Non ho trovato il file recordPersonali");
        }catch (IOException e) {
            e.printStackTrace();
            Log.e("DEBUG", "[RecordPersonali] IOException");
        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(),HomeAlunno.class));
        finish();
    }

    private static class Record implements Serializable{
        int recordItaliano, recordStoria, recordInglese, recordMatematica, recordGeografia;

        public Record(){
            recordItaliano = recordStoria = recordInglese = recordMatematica = recordGeografia = 0;
        }

        public Record(int recordItaliano, int recordStoria, int recordInglese, int recordMatematica, int recordGeografia) {
            this.recordItaliano = recordItaliano;
            this.recordStoria = recordStoria;
            this.recordInglese = recordInglese;
            this.recordMatematica = recordMatematica;
            this.recordGeografia = recordGeografia;
        }

        public int getRecordItaliano() {
            return recordItaliano;
        }

        public int getRecordStoria() {
            return recordStoria;
        }

        public int getRecordInglese() {
            return recordInglese;
        }

        public int getRecordMatematica() {
            return recordMatematica;
        }

        public int getRecordGeografia() {
            return recordGeografia;
        }

        public void setRecordItaliano(int recordItaliano) {
            this.recordItaliano = recordItaliano;
        }

        public void setRecordStoria(int recordStoria) {
            this.recordStoria = recordStoria;
        }

        public void setRecordInglese(int recordInglese) {
            this.recordInglese = recordInglese;
        }

        public void setRecordMatematica(int recordMatematica) {
            this.recordMatematica = recordMatematica;
        }

        public void setRecordGeografia(int recordGeografia) {
            this.recordGeografia = recordGeografia;
        }
    }
}