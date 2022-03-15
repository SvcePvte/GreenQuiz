package com.example.greenquiz;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_activity);

        score = getIntent().getIntExtra("score", 0);

        TextView textScore = findViewById(R.id.resul_score);
        textScore.setText("score :" + score);

        Button result_tweet_btn = (Button) findViewById(R.id.result_tweet_btn);
        result_tweet_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(),"Hello Javatpoint",Toast.LENGTH_SHORT).show();
                String msg = "Score GreenQuiz: " + score;
                String tweetUrl = "https://twitter.com/intent/tweet?text=" + msg + " &url="
                        + "";
                Uri uri = Uri.parse(tweetUrl);
                startActivity(new Intent(Intent.ACTION_VIEW, uri));
            }
        });

        Button result_btn_partager = (Button) findViewById(R.id.result_btn_partager);
        result_btn_partager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Score GreenQuiz: " + score);
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
            }
        });


    }
}