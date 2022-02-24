package com.example.greenquiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_activity);

        TextView score = findViewById(R.id.resul_score);
        score.setText("score :" + getIntent().getStringExtra("score"));

        Button result_btn_partager = (Button) findViewById(R.id.result_btn_partager);
        /* Bouton partager
        bnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent( ResultActivity.this, ResultActivity.class );
                startActivity(myIntent);
            }
        });
        */
    }
}