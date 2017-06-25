package com.example.kwon.dietmanager;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.Button;
import android.widget.ListView;

import com.example.kwon.dietmanager.Item.DailyReccordItem;
import com.example.kwon.dietmanager.SQLiteOpenHelper.DailyRecordSQLiteOpenHelper;

import java.util.ArrayList;

public class DailyRecordFragment extends Fragment {
    DailyRecordSQLiteOpenHelper myDB;
    ListView listView;
    Button btnSubmit;
    public DailyRecordFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_daily_record, container, false);
        listView = (ListView)view.findViewById(R.id.listView);
        btnSubmit = (Button) view.findViewById(R.id.btnSubmit);

        dataSetting();

        //if문과 함께 버튼 클릭 이벤트 발동
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDB.execSQL("insert into dailyRecord(" + weight + "," + date ") values(" + 몸무게부분 + "," + 날짜부분 + ")");
            }
        });
        // Inflate the layout for this fragment
        return view;
    }

    public void dataSetting(){
        myDB = new DailyRecordSQLiteOpenHelper(getActivity(), "DietManager", null, 1);
        int count = myDB.getCount();
        ArrayList<DailyReccordItem> dailyReccordItems = myDB.getDailyRecords();

        for(int i=0; i<count; ++i){
            // myDB.execSQL("insert into dailyRecord(" + weight + "," + date ") values(" + 몸무게부분 + "," + 날짜부분 + ")");
        }
    }


}