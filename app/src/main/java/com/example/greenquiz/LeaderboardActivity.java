package com.example.greenquiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class LeaderboardActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    SQLClient bdd;
    ArrayList<String> user_classement, user_id, user_name, user_score;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        recyclerView = findViewById(R.id.recyclerView);

        //TextView textView = (TextView) findViewById(R.id.recyclerView);
        //textView.setText("HELLO");
        /*
        SQLClient bdd = new SQLClient(this);
        this.saveData(bdd);
        this.readData(bdd);
        bdd.close();

        String mystring = getResources().getString(R.string.mystring);
         */

        bdd = new SQLClient(this);
        user_classement = new ArrayList<>();
        user_id = new ArrayList<>();
        user_name = new ArrayList<>();
        user_score = new ArrayList<>();

        //bdd.deleteAllData();

        //this.saveData(bdd);

        storeDataInArrays();

        //STORE DATA IN ARRAYS

        customAdapter = new CustomAdapter(LeaderboardActivity.this, user_classement, user_id, user_name, user_score);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(LeaderboardActivity.this));
    }

    void storeDataInArrays(){
        Cursor cursor = bdd.getClassement();

        int i = 1;

        if(cursor.getCount() == 0){
            return;
            //empty_imageview.setVisibility(View.VISIBLE);
            //no_data.setVisibility(View.VISIBLE);
        }else{
            while (cursor.moveToNext()){
                user_classement.add("" + i);
                user_id.add(cursor.getString(0));
                user_name.add(cursor.getString(1));
                user_score.add(cursor.getString(2));

                i++;
            }
            //empty_imageview.setVisibility(View.GONE);
            //no_data.setVisibility(View.GONE);
        }
    }

    public void saveData(SQLClient bdd)
    {
        SQLiteDatabase dbW = bdd.getWritableDatabase();

        /*
        ContentValues valeursClient1 = new ContentValues();
        valeursClient1.put("id", "1");
        valeursClient1.put("name", "Paul");
        dbW.insert("Users", null, valeursClient1);

        ContentValues valeursClient2 = new ContentValues();
        valeursClient2.put("id", "2");
        valeursClient2.put("name", "Léo");
        dbW.insert("Users", null, valeursClient2);
         */

        dbW.execSQL("insert into Users values(null, 'Paul', 100);");
        dbW.execSQL("insert into Users values(null, 'Léo', 500);");
        dbW.execSQL("insert into Users values(null, 'Max', 300);");
        dbW.execSQL("insert into Users values(null, 'Guillaume', 800);");

        dbW.close();
    }

    public void readData(SQLClient bdd)
    {
        SQLiteDatabase dbR = bdd.getReadableDatabase();

        String[] table = {"id", "name", "score"};
        String[] selectFilters = {};

        Cursor curs = dbR.query("Users", table, "", selectFilters, null, null, "name DESC");

        if(curs.moveToFirst())
        {
            do{
                long userID = curs.getLong(curs.getColumnIndexOrThrow("id"));
                String userNAME = curs.getString(curs.getColumnIndexOrThrow("name"));

                TextView textView = (TextView) findViewById(R.id.recyclerView);
                textView.setText(userID + ", " + userNAME);

                Log.v("MSG", userID + ", " + userNAME);
            }while(curs.moveToNext());
        }
        else{
            Toast.makeText(this, "PAS DE REPONSE..", Toast.LENGTH_SHORT).show();
        }



        /*

        Cursor cursSQL = dbR.rawQuery("select id, name from Users order by name ASC", null);
        if(cursSQL.moveToFirst())
        {
            do {
                long userID = cursSQL.getLong(cursSQL.getColumnIndexOrThrow("id"));
                String userNAME = cursSQL.getString(cursSQL.getColumnIndexOrThrow("name"));

                Log.v("MSG", userID + ", " + userNAME);
            } while(cursSQL.moveToNext());
        }
        else{
            Toast.makeText(this, "Pas de réponse..", Toast.LENGTH_SHORT).show();
        }
        */

        dbR.close();
    }
}