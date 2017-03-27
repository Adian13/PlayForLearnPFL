package com.example.adi.playforlearnpfl.Giochi;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * DbHelper è una classe che effettua la creazione di un database contenenete 20 domande, che verranno estratte
 * in maniera random. Queste domande verranno gestite da un arraylist di Domande e verranno visualizzare in maniera random
 * 5 di esse.
 */
public class DbHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "Alfabeto";

    private static final String QUEST = "quest";
    private static final String KEY_ID = "id";
    private static final String KEY_QUES = "question";
    private static final String KEY_ANSWER = "answer";
    private static final String KEY_OPTA= "opta";
    private static final String KEY_OPTB= "optb";
    private static final String KEY_OPTC= "optc";
    private SQLiteDatabase dbase;


    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override

    public void onCreate(SQLiteDatabase db) {
        dbase=db;
        String sql = "CREATE TABLE IF NOT EXISTS " + QUEST + " ( "
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_QUES
                + " TEXT, " + KEY_ANSWER+ " TEXT, "+KEY_OPTA +" TEXT, "
                +KEY_OPTB +" TEXT, "+KEY_OPTC+" TEXT)";
        db.execSQL(sql);
        aggiungiDomanda();

    }
    private void aggiungiDomanda()
    {
        Domande q1=new Domande("Inserisci la lettera fra A...C","D", "A", "B", "B");
        this.addQuestion(q1);
        Domande q2=new Domande("Inserisci la lettera fra K...M","O", "L", "J", "L");
        this.addQuestion(q2);
        Domande q3=new Domande("Inserisci la lettera fra X...Z","Y", "W", "A", "Y");
        this.addQuestion(q3);
        Domande q4=new Domande("Inserisci la lettera fra J...L","Y", "K", "F", "K");
        this.addQuestion(q4);
        Domande q5=new Domande("Inserisci la lettera fra B...D","D", "E", "C", "C");
        this.addQuestion(q5);
        Domande q6=new Domande("Inserisci la lettera fra H...J","F", "I", "L", "I");
        this.addQuestion(q6);
        Domande q7=new Domande("Inserisci la lettera fra O...Q","S", "R", "P", "P");
        this.addQuestion(q7);
        Domande q8=new Domande("Inserisci la lettera fra T...V","U", "J", "W", "U");
        this.addQuestion(q8);
        Domande q9=new Domande("Inserisci la lettera fra Z...B","Z", "A", "B", "A");
        this.addQuestion(q9);
        Domande q10=new Domande("Inserisci la lettera fra D...F","D", "E", "G", "E");
        this.addQuestion(q10);
    }
    @Override
    /**
     *
     * Metodo onUpgrade permette l'aggiornamento del database, prendendo in input il database e due interi.
     * @param db oggetto di tipo database al quale si fa accesso per ottenere le domande del gioco.
     * @param oldV vecchia versione del database
     * @param newV nuova versione del database con aggiornamenti.
     */
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
        db.execSQL("DROP TABLE IF EXISTS " + QUEST);
        onCreate(db);
    }

    private void addQuestion(Domande quest) {

        ContentValues values = new ContentValues();
        values.put(KEY_QUES, quest.getDomanda());
        values.put(KEY_ANSWER, quest.getRisposta());
        values.put(KEY_OPTA, quest.getOPTA());
        values.put(KEY_OPTB, quest.getOPTB());
        values.put(KEY_OPTC, quest.getOPTC());

        dbase.insert(QUEST, null, values);
    }

    /**
     * Il metodo getAllQuestions fa un interrogazione al database attraverso una query, selezionando tutti i campi delle varie
     * domande. Infine attraverso l'interfaccia cursor, permette l'accesso ai dati SQL riga per riga. Attraverso i metodi moveToFirst
     * e moveToNext() è possibile ottenere gli altri dati.
     *
     * @return lista : rappresenta la lista di domande memorizzate in un arrayList di Domande.
     */
    public List<Domande> getAllQuestions() {
        List<Domande> lista = new ArrayList<Domande>();

        String selectQuery = "SELECT  * FROM QUEST ORDER BY RANDOM()" ;
        dbase=this.getReadableDatabase();
        Cursor cursor = dbase.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Domande quest = new Domande();
                quest.setID(cursor.getInt(0));
                quest.setDomanda(cursor.getString(1));
                quest.setRisposta(cursor.getString(2));
                quest.setOPTA(cursor.getString(3));
                quest.setOPTB(cursor.getString(4));
                quest.setOPTC(cursor.getString(5));
                lista.add(quest);
            } while (cursor.moveToNext());
        }

        return lista;
    }
}