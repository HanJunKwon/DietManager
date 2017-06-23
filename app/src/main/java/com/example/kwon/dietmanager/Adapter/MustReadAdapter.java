package com.example.kwon.dietmanager.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.widget.TextViewCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.kwon.dietmanager.Item.MustReadItem;
import com.example.kwon.dietmanager.MustRead2Activity;
import com.example.kwon.dietmanager.MustReadFragment;
import com.example.kwon.dietmanager.R;
import java.util.ArrayList;

/**
 * Created by god on 2017-06-10.
 */

public class MustReadAdapter extends BaseAdapter{
    ArrayList<MustReadItem> items = new ArrayList<MustReadItem>();

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public MustReadItem getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.v("알림","getView 시작");
        Context context = parent.getContext();

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.must_read_listview_custom, parent, false);
        }

        TextView txtName = (TextView)convertView.findViewById(R.id.txtName);
        TextView txtContent = (TextView)convertView.findViewById(R.id.txtContents);

        final MustReadItem item = getItem(position);

        // 리스트 하나의 아이템에 대해서 설정
        txtName.setText(item.getTitle());

        txtContent.setText(item.getContents());


        // 여기 아래에 위젯에 넣고 싶은 위젯을 넣는다.


        Log.v("알림","getView 마침");
        return convertView;
    }

    public void addItem(int id, String title, String contents){
        MustReadItem item = new MustReadItem();

        item.setId(id);
        item.setTitle(title);
        item.setContents(contents);

        items.add(item);
        Log.v("알림", "아이템 추가 완료");
    }
}
