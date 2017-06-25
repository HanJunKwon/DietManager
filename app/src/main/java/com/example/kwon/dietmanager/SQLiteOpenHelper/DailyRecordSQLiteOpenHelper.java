package com.example.kwon.dietmanager.SQLiteOpenHelper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.kwon.dietmanager.Item.DailyReccordItem;

import java.util.ArrayList;

/**
 * Created by god on 2017-06-26.
 */

public class DailyRecordSQLiteOpenHelper extends SQLiteOpenHelper {
    public DailyRecordSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public int getCount(){
        int count = 0;

        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("select count(*) from dailyRecord", null);

        if(cursor != null){
            cursor.moveToFirst();
            count = cursor.getInt(0);
        }

        return count;
    }

    public ArrayList<DailyReccordItem> getDailyRecords(){
        ArrayList<DailyReccordItem> dailyReccordItems = new ArrayList<>();

        SQLiteDatabase db =getReadableDatabase();

        Cursor cursor = db.rawQuery("select * from dailyRecord", null);

        if(cursor != null){
            if(cursor.moveToFirst()){
                do{
                    DailyReccordItem item = new DailyReccordItem();
                    item.setId(cursor.getInt(0));
                    item.setWeight(cursor.getString(1));
                    item.setDate(cursor.getString(2));
                    dailyReccordItems.add(item);
                }while(cursor.moveToNext());
            }
        }
        return dailyReccordItems;
    }
}
