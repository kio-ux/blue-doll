package com.example.bluedoll.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.bluedoll.models.Users;

import java.util.ArrayList;

public class UsersHelper {

    private DBHelper dbHelper;
    private SQLiteDatabase db;

    public UsersHelper(Context context) {
        this.dbHelper = new DBHelper(context);
    }



    public boolean insertUsers(String UserID, String UserName, String UserEmail, String UserPassword, String UserGender, String UserRole){
        db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("UserID", UserID);
        cv.put("UserName", UserName);
        cv.put("UserEmail", UserEmail);
        cv.put("UserPassword", UserPassword);
        cv.put("UserGender", UserGender);
        cv.put("UserRole", UserRole);
        long result = db.insert(dbHelper.USERS_TABLE, null,cv);
        return (result == -1) ? false :true;
    }

    public ArrayList<Users> getUsersData(){
        db = dbHelper.getReadableDatabase();

        String sql = "SELECT * FROM "+ dbHelper.USERS_TABLE;
        Cursor c = db.rawQuery(sql,null);
        if(c != null && c.getCount()>0){
            ArrayList<Users> usersArrayList = new ArrayList<>();
            c.moveToFirst();
            while(!c.isAfterLast()){
                usersArrayList.add(new Users(c.getString(0),c.getString(1),c.getString(2),c.getString(3),c.getString(4),c.getString(5)));
                c.moveToNext();
            }
            return usersArrayList;
        }

        return null;
    }


    public ArrayList<Users> getUsersDataLogin(String email){
        db = dbHelper.getReadableDatabase();

        String sql = "SELECT * FROM "+ dbHelper.USERS_TABLE + " WHERE Users.UserEmail = '" + email + "'";
        Cursor c = db.rawQuery(sql,null);
        if(c != null && c.getCount()>0){
            ArrayList<Users> usersArrayList = new ArrayList<>();
            c.moveToFirst();
            while(!c.isAfterLast()){
                usersArrayList.add(new Users(c.getString(0),c.getString(1),c.getString(2),c.getString(3),c.getString(4),c.getString(5)));
                c.moveToNext();
            }
            return usersArrayList;
        }

        return null;
    }

    public boolean checkLogin(String emailInput, String passwordInput){
        db =dbHelper.getReadableDatabase();
        String sql = "SELECT Users.UserEmail, Users.UserPassword from " + dbHelper.USERS_TABLE;
        Cursor c= db.rawQuery(sql,null);
        String emailDatabase;
        String passwordDatabase;
        if(c.moveToFirst()){
            do {
                emailDatabase = c.getString(0);
                passwordDatabase = c.getString(1);
                if (emailDatabase.equals(emailInput)&&passwordDatabase.equals(passwordInput)) {
                    return true;
                }
            } while (c.moveToNext());
        }
        c.close();

        return false;
    }

    public boolean checkRegister(String emailInput){
        db =dbHelper.getReadableDatabase();
        String sql = "SELECT Users.UserEmail FROM " + dbHelper.USERS_TABLE;
        Cursor c= db.rawQuery(sql,null);
        String emailDatabase;
        if(c.moveToFirst()){
            do {
                emailDatabase = c.getString(0);
                if (emailDatabase.equals(emailInput)) {
                    return true;
                }
            } while (c.moveToNext());
        }
        c.close();

        return false;
    }


}
