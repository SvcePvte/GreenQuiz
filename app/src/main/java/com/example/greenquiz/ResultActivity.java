package com.example.greenquiz;

import android.app.Dialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity implements PopUp.PopUpListener {

    private ImageView imageView;
    private int score;

    private String drapeau;
    private int classement[] = {0, 10, 20, 30, 40};
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_activity);

        score = getIntent().getIntExtra("score", 0);
        this.imageView = (ImageView) this.findViewById(R.id.imageView);

        getDrapeau();
        afficherResultat();

        Button btn_partager = (Button) findViewById(R.id.result_btn_partager);
        btn_partager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });

        Button btn_leaderboard = (Button) findViewById(R.id.result_btn_classement);
        btn_leaderboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeActivityToLeaderboard();
            }
        });

        Button btn_twitter = (Button) findViewById(R.id.reslt_btn_twitter);
        btn_twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg = "Score GreenQuiz: " + score;
                String tweetUrl = "https://twitter.com/intent/tweet?text=" + msg + " &url="
                        + "";
                Uri uri = Uri.parse(tweetUrl);
                startActivity(new Intent(Intent.ACTION_VIEW, uri));
            }
        });
    }

    public void openDialog() {
        PopUp popup = new PopUp();
        popup.show(getSupportFragmentManager(), "popup");
    }

    @Override
    public void sendText(String pseudo) {
        // RODO envoyer code Classement

        SQLClient bdd = new SQLClient(this);
        SQLiteDatabase dbW = bdd.getWritableDatabase();
        dbW.execSQL("insert into Users values(null, '" + pseudo + "', " + score + ");");
        dbW.close();

        Toast.makeText(this, "Votre pseudo " + pseudo + " a bien été pris en compte", Toast.LENGTH_LONG).show();
        Button btn_partager = (Button) findViewById(R.id.result_btn_partager);
        btn_partager.setEnabled(false);
    }

    public String getDrapeau () {

        int borneInf = 0;
        int borneSup = 10;
        for (int i = 0; i <= classement.length  ; i++) {
           if (borneInf <= this.score && this.score <= borneSup){
               drapeau = Country.values()[i].name().toLowerCase();
               break;
           }
           borneSup += 10;
           borneInf += 10;
       }
        return drapeau;
    }

    public void afficherResultat () {

        Country country = Country.getByName(drapeau);

        if(country != null) {
            this.imageView.setImageResource(country.getResource());

            TextView presentation_titre = findViewById(R.id.presentation_score_titre);
            presentation_titre.setText(country.getTitle());

            TextView presentation_description = findViewById(R.id.presentation_score_texte);
            presentation_description.setText(country.getDescription());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflaterMenu = getMenuInflater();
        inflaterMenu.inflate(R.menu.menu_result, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                Intent myIntent = new Intent(ResultActivity.this, MainActivity.class);
                startActivity(myIntent);
                break;
            case R.id.share:
                shareScore();
                break;

            default:
                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }

    private void changeActivityToLeaderboard()
    {
        Intent myIntent = new Intent(ResultActivity.this, LeaderboardActivity.class);
        startActivity(myIntent);
    }

    private void shareScore()
    {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "Score GreenQuiz: " + score);
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }
}