package com.example.adi.playforlearnpfl.Giochi;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

/**
 * Created by Adi on 18/01/2017.
 */


public class Gioco_StoGeoIng extends AppCompatActivity{
    /**
     * E' un oggetto di tipo gioco che è specificato per una tipologia di gioco, in cui bisogna scegliere la risposta esatta tra 4 risposte.
     * @param soluzione opzione esatta
     * @param opzione1 prima opzione
     * @param opzione2 seconda opzione
     * @param opzione3 terza opzione
     * @param immagine immagine con il quesito
     * @param opzioni   array di opzioni che vengono mostrate mischiate tra loro.
     */
    public String soluzione,opzione1, opzione2, opzione3;
    public Integer immagine;
    public Gioco_StoGeoIng(int id, Integer immagine, String soluzione, String opzione1, String opzione2, String opzione3) {
        this.immagine= immagine;
        this.soluzione = soluzione;
        this.opzione1 = opzione1;
        this.opzione2 = opzione2;
        this.opzione3 = opzione3;
        ArrayList<String> opzioni = new ArrayList<String>(4);
        opzioni.add(soluzione);
        opzioni.add(opzione1);
        opzioni.add(opzione2);
        opzioni.add(opzione3);
    }

    public ArrayList<String> getOpzioni(ArrayList<String> opzioni) {
        return opzioni;
    }

    public void setSoluzione(String soluzione) {
        this.soluzione = soluzione;
    }

    public String getOpzione1() {
        return opzione1;
    }

    public void setOpzione1(String opzione1) {
        this.opzione1 = opzione1;
    }

    public String getOpzione2() {
        return opzione2;
    }

    public void setOpzione2(String opzione2) {
        this.opzione2 = opzione2;
    }

    public String getOpzione3() {
        return opzione3;
    }

    public void setOpzione3(String opzione3) {
        this.opzione3 = opzione3;
    }

    public String getSoluzione() {
        return soluzione;
    }

    public Integer getImmagine() {
        return immagine;
    }
}
