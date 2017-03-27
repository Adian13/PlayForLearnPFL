package com.example.adi.playforlearnpfl;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by raffaeledellaporta on 11/02/2017.
 */

public class AlunniListAdapter extends ArrayAdapter<Alunni> {
    Context context;
    private LayoutInflater inflater;
// private int resource;


    public AlunniListAdapter(Context context, int resource, List<Alunni> objects) {
        super(context, resource, objects);
        this.context = context;
        // this.resource=resource;
        inflater = LayoutInflater.from(context);
    }

    public View getView(int position, View v, ViewGroup parent) {

        v = inflater.inflate(R.layout.lista_row, null);
        Alunni contatto = getItem(position);
        TextView tvUsernameAlunno = (TextView) v.findViewById(R.id.tvNomeAlunno);
        TextView tvRecord = (TextView) v.findViewById(R.id.tvRecord);
       // ListaAlunni.Alunni al = (ListaAlunni.Alunni) this.getItem(position);
        tvUsernameAlunno.setText(contatto.getNome());
        tvRecord.setText(contatto.getPunteggio());
        return v;
    }
}