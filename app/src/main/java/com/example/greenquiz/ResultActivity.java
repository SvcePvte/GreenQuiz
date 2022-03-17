package com.example.greenquiz;

import android.app.Dialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
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

    private String drapeau;
    private int classement[] = {0, 1, 2, 3, 4};

    private int resulat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_activity);

        this.imageView = (ImageView) this.findViewById(R.id.imageView);

        resulat = getIntent().getIntExtra("score",0);

        Toast.makeText(this, "Votre score : " + resulat, Toast.LENGTH_LONG).show();

        getDrapeau();
        afficherResultat();

        Button btn_partager = (Button) findViewById(R.id.result_btn_partager);
        btn_partager.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                openDialog();
            }
        });

    }

    public void openDialog() {
        PopUp popup = new PopUp();
        popup.show(getSupportFragmentManager(), "popup");
    }

    @Override
    public void sendText(String pseudo) {

        SQLClient bdd = new SQLClient(this);
        SQLiteDatabase dbW = bdd.getWritableDatabase();
        dbW.execSQL("insert into Users values(null, '" + pseudo + "', " + this.resulat + ");");
        dbW.close();

        Toast.makeText(this, "Votre partie a bien été enregistrée " + pseudo, Toast.LENGTH_LONG).show();
        Button btn_partager = (Button) findViewById(R.id.result_btn_partager);
        btn_partager.setEnabled(false);

    }

    public String getDrapeau () {

        for (int i = 0; i < classement.length; i++)
            if (i == this.resulat) {
                drapeau = Country.values()[i].name().toLowerCase();
                break;
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
            case R.id.main_activity:
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
        Intent myIntent = new Intent(ResultActivity.this, MainActivity.class);
        startActivity(myIntent);
    }
}