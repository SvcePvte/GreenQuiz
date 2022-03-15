package com.example.greenquiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

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
                onButtonShowPopupWindowClick(v);
            }
        });
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflaterMenu = getMenuInflater();
        inflaterMenu.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.main_item:
                Intent main = new Intent(ResultActivity.this, MainActivity.class);
                startActivity(main);
                break;
            case R.id.questionnary_item:
                Intent questionnary = new Intent(ResultActivity.this, QuestionnaryActivity.class);
                startActivity(questionnary);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }

    public void onButtonShowPopupWindowClick(View view) {

        // inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popup, null);

        // create the popup window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        // show the popup window
        // which view you pass in doesn't matter, it is only used for the window tolken
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        // dismiss the popup window when touched
        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                return true;
            }
        });
    }
}