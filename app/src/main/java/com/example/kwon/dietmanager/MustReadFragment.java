package com.example.kwon.dietmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.example.kwon.dietmanager.Adapter.MustReadAdapter;
import com.example.kwon.dietmanager.Item.MustReadItem;
import com.example.kwon.dietmanager.SQLiteOpenHelper.MustReadSQLiteOpenHelper;

import java.util.ArrayList;


public class MustReadFragment extends Fragment {
    MustReadSQLiteOpenHelper myDB;
    ListView listView;
    ArrayList<MustReadItem> mustReads;

    public MustReadFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_must_read, container, false);

        listView = (ListView)view.findViewById(R.id.listView);
        // listView 아이템 클릭 이벤트
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 선택된 필독사항의 내용을 보는 액티비티로 이동한다.
                Intent intent = new Intent(getActivity().getApplicationContext(), MustRead2Activity.class);
                intent.putExtra("id", mustReads.get(position).getId());
                startActivity(intent);
            }
        });
        // 아이템 추가 및 어댑터 등록
        dataSetting();

        return view;
    }

    private void dataSetting(){
        MustReadAdapter adapter = new MustReadAdapter(); // 어댑터 지정
        myDB = new MustReadSQLiteOpenHelper(getActivity(), "MustRead", null, 2); // 데이터 베이스 연결

        int count = myDB.getCount(); // table의 레코드 개수
        mustReads = myDB.getMustReads();
        for(int i=0; i<count; ++i){
            adapter.addItem(mustReads.get(i).getId(), mustReads.get(i).getTitle(), mustReads.get(i).getContents());
        }

        listView.setAdapter(adapter);
    }
}
