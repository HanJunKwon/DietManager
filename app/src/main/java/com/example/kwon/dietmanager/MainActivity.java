package com.example.kwon.dietmanager;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btnStart, btnInit;
    SQLiteDatabase myDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart = (Button)findViewById(R.id.btnStart);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDB = openOrCreateDatabase("DietManager", MODE_PRIVATE, null);
                Cursor cursor = myDB.rawQuery("select count(*) from sqlite_master where name='userInfo';", null);
                int count = 0;
                if(cursor.moveToFirst()){
                    count = cursor.getInt(0);
                }

                // userInfo 테이블이 존재하지 않는 경우
                if(count == 0){
                    Intent startIntent = new Intent(MainActivity.this, StartActivity.class);
                    startActivity(startIntent);
                }
                // userInfo테이블이 존재하는 경우
                else{
                    Intent NavigationIntent = new Intent(MainActivity.this, NavigationActivity.class);
                    startActivity(NavigationIntent);
                }
                if(myDB != null) myDB.close();
            }
        });

        btnInit = (Button)findViewById(R.id.btnInit);
        btnInit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("모든 정보를 초기화 하시겠습니까?");

                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 확인을 누를 경우 사용자 정보와 관련된 table 드랍
                        myDB = openOrCreateDatabase("DietManager", MODE_PRIVATE, null);
                        myDB.execSQL("Drop table if exists userInfo");
                        myDB.execSQL("Drop table if exists dailyRecord");
                        myDB.execSQL("Drop table if exists dailyExerciseRecord");

                        Toast.makeText(MainActivity.this, "정보가 초기화 되었습니다", Toast.LENGTH_LONG).show();
                    }
                });

                dialog.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                dialog.show();
            }
        });
    }
}
