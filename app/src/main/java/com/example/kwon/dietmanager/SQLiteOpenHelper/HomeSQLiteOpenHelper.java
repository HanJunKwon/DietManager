package com.example.kwon.dietmanager.SQLiteOpenHelper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.kwon.dietmanager.Item.DailyReccordItem;
import com.example.kwon.dietmanager.Item.UserInfoItem;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by god on 2017-06-26.
 */

public class HomeSQLiteOpenHelper extends SQLiteOpenHelper {
    public HomeSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public UserInfoItem getUserInfo(){
        UserInfoItem userInfoItem = new UserInfoItem();

        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("select * from userInfo;", null);

        if(cursor!=null){
            cursor.moveToFirst();
            userInfoItem.setName(cursor.getString(0));
            userInfoItem.setAge(cursor.getInt(1)); // age
            userInfoItem.setWeight(cursor.getString(2)); // weiwght
            userInfoItem.setHeight(cursor.getInt(3)); // height
            userInfoItem.setGender(cursor.getInt(4)); // gender
            userInfoItem.setActivityMass(cursor.getInt(5)); // activityMass
            userInfoItem.setGoalWeight(cursor.getInt(6)); // goalWeight
            userInfoItem.setStartDate(cursor.getString(7)); // startDate
            userInfoItem.setEndDate(cursor.getString(8)); // endDate
            userInfoItem.setPromise(cursor.getString(9)); // promise
        }

        return userInfoItem;
    }

    public ArrayList<DailyReccordItem> getDailyRecordItems(){
        ArrayList<DailyReccordItem> dailyReccordItems = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("select * from dailyRecord", null);

        if(cursor != null){
            if(cursor.moveToFirst()) {
                do {
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
