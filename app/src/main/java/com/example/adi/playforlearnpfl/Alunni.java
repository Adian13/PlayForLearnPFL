package com.example.adi.playforlearnpfl;

/**
 * Oggetto che rappresenta gli elementi della lista usata per la top10, contiene informazioni prese dal db che non devono essere memorizzate.
 */

public class Alunni {
    String nome, punteggio;

    public Alunni(String nome, String punteggio) {
        this.nome = nome;
        this.punteggio = punteggio;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public String getPunteggio() {
        return punteggio;
    }
}
