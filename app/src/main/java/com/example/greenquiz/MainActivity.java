package com.example.greenquiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button main_btn_lancer = (Button) findViewById(R.id.main_btn_lancer);
        main_btn_lancer.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, QuestionActivity.class);
                myIntent.putExtra("numQuestion",0);
                myIntent.putExtra("score", 0);
                startActivity(myIntent);
           }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflaterMenu = getMenuInflater();
        inflaterMenu.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.leaderboard:
                changeActivityToLeaderboard();
                break;
            case R.id.translate_app_fr:
                setAppLocal("fr");
                Intent monIntent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(monIntent);
                break;
            case R.id.translate_app_en:
                setAppLocal("en");
                Intent myIntent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(myIntent);
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

    public void setAppLocal(String localCode) {
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            conf.setLocale(new Locale(localCode.toLowerCase()));
        } else {
            conf.locale = new Locale(localCode.toLowerCase());
        }
        res.updateConfiguration(conf, dm);
    }
}