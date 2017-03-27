package com.example.adi.playforlearnpfl;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Adi on 14/03/2017.
 */

public class AlunniInListaListAdapter extends ArrayAdapter<ListaAlunni.AlunnoInLista> {
        Context context;
    private LayoutInflater inflater;
    public AlunniInListaListAdapter(Context context, int resource, List<ListaAlunni.AlunnoInLista> objects) {
        super(context, resource, objects);
        this.context = context;
        // this.resource=resource;
        inflater = LayoutInflater.from(context);
    }

        public View getView(int position, View v, ViewGroup parent) {

            v = inflater.inflate(R.layout.lista_row_alunni, null);
            ListaAlunni.AlunnoInLista contatto = getItem(position);
            TextView tvNomeAlunno = (TextView) v.findViewById(R.id.tvNomeAlunno);
            TextView tvCognomeAlunno = (TextView) v.findViewById(R.id.tvCognomeAlunno);
            TextView tvUsernameAlunno = (TextView) v.findViewById(R.id.tvUsernameAlunno);
           // TextView tvRecordAlunno = (TextView) v.findViewById(R.id.tvRecordAlunno);
            tvNomeAlunno.setText(contatto.getNome());
            tvCognomeAlunno.setText(contatto.getCognome());
            tvUsernameAlunno.setText(contatto.getUsername());
          //  tvRecordAlunno.setText(contatto.getRecord());

            return v;
        }
    }
