package com.example.greenquiz;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLClient extends SQLiteOpenHelper {
    // Vous devez gérer le numéro de version de votre BDD (a un impact sur la reconstruction de la BDD par exemple)
    public static final int DATABASE_VERSION = 5;

    // Nom du fichier contenant la BDD (sqlite = fichier)
    public static final String  DATABASE_FILE = "users.db";

    // Requete de creation de la bdd (exemple simplifié)
    public static final String SQL_CREATE = "CREATE TABLE Users (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, score INTEGER);";

    // Requete de suppression de la bdd (exemple simplifié)
    public static final String SQL_DELETE = "DROP TABLE IF EXISTS  Users ;";


    // Constructeur permettant d'appeler le constructeur de SQLIteOpenHelper (cf. doc)
    public SQLClient(Context context){
        super (context, DATABASE_FILE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // On créé la BDD si besoin
        db.execSQL(SQLClient.SQL_CREATE);

        db.execSQL("insert into Users values(null, 'Paul', 10);");
        db.execSQL("insert into Users values(null, 'Olivier (nice)', 69);");
        db.execSQL("insert into Users values(null, 'Max', 25);");
        db.execSQL("insert into Users values(null, 'Léo', 30);");
        db.execSQL("insert into Users values(null, 'Shrek', 5);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Si la version de la BDD change.. Ici doit être mis le code pour traiter cette situation
        // Ici : traitement violent... On supprimme et on la créé à nouveau...
        // A adapter en fonction des besoins....
        db.execSQL(SQLClient.SQL_DELETE);
        this.onCreate(db);
    }

    Cursor readAllData(){
        String query = "SELECT * FROM " + "users";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    Cursor getClassement()
    {
        String query = "SELECT * FROM users ORDER BY score ASC";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    void deleteAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + "users");
    }
}
