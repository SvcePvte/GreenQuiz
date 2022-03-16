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
    private int score = 0;

    private String drapeau;
    private int classement[] = {0, 100, 200, 300, 400, 500, 600, 700, 800, 900, 1000};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_activity);

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
        dbW.execSQL("insert into Users values(null, '" + pseudo + "', " + 123 + ");");
        dbW.close();

        Toast.makeText(this, "Votre pseudo " + pseudo + " a bien été pris en compte", Toast.LENGTH_LONG).show();
        Button btn_partager = (Button) findViewById(R.id.result_btn_partager);
        btn_partager.setEnabled(false);

    }

    public String getDrapeau () {

        int borneInf = 0;
        int borneSup = 100;
        for (int i = 0; i <= 10  ; i++) {
           if (borneInf <= this.score && this.score <= borneSup){
               drapeau = Country.values()[i].name().toLowerCase();
               break;
           }
           borneSup += 100;
           borneInf += 100;
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
}