package com.example.adi.playforlearnpfl.Giochi;
/**
 * Quiz è una classe che contiene le domande inerenti a moltiplicazioni, divisioni , addizioni o sottrazioni che verranno
 * fatte agli utenti che giocano. Ha vari metodi get e set che restituiscono il testo, la risposta che è un boolean per dire
 * se la risposta è vera o falsa.
 */
public class Quiz {
    private String testo;
    private boolean risposta;
    private boolean counted;

    public Quiz(String str, boolean risposta) {
        testo = str;
        this.risposta = risposta;
        counted = false;
    }

    public String getTesto() {
        return testo;
    }

    public boolean getRisposta() {
        return risposta;
    }

    public void setRisposta(boolean risposta) {
        this.risposta = risposta;
    }
}

