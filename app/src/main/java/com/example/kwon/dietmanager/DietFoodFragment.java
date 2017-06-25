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

import com.example.kwon.dietmanager.Adapter.DietFoodAdapter;
import com.example.kwon.dietmanager.Item.DietFoodItem;
import com.example.kwon.dietmanager.SQLiteOpenHelper.DietFoodSQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


public class DietFoodFragment extends Fragment {
    DietFoodSQLiteOpenHelper myDB;
    ListView listView;
    ArrayList<DietFoodItem> dietFoods;
    public DietFoodFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_diet_food, container, false);
        listView = (ListView)view.findViewById(R.id.listView);

        ArrayList<Integer> img = new ArrayList<>();
        img.add(R.drawable.ic_chickenbreast);
        img.add(R.drawable.ic_hub);
        img.add(R.drawable.ic_tofu);
        img.add(R.drawable.ic_egg);
        img.add(R.drawable.ic_apple);
        img.add(R.drawable.ic_avocado);
        img.add(R.drawable.ic_banana);
        img.add(R.drawable.ic_chocolate);
        img.add(R.drawable.ic_beansproutsbibimnoodles);
        img.add(R.drawable.ic_sweetpumkinsalad);

        myDB = new DietFoodSQLiteOpenHelper(getActivity(), "DietFood", null, 12);
        myDB.updateDietFoodImage(img);

        // listView 아이템 클릭 이벤트
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity().getApplicationContext(), DietFood2Activity.class);
                intent.putExtra("id", dietFoods.get(position).getId());
                startActivity(intent);
            }
        });

        // 아이템 추가 및 어댑터 등록
        dataSetting();

        return view;
    }

    // 리스트뷰에 어댑터를 지정하기 전에 어댑터는 리스트뷰에서 쓸 데이터를 미리 갖고 있어야 한다.
    private void dataSetting() {
        DietFoodAdapter dietFoodAdapter = new DietFoodAdapter(); // 리스트뷰의 어댑터 지정

        int count = myDB.getCount(); // table의 레코드 개수
        dietFoods = myDB.getDietFoods(); // 필독사항 글의 제목, 내용을 ArrayList로 저장

        // 데이터베이스에서 가져온 다이어트푸드 정보를 어댑터를 통해서 리스트 뷰에 추가
        for(int i=0; i<count; ++i){
            // diet_food_TB의 레코드 수만큼 반복문을 돌려서 아이템을 등록한다.
            // dietFoods ArrayList객체의 i번째 객체를 가져와서 인스턴스의 값을 뽑는다.
            dietFoodAdapter.addItem(dietFoods.get(i).getId(), dietFoods.get(i).getFoodName(), dietFoods.get(i).getFoodKcal(), dietFoods.get(i).getImage_path());
        }

        // listView의 어탭터를 dietFoodAdapter로 등록
        listView.setAdapter(dietFoodAdapter);
    }

}
