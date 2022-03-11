package com.example.greenquiz;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

public class QuestionnaryActivity extends AppCompatActivity {

    private Question[] questions = {
            new Question("Possedez-vous une voiture ?", 50, 0),
            new Question("Possedez-vous un vélo ?", 20, 0)
    };

    private Question currentQuestion;

    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questionnary_activity);

        score = getIntent().getIntExtra("score", 0);
        int numQuestion = getIntent().getIntExtra("numQuestion", 0);
        Log.d("MSG", "" + score + ", numQuestion: " + numQuestion);

        currentQuestion = questions[numQuestion];

        //INIT LAYOUT
        TextView text = findViewById(R.id.label);
        text.setText(currentQuestion.getLibelle());

        Button button = findViewById(R.id.qestionnary_btn_valider);
        button.setEnabled(false);

        Button qestionnary_btn_valider = (Button) findViewById(R.id.qestionnary_btn_valider);
        qestionnary_btn_valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent;
                if(numQuestion < questions.length - 1)
                    myIntent = new Intent(QuestionnaryActivity.this, QuestionnaryActivity.class);
                else
                    myIntent = new Intent( QuestionnaryActivity.this, ResultActivity.class );

                //
                score += getAnswerScore();
                myIntent.putExtra("numQuestion", numQuestion+1);
                myIntent.putExtra("score", score);
                startActivity(myIntent);
            }
        });
    }

    public void onRadioButtonClicked(View v) {
        boolean checked = ((RadioButton) v).isChecked();

        if (((RadioButton)v).isChecked())
        {
            Button button = findViewById(R.id.qestionnary_btn_valider);
            button.setEnabled(true);
        }

        /*
        switch (v.getId()) {
            case R.id.radio_btn_1:
                if (checked)
                    this.score += currentQuestion.getNbOui();
                    break;
            case R.id.radio_btn_2:
                if (checked)
                    this.score += currentQuestion.getNbNon();
                break;
         }
         */
    }

    private int getAnswerScore()
    {
        int result = 0;

        RadioButton btn1 = findViewById(R.id.radio_btn_1);
        RadioButton btn2 = findViewById(R.id.radio_btn_2);

        if (btn1.isChecked())
            result = currentQuestion.getNbOui();
        else
            result = currentQuestion.getNbNon();

        return result;
    }
}