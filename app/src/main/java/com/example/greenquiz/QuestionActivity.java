package com.example.greenquiz;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class QuestionActivity extends AppCompatActivity {

    private Question[] questions = {
            new Question("Possedez-vous une voiture ?", 10, 0),
            new Question("Possedez-vous un vélo ?", 0, 5),
            new Question("Avez-vous déjà pris 10 fois l'avion c'est deux dernière années ?", 10, 0),
            new Question("Mangez-vous des produits de saison", 0, 5),
            new Question("Possedez-vous plus de 10 appareils connectés ?", 10, 0),
            new Question("Avez-vous des enfants ? ", 5, 0),
    };

    private Question currentQuestion;
    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_activity);

        score = getIntent().getIntExtra("score", 0);
        int numQuestion = getIntent().getIntExtra("numQuestion", 0);
        Log.d("MSG", "" + score + ", numQuestion: " + numQuestion);

        //IF CODE = LEADERBOARD
        // DISABLE BUTTON SHARE

        currentQuestion = questions[numQuestion];

        //INIT LAYOUT
        TextView text = findViewById(R.id.label);
        text.setText(currentQuestion.getLibelle());

        TextView indentation = findViewById(R.id.indentation_question);
        int nb_questions = questions.length;
        int question_actuel = numQuestion+1;
        indentation.setText("QUESTION [ " + question_actuel + " / " + nb_questions + " ]");

        Button question_btn_valider = (Button) findViewById(R.id.qestion_btn_valider);
        question_btn_valider.setEnabled(false);
        question_btn_valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent;
                if(numQuestion < questions.length - 1)
                    myIntent = new Intent(QuestionActivity.this, QuestionActivity.class);
                else {
                    myIntent = new Intent(QuestionActivity.this, ResultActivity.class);
                    myIntent.putExtra("score", score);
                }

                //
                score += getAnswerScore();
                myIntent.putExtra("numQuestion", numQuestion+1);
                myIntent.putExtra("score", score);
                startActivity(myIntent);
                finish();
            }
        });
    }

    public void onRadioButtonClicked(View v) {
        boolean checked = ((RadioButton) v).isChecked();

        if (((RadioButton)v).isChecked())
        {
            Button button = findViewById(R.id.qestion_btn_valider);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflaterMenu = getMenuInflater();
        inflaterMenu.inflate(R.menu.menu_question, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.quit:
                Intent myIntent = new Intent(QuestionActivity.this, MainActivity.class);
                setResult(MainActivity.QUIZ_CANCELED, myIntent);
                finish();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }

}