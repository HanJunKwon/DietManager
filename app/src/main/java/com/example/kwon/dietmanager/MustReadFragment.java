package com.example.kwon.dietmanager;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.example.kwon.dietmanager.Adapter.MustReadAdapter;
import com.example.kwon.dietmanager.Item.MustReadItem;
import com.example.kwon.dietmanager.SQLiteOpenHelper.MustReadSQLiteOpenHelper;

import java.util.ArrayList;


public class MustReadFragment extends Fragment {
    MustReadSQLiteOpenHelper myDB;
    ListView listView;

    public MustReadFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_must_read, container, false);

        listView = (ListView)view.findViewById(R.id.listView);

        Log.v("알림" , "dataSetting이전");
        // 아이템 추가 및 어댑터 등록
        dataSetting();

        return view;
    }

    private void dataSetting(){
        MustReadAdapter adapter = new MustReadAdapter(); // 어댑터 지정
        myDB = new MustReadSQLiteOpenHelper(getActivity()); // 데이터 베이스 연결

        int count = myDB.getCount(); // table의 레코드 개수
        Log.v("알림", "카운트 : "+count);
        ArrayList<MustReadItem> mustReads = myDB.getMustRead();

        for(int i=0; i<count; ++i){
            Log.v("알림", "id:"+ mustReads.get(i).getId()+", 제목:"+mustReads.get(i).getName()+", 내용:"+mustReads.get(i).getContents());
            adapter.addItem(mustReads.get(i).getId(), mustReads.get(i).getName(), mustReads.get(i).getContents());
        }

        listView.setAdapter(adapter);
    }
}
