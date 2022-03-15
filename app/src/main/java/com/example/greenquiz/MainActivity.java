package com.example.greenquiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
                myIntent.putExtra("numQuestion",0);
                myIntent.putExtra("score", 0);
                startActivity(myIntent);
           }
        });

        //changeActivityToLeaderboard();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflaterMenu = getMenuInflater();
        inflaterMenu.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.leaderboard:
                changeActivityToLeaderboard();
                break;

            case R.id.quitter_button:
                System.exit(0);
                break;

            default:
                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }

    private void changeActivityToLeaderboard()
    {
        Intent myIntent = new Intent(MainActivity.this, LeaderboardActivity.class);
        startActivity(myIntent);
    }
}