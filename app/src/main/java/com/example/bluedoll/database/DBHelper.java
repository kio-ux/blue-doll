package com.example.bluedoll.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "BlueDoll.db";

    public DBHelper(Context context){

        super(context,DBNAME,null,1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String users= "CREATE TABLE Users(UserID TEXT PRIMARY KEY," +
                "UserName TEXT," +
                "UserEmail TEXT," +
                "UserPassword TEXT," +
                "UserGender TEXT," +
                "UserRole TEXT)";
        db.execSQL(users);
        String dolls= "CREATE TABLE Dolls(DollID TEXT PRIMARY KEY AUTOINCREMENT," +
                "DollName TEXT," +
                "DollDescription TEXT," +
                "DollImage Text," +
                "FOREIGN KEY (UserID) REFERENCES Users (UserID))";
        db.execSQL(dolls);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String dropTableUsers= "DROP TABLE IF EXISTS Users";
        db.execSQL(dropTableUsers);
        String dropTableDolls= "DROP TABLE IF EXISTS Dolls";
        db.execSQL(dropTableDolls);
    }

}
