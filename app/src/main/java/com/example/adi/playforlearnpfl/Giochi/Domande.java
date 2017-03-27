package com.example.adi.playforlearnpfl.Giochi;
/**
 * Domande è una classe che contiene le domande del gioco Quiz_italiano. Ha vari metodi get e set che restituiscono la
 * domanda, le tre opzioni di scelta e la risposta sottoforma di stringhe.
 */
public class Domande {
    private int ID;
    private String domanda;
    private String OPTA;
    private String OPTB;
    private String OPTC;
    private String risposta;
    public Domande()
    {
        ID=0;
        domanda="";
        OPTA="";
        OPTB="";
        OPTC="";
        risposta="";
    }
    public Domande(String qUESTION, String oPTA, String oPTB, String oPTC,
                    String aNSWER) {

        domanda = qUESTION;
        OPTA = oPTA;
        OPTB = oPTB;
        OPTC = oPTC;
        risposta = aNSWER;
    }
    public int getID()
    {
        return ID;
    }
    public String getDomanda() {
        return domanda;
    }
    public String getOPTA() {
        return OPTA;
    }
    public String getOPTB() {
        return OPTB;
    }
    public String getOPTC() {
        return OPTC;
    }
    public String getRisposta() {
        return risposta;
    }
    public void setID(int id)
    {
        ID=id;
    }
    public void setDomanda(String qUESTION) {
        domanda = qUESTION;
    }
    public void setOPTA(String oPTA) {
        OPTA = oPTA;
    }
    public void setOPTB(String oPTB) {
        OPTB = oPTB;
    }
    public void setOPTC(String oPTC) {
        OPTC = oPTC;
    }
    public void setRisposta(String aNSWER) {
        risposta = aNSWER;
    }

}
