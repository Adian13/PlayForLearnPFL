package com.example.adi.playforlearnpfl;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.adi.playforlearnpfl.Giochi.FineGioco;

import java.util.List;
public class QuizActivity extends Activity {
    List<Domande> lista;
    int score=0;
    int qid=0;
    Domande currentQ;
    TextView txtQuestion,iniziaGioco;
    RadioButton rda, rdb, rdc;
    Button butNext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        DbHelper db=new DbHelper(this);
        lista=db.getAllQuestions();
        currentQ=lista.get(qid);
        txtQuestion=(TextView)findViewById(R.id.textView1);
        iniziaGioco=(TextView)findViewById(R.id.Inizia);
        rda=(RadioButton)findViewById(R.id.radio0);
        rdb=(RadioButton)findViewById(R.id.radio1);
        rdc=(RadioButton)findViewById(R.id.radio2);
        butNext=(Button)findViewById(R.id.button1);
        setQuestionView();
        butNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioGroup grp=(RadioGroup)findViewById(R.id.radioGroup1);
                RadioButton answer=(RadioButton)findViewById(grp.getCheckedRadioButtonId());
                Log.d("la tua risposta", currentQ.getDomanda()+" "+answer.getText());
                if(currentQ.getRisposta().equals(answer.getText()))
                {
                    score++;
                    Log.d("score", "Il tuo score"+score);
                }
                if(qid<5){
                    currentQ=lista.get(qid);
                    setQuestionView();
                }else{
                    Intent intent = new Intent(QuizActivity.this, FineGioco.class);
                    Bundle b = new Bundle();
                    b.putInt("score", score);
                    intent.putExtras(b);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.activity_quiz, menu);
        return true;
    }
    private void setQuestionView()
    {
        txtQuestion.setText(currentQ.getDomanda());
        rda.setText(currentQ.getOPTA());
        rdb.setText(currentQ.getOPTB());
        rdc.setText(currentQ.getOPTC());
        qid++;
    }
}
