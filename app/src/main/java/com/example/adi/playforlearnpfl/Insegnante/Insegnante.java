package com.example.adi.playforlearnpfl.Insegnante;

import com.example.adi.playforlearnpfl.Utente;

import java.util.ArrayList;

/**
 * Created by Adi on 17/02/2017.
 * Rappresenta la tabella Insegnante del database
 */

public class Insegnante extends Utente {
    ArrayList<String> materia, classe;
    /**
     * Costruttore di un oggetto insegnate
     * @param id chiave identificativa dell'utente insegnante
     * @param nome nome dell'utente insegnante
     * @param cognome cognome dell'utente insegnante
     * @param password password dell'utente insegnante
     * @param username username dell'utente insegnante
     * @param materia array di materie di cui si occupa l'insegnate
     * @param classe classi di cui si occupa l'insegnante
     */
    public Insegnante(String id, String nome, String cognome, String password, String username, ArrayList<String> materia, ArrayList<String>classe) {
        super(id, nome, cognome, password, username);
        this.materia = materia;
        this.classe= classe;
    }

    public ArrayList<String> getMateria() {
        return materia;
    }

    public void setMateria(ArrayList<String> materia) {
        this.materia = materia;
    }

    public ArrayList<String> getClasse() {
        return classe;
    }

    public void setClasse(ArrayList<String> classe) {
        this.classe = classe;
    }
}