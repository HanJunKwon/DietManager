package com.example.kwon.dietmanager.SQLiteOpenHelper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.kwon.dietmanager.Item.MustReadItem;

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
        /*
        SQLiteDatabase db = myDB.getReadalbeDatabase();
        SQLiteDatabase db = myDB.getWriteableDatase();
        둘 중에 하나가 실행되면 호출 되는 함수
        반드시 여기에 해당 테이블이 존재하는지 확인하는 명령어를 적고 없으면 테이블을 만들어줘야한다.
        adb shell로 테이블 만들어서 데이터넣고 해야하는데 이거는 나중에 설명
        */
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

    public ArrayList<MustReadItem> getMustRead(){
        ArrayList<MustReadItem> mustReads = new ArrayList<MustReadItem>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("select * from mustRead", null);

        if(cursor != null){
            if(cursor.moveToFirst()){
                do{
                    MustReadItem mustRead = new MustReadItem();
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
