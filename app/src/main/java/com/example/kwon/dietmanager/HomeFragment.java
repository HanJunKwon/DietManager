package com.example.kwon.dietmanager;

import android.content.Context;
import android.graphics.Color;
import android.icu.text.SimpleDateFormat;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.example.kwon.dietmanager.Item.DailyReccordItem;
import com.example.kwon.dietmanager.Item.UserInfoItem;
import com.example.kwon.dietmanager.SQLiteOpenHelper.HomeSQLiteOpenHelper;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class HomeFragment extends Fragment {
    HomeSQLiteOpenHelper myDB;
    UserInfoItem userInfoItem;
    TextView txtProgress,txtKcalInfomation;
    ImageView imageView;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        myDB = new HomeSQLiteOpenHelper(getActivity(), "DietManager", null, 1);
        userInfoItem = myDB.getUserInfo(); // 유저 정보는 가져온다.
        ArrayList<DailyReccordItem> dailyReccordItems = myDB.getDailyRecordItems();



        // 그래프 등록
        // 그래프 그리는 참고 url: http://namgh.blogspot.kr/2016/05/mpandroidchart-1.html
        LineChart chart = (LineChart)view.findViewById(R.id.chart);

        // 그려질 그래프의 값을 넣는다. Entry가 연결되면서 라인이 그려진다.
        ArrayList<Entry> goalWeightEntry = new ArrayList<Entry>();
        ArrayList<Entry> startWeightEntry = new ArrayList<Entry>();
        ArrayList<Entry> weightEntry = new ArrayList<Entry>();

        // 데이터 베이스 연결해서 사용자가 입력한 몸무게의 개수

        // 최대 15일의 몸무게를 받기 위해
        int s =0;
        int count = dailyReccordItems.size();
        if(count > 15){
            s = count-15;
        }
        // 그래프에 사용자의 15일간 몸무게 변화를 추가
        float startWeight = Float.parseFloat(userInfoItem.getWeight());
        float goalWeight = userInfoItem.getGoalWeight();
        float weight;
        for(int i=s; i<count; ++i){
            startWeightEntry.add(new Entry(startWeight, i-s)); // 변화가 없는 값이다.
            goalWeightEntry.add(new Entry(goalWeight, i-s)); // 변화가 없는 값이다.
            weight = Float.parseFloat(dailyReccordItems.get(i).getWeight());
            weightEntry.add(new Entry(weight,i-s));
        }


        ArrayList<ILineDataSet> lines = new ArrayList<>(); // 멀티 라인을 그리기 위함

        LineDataSet lineDataSet = new LineDataSet(startWeightEntry, "시작 몸무게");
        lineDataSet.setColor(Color.parseColor("#DD0000"));
        lineDataSet.setCircleColor(Color.parseColor("#DD0000"));
        lines.add(lineDataSet);
        LineDataSet lineDataSet1 = new LineDataSet(goalWeightEntry, "목표 몸무게");
        lineDataSet1.setColor(Color.parseColor("#0000FF"));
        lineDataSet1.setCircleColor(Color.parseColor("#0000FF"));
        lines.add(lineDataSet1);
        LineDataSet lineDataSet2 = new LineDataSet(weightEntry, "몸무게 변화");
        lineDataSet2.setColor(Color.parseColor("#00FF00"));
        lineDataSet2.setCircleColor(Color.parseColor("#00FF00"));
        lines.add(lineDataSet2);


        // X축의 이름을 지정해준다.
        ArrayList<String> xVals = new ArrayList<String>();
        for(int i=s; i<count; ++i){
            String[] str = dailyReccordItems.get(i).getDate().split("-");
            String date = str[1]+"-"+str[2];
            xVals.add(date);
        }

        String[] xAxis = xVals.toArray(new String[xVals.size()]);

        // x축과 앞에서 설정한 데이터를 라인으로 그린다.
        chart.setData(new LineData(xAxis, lines));
        chart.invalidate();

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        int percent = 0;
        try{
            Date startDate = new Date();
            Date endDate = new Date();

            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            startDate = df.parse(userInfoItem.getStartDate()); // 시작일 가져옴
            SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
            endDate = df1.parse(userInfoItem.getEndDate()); // 종료일 가져옴

            Calendar startCal = Calendar.getInstance();
            Calendar endCal = Calendar.getInstance();

            startCal.setTime(startDate);
            endCal.setTime(endDate);

            long diffMillis = endCal.getTimeInMillis() - startCal.getTimeInMillis();
            int diff = (int)(diffMillis/(24*60*60*1000));
            Log.v("시작일과 종료일 차이", ""+diff);

            double perOfDay = 100.0/diff; // 일당 퍼센트
            Log.v("일당 퍼센트",""+perOfDay);

            long now = System.currentTimeMillis();

            Date date = new Date(now);

            SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
            String getTime = df2.format(date);
            date = df2.parse(getTime);

            Calendar nowCal = Calendar.getInstance();

            nowCal.setTime(date);

            diffMillis = nowCal.getTimeInMillis() - startCal.getTimeInMillis();
            diff = (int)(diffMillis/(24*60*60*1000));
            Log.v("현재 일과 시작날짜 차이", ""+diff);

            percent = (int)(diff*perOfDay);

        }catch (Exception e){
            Log.v("알림", "날짜 계산 에러");
        }

        ProgressBar progress = (ProgressBar)view.findViewById(R.id.progress);
        progress.setProgress(percent);
        txtProgress = (TextView)view.findViewById(R.id.txtProgress);
        txtProgress.setText("진행률 : "+String.valueOf(percent)+"%");

        imageView = (ImageView)view.findViewById(R.id.imageView2);
        txtKcalInfomation = (TextView)view.findViewById(R.id.txtKcalInfomation);
        double kcal;
        if(userInfoItem.getGender() == 0) {
            imageView.setImageResource(R.drawable.man);
            kcal = (5*userInfoItem.getHeight()) + (13.73 * Float.parseFloat(userInfoItem.getWeight())) - (6.8 * userInfoItem.getAge()) + 66;
        }
        else {
            imageView.setImageResource(R.drawable.woman);
            kcal = (1.85 * userInfoItem.getHeight())+ (9.59*Float.parseFloat(userInfoItem.getWeight()))-(4.7*userInfoItem.getAge())+655;
        }
        switch (userInfoItem.getActivityMass()){
            case 0:
                kcal*=1.2;
                break;
            case 1:
                kcal*=1.375;
                break;
            case 2:
                kcal*=1.55;
                break;
            case 3:
                kcal*=1.725;
                break;
        }
        txtKcalInfomation.setText(kcal+"Kcal");
        return view;
    }
}
