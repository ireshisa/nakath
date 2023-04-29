package com.universlsoftware.nakathpathraya.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "nakath.db";
    public static final String TABLENAME = "user";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "PASSWORD";


    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getReadableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create  table " + TABLENAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT," + "NAME TEXT,PASSWORD TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLENAME);
        onCreate(sqLiteDatabase);
    }

    public boolean insetData(String name, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, password);
        long result = db.insert(TABLENAME, null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor getall() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("select * from user", null);
        return result;
    }

    public boolean updatedata(String id, String name, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, password);
        long result = db.update(TABLENAME, contentValues, "ID = ?", new String[]{id});
        return true;
    }

    public Integer deletData(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLENAME, "ID = ?", new String[]{id});
    }


    public int login(String user_name, String user_password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"ID"};
        String selection = "NAME = ? AND PASSWORD = ?";
        String[] selectionArgs = {user_name, user_password};
        Cursor cursor = db.query(TABLENAME, columns, selection, selectionArgs, null, null, null);


        int count;
        if (cursor.moveToFirst()) {
            @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex("ID"));
            // store the id in a variable or do something else with it
            count = id;
        } else {
            count = cursor.getCount();
        }

        cursor.close();
        return count;
    }


    @SuppressLint("Range")
    public String[] getUserData(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = { COL_2, COL_3 };
        String selection = "ID = ?";
        String[] selectionArgs = { id };
        Cursor cursor = db.query(TABLENAME, columns, selection, selectionArgs, null, null, null);
        String[] userData = new String[2];
        if (cursor.moveToFirst()) {
            userData[0] = cursor.getString(cursor.getColumnIndex(COL_2));
            userData[1] = cursor.getString(cursor.getColumnIndex(COL_3));
        }
        cursor.close();
        return userData;
    }

}
