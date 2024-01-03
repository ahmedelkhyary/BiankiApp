package com.bianki.biankiapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import com.bianki.biankiapp.ClassModel.getToken;

import java.util.ArrayList;

import androidx.annotation.Nullable;

public class Helper extends SQLiteOpenHelper {


    public static final String Databasename = "smalldb.db";
    public static final int version = 1;

    public Helper(@Nullable Context context) {
        super(context, Databasename, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

//        final String CreateTable = "CREATE TABLE " + Contract.WiatlistEntity.TableName + "("+
//                Contract.WiatlistEntity._ID + "INTEGER PRIMARY KEY AUTOINCREMENT," +
//                Contract.WiatlistEntity.Columnnem + "TEXT NOT NULL," +
//                Contract.WiatlistEntity.Columnsalary + "TEXT NOT NULL," +
//                Contract.WiatlistEntity.Time + "TIMESTAMP DEFAULT CURRENT_TIMESTAMP" + "); ";

        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS idtable ( id INTEGER PRIMARY KEY , nameofid TEXT )");




    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS idtable");



        onCreate(sqLiteDatabase);

    }





    public void insertid(String x) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nameofid", x);
        sqLiteDatabase.insert("idtable", null, contentValues);
    }


    public String getiddata() {
        String x = null;
        ArrayList<getToken> arrayList = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from idtable ", null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
//
            x = cursor.getString(cursor.getColumnIndex("nameofid"));
//            String y = cursor.getString(cursor.getColumnIndex("salary"));
//
//
//            Log.e("db" , x);
//            Log.e("dbdb" , y);
//
//            arrayList.add(new makeorder(x , y));
//
            cursor.moveToNext();

//        String x = cursor.getString(cursor.getColumnIndex("name"));



        }

        return x;
    }





}
