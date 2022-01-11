package com.example.bluedoll.database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.bluedoll.models.Dolls;

import java.util.ArrayList;

public class DollsHelper {

    private DBHelper dbHelper;
    private SQLiteDatabase db;

    public DollsHelper(Context context) {
        this.dbHelper = new DBHelper(context);
    }

    public boolean insertDolls(String dollName, String dollDescription, byte [] dollImage, String userID){
        db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("DollName", dollName);
        cv.put("DollDescription", dollDescription);
        cv.put("DollImage", dollImage);
        cv.put("UserID", userID);
        long result = db.insert(dbHelper.DOLLS_TABLE, null,cv);
        return (result == -1) ? false :true;
    }

    public boolean deleteDolls(Integer dollID){
        db = dbHelper.getWritableDatabase();
        int result = db.delete(dbHelper.DOLLS_TABLE, "DollID =?", new String[]{dollID.toString()});
        return (result > 0) ? true: false;
    }

    public boolean update(Integer id, String name, String description, byte [] image){
        db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("DollName", name);
        cv.put("DollDescription", description);
        cv.put("DollImage", image);

        int result = db.update(dbHelper.DOLLS_TABLE, cv, "DollID =?", new String[]{id.toString()});
        return (result > 0) ? true: false;
    }



    public ArrayList<Dolls> getDollsData(){
        db = dbHelper.getReadableDatabase();

        String sql = "SELECT Dolls.DollID, Dolls.DollName, Dolls.DollDescription, Users.UserName, Dolls.DollImage FROM "+
                dbHelper.DOLLS_TABLE +
                " JOIN "+ dbHelper.USERS_TABLE + " ON Users.UserID = Dolls.UserID";
        Cursor c = db.rawQuery(sql,null);
        if(c != null && c.getCount()>0){
            ArrayList<Dolls> dollsArrayList = new ArrayList<>();
            c.moveToFirst();
            while(!c.isAfterLast()){
                dollsArrayList.add(new Dolls(c.getInt(0),c.getString(1),c.getString(2),c.getString(3),c.getBlob(4)));
                c.moveToNext();
            }
            return dollsArrayList;
        }

        return null;
    }

    public ArrayList<Dolls> getDollsID(Integer ID){
        db = dbHelper.getReadableDatabase();

        String sql = "SELECT Dolls.DollID, Dolls.DollName, Dolls.DollDescription, Users.UserName, Dolls.DollImage FROM "+
                dbHelper.DOLLS_TABLE +
                " JOIN "+ dbHelper.USERS_TABLE + " ON Users.UserID = Dolls.UserID" +
                " WHERE Dolls.DollID =" + ID;
        Cursor c = db.rawQuery(sql,null);
        if(c != null && c.getCount()>0){
            ArrayList<Dolls> dollsArrayList = new ArrayList<>();
            c.moveToFirst();
            while(!c.isAfterLast()){
                dollsArrayList.add(new Dolls(c.getInt(0),c.getString(1),c.getString(2),c.getString(3),c.getBlob(4)));
                c.moveToNext();
            }
            return dollsArrayList;
        }

        return null;
    }

    public boolean checkDollName(String nameInput){
        db =dbHelper.getReadableDatabase();
        String sql = "SELECT Dolls.DollName FROM " + dbHelper.DOLLS_TABLE;
        Cursor c= db.rawQuery(sql,null);
        String nameDatabase;
        if(c.moveToFirst()){
            do {
                nameDatabase = c.getString(0);
                if (nameDatabase.equals(nameInput)) {
                    return true;
                }
            } while (c.moveToNext());
        }
        c.close();

        return false;
    }



}
