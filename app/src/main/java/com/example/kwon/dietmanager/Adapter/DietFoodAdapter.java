package com.example.kwon.dietmanager.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kwon.dietmanager.Item.DietFoodItem;
import com.example.kwon.dietmanager.R;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by god on 2017-06-24.
 */

// 어댑터에서는 리스트뷰의 이벤트와 사용할 데이터들을 갖고 있다가 이 어댑터를 등록하면 해당 데이터와 이벤트를 리스트뷰가 갖게 된다.
public class DietFoodAdapter extends BaseAdapter{
    TextView txtFoodName, txtKcal;
    ImageView imgFood;
    ArrayList<DietFoodItem> dietFoodItems = new ArrayList<>();

    @Override
    // 화면에 표시해야하는 데이터의 갯수 반환
    public int getCount() {
        return dietFoodItems.size();
    }

    @Override
    // 인자로 받은 위치의 데이터 반환
    public DietFoodItem getItem(int position) {
        return dietFoodItems.get(position);
    }

    @Override
    // 인자로 받은 위치의 데이터 id 구분자 반환
    public long getItemId(int position) {
        return position;
    }

    @Override
    // 화면에 표시될 뷰 반환
    // 여기서는 리스트뷰 하나의 Row에 대해서 설정하는 곳이다.
    // ListView안의 뷰에 대해서 설정하고 이벤트를 설정한다.
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.v("알림","getView 시작");
        Context context = parent.getContext();

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.diet_food_listview_custom, parent, false);
        }

        txtFoodName = (TextView)convertView.findViewById(R.id.txtFoodName);
        txtKcal = (TextView)convertView.findViewById(R.id.txtKcal);
        imgFood = (ImageView)convertView.findViewById(R.id.imageFood);

        // position은 몇 번 째 row인지 알려주기 위한 인자이다.
        // getItem은 position에 position의 row에 해당하는 DietFoodItem을 ArrayList에서 DietFood객체를 반환한다
        DietFoodItem item = getItem(position);
        txtFoodName.setText(item.getFoodName().toString());
        txtKcal.setText("칼로리 : "+item.getFoodKcal().toString());
        imgFood.setImageResource(item.getImage_path());
        /////////////////////////////불러오기
//        String path = Environment.getExternalStorageDirectory().getAbsolutePath()+"/"+item.getImage_path().toString();
//        //Environment.getExternalStorageDirectory().getAbsolutePath()는 SD카드의 절대경로를 구하는 매소드
//        //path에 불러올 비트맵파일의 주소명을 초기화 시켜준다.
//        //주소 불러
//        Log.d("TAG", path);
//        BitmapFactory.Options bo = new BitmapFactory.Options();
//        bo.inSampleSize = 2;
//        Bitmap bmp = BitmapFactory.decodeFile(path, bo);
//        //저장되있던 비트맵 불러왔음
//        ImageView imageView = (ImageView)convertView.findViewById(R.id.imageFood);
//        imageView.setImageBitmap(bmp);
//        //불러온 비트맵을 이미지뷰에 셋팅

        Log.v("알림","getView 마침");
        return convertView;
    }

    // listView에 하나의 리스트의 내용을 추가할때 쓴다.
    public void addItem(int id, String foodName, String foodKcal, int imagePath){
        DietFoodItem item = new DietFoodItem();

        item.setId(id);
        item.setFoodName(foodName);
        item.setFoodKcal(foodKcal);
        item.setImage_path(imagePath);

        dietFoodItems.add(item);
    }
}
