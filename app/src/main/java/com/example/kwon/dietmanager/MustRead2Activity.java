package com.example.kwon.dietmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.kwon.dietmanager.Item.MustReadItem;
import com.example.kwon.dietmanager.SQLiteOpenHelper.MustReadSQLiteOpenHelper;

import org.w3c.dom.Text;

public class MustRead2Activity extends AppCompatActivity {
    TextView txtTitle, txtContents;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_must_read2);

        //  MustReadActiviry에서 선택한 글의 아이디를 넘겨받는다
        Intent intent = getIntent();
        id = intent.getExtras().getInt("id");

        MustReadSQLiteOpenHelper mustReadHelper = new MustReadSQLiteOpenHelper(this, "MustRead", null, 2);

        MustReadItem mustReadItem = mustReadHelper.getMustRead(id);

        txtTitle = (TextView)findViewById(R.id.txtTitle);
        txtTitle.setText(mustReadItem.getTitle());
        txtContents = (TextView)findViewById(R.id.txtContents);
        txtContents.setText(mustReadItem.getContents());
    }
}
