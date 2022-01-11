package com.example.bluedoll.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public final String USERS_TABLE = "Users";
    public final String DOLLS_TABLE = "Dolls";

    public DBHelper(Context context){

        super(context,"Project Blue Doll",null,1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String users= "CREATE TABLE "+ USERS_TABLE + "(" +
                "UserID TEXT PRIMARY KEY," +
                "UserName TEXT," +
                "UserEmail TEXT," +
                "UserPassword TEXT," +
                "UserGender TEXT," +
                "UserRole TEXT)";
        db.execSQL(users);
        String dolls= "CREATE TABLE "+ DOLLS_TABLE + "(" +
                "DollID integer PRIMARY KEY AUTOINCREMENT," +
                "DollName TEXT," +
                "DollDescription TEXT," +
                "DollImage BLOB," +
                "UserID TEXT REFERENCES Users (UserID))";
        db.execSQL(dolls);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String dropTableUsers= "DROP TABLE IF EXISTS "+ USERS_TABLE;
        db.execSQL(dropTableUsers);
        String dropTableDolls= "DROP TABLE IF EXISTS "+ DOLLS_TABLE;
        db.execSQL(dropTableDolls);
    }

}
