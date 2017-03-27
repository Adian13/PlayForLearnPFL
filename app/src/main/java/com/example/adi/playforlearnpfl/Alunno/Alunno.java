package com.example.adi.playforlearnpfl.Alunno;

import android.content.Intent;

import com.example.adi.playforlearnpfl.Utente;

import java.util.ArrayList;

/**
 * Created by Adi on 17/02/2017.
 */

public class Alunno extends Utente {
    /**
     * Crea un oggetto alunno con
     * @param id chiave identificativa dell'utente
     * @param nome nome dell'utente
     * @param cognome cognome dell'utente
     * @param password password dell'utente
     * @param username username dell'utente
     * @param record  array di record dell'utente, inizialmente vuoto
     * @return Alunno
     */
    String classe;
    ArrayList<Integer> record;

    public Alunno(String id, String nome, String cognome, String password, String username, ArrayList<Integer> record, String classe) {
        super(id, nome, cognome, password, username);
        this.record = record;
        this.classe = classe;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public ArrayList<Integer> getRecord() {
        return record;
    }

    public void setRecord(int recordValue , int i) {
        record.set(i,recordValue);
    }
}
