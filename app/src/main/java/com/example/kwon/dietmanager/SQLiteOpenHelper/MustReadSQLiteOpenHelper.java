package com.example.kwon.dietmanager.SQLiteOpenHelper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.kwon.dietmanager.DataClass.MustRead;

import java.util.ArrayList;

/**
 * Created by god on 2017-06-11.
 */

public class MustReadSQLiteOpenHelper extends SQLiteOpenHelper {

    public MustReadSQLiteOpenHelper(Context context) {
        super(context, "DietManager", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public int getCount(){
        int count=0;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("select count(*) from mustRead", null);

        if(cursor!=null){
            cursor.moveToFirst();
            count = cursor.getInt(0);
        }

        return count;
    }

    public ArrayList<MustRead> getMustRead(){
        ArrayList<MustRead> mustReads = new ArrayList<MustRead>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("select * from mustRead", null);

        if(cursor != null){
            if(cursor.moveToFirst()){
                do{
                    MustRead mustRead = new MustRead();
                    mustRead.setId(cursor.getInt(0));
                    mustRead.setName(cursor.getString(1));
                    mustRead.setContents(cursor.getString(2));
                    mustReads.add(mustRead);
                }while(cursor.moveToNext());
            }
        }

        return mustReads;
    }
}
