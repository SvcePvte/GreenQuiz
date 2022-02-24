package com.example.greenquiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;


import androidx.appcompat.app.AppCompatActivity;

public class QuestionnaryActivity extends AppCompatActivity {

    private int score = 0;

    private Question[] questions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questionnary_activity);

        Button qestionnary_btn_valider = (Button) findViewById(R.id.qestionnary_btn_valider);

        qestionnary_btn_valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent( QuestionnaryActivity.this, ResultActivity.class );
                myIntent.putExtra("score", "" + score);
                startActivity(myIntent);
            }
        });
    }

    public void onRadioButtonClicked(View v) {
        boolean checked = ((RadioButton) v).isChecked();
        switch (v.getId()) {
            case R.id.radio_btn_1:
                if (checked)
                    this.score += 50;
                    break;
            case R.id.radio_btn_2:
                if (checked)
                    this.score += 0;
                break;
        }
    }
}