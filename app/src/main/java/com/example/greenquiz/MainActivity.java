package com.example.greenquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button main_btn_lancer = (Button) findViewById(R.id.main_btn_lancer);

        main_btn_lancer.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, QuestionnaryActivity.class);
                startActivity(myIntent);
           }
        });
    }
}