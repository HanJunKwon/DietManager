package com.example.kwon.dietmanager.SQLiteOpenHelper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.kwon.dietmanager.Item.MustReadItem;
import com.example.kwon.dietmanager.R;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;


/**
 * Created by god on 2017-06-11.
 */

public class MustReadSQLiteOpenHelper extends SQLiteOpenHelper {
    Context con;
    public MustReadSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        con = context;
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
        Log.v("알림","onCreate()");
        db.execSQL("Create table mustRead(id INTEGER PRIMARY KEY AUTOINCREMENT, title text not null, contents text not null);");
        db.execSQL("Insert into mustRead(title, contents) values('"+con.getResources().getString(R.string.title1)+"', '"+con.getResources().getString(R.string.contents1)+"');");
        db.execSQL("Insert into mustRead(title, contents) values('"+con.getResources().getString(R.string.title2)+"', '"+con.getResources().getString(R.string.contents2)+"');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion < newVersion){
            db.execSQL("DROP TABLE IF EXISTS mustRead");
            onCreate(db);
        }
    }

    public int getCount(){
        int count=0;

        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("select count(*) from mustRead", null);

        if(cursor!=null){
            cursor.moveToFirst();
            count = cursor.getInt(0);
        }

        return count;
    }

    public ArrayList<MustReadItem> getMustReads(){
        ArrayList<MustReadItem> mustReads = new ArrayList<MustReadItem>();

        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("select * from mustRead", null);

        if(cursor != null){
            if(cursor.moveToFirst()){
                do{
                    MustReadItem mustRead = new MustReadItem();
                    mustRead.setId(cursor.getInt(0));
                    mustRead.setTitle(cursor.getString(1));
                    mustRead.setContents(cursor.getString(2));

                    // 내용이 20글자 이상인 경우에는 짤라서 ...으로 표시
                    if(mustRead.getContents().length()>30){
                        String contents = mustRead.getContents().substring(0,30);
                        contents = contents.concat("...");
                        mustRead.setContents(contents);
                    }
                    mustReads.add(mustRead);
                }while(cursor.moveToNext());
            }
        }
        return mustReads;
    }

    public MustReadItem getMustRead(int id){
        MustReadItem item = new MustReadItem();

        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("select title, contents from mustRead where id="+id+";", null);

        if(cursor != null){
            if(cursor.moveToFirst()){
                item.setTitle(cursor.getString(0));
                item.setContents(cursor.getString(1));
            }
        }
        return item;
    }
}
