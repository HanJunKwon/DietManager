package com.example.kwon.dietmanager;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // 그래프 등록
        // 그래프 그리는 참고 url: http://namgh.blogspot.kr/2016/05/mpandroidchart-1.html
        LineChart chart = (LineChart)view.findViewById(R.id.chart);

        // 사용자 몸무게를 저장
        ArrayList<Entry> userWeight = new ArrayList<Entry>();

        // 데이터 베이스 연결해서 사용자가 입력한 몸무게의 개수
        int count = 3; // 임시로 3이라고 지정해놨다

        // 최대 15일의 몸무게를 받기 위해
        if(count>15)
            count=15;


        // 그래프에 사용자의 15일간 몸무게 변화를 추가
        for(int i=0; i<count; ++i){
            userWeight.add(new Entry(90.4f, i));
        }

        // 라인에 대한 설정을 해주는 코드
        LineDataSet setComp = new LineDataSet(userWeight, "몸무게"); // userWeight는 사용자의 몸무게 라고는 것을 알려준다.
        setComp.setAxisDependency(YAxis.AxisDependency.LEFT); // 데이터를 그래프 좌측에 표시한다.
        ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
        dataSets.add(setComp);

        // X축의 이름을 지정해준다.
        ArrayList<String> xVals = new ArrayList<String>();
        xVals.add("6/7");
        xVals.add("6/8");
        xVals.add("6/9");

        // x축과 앞에서 설정한 데이터를 라인으로 그린다.
        LineData data = new LineData(xVals, dataSets);

        chart.setData(data); // 차트에 데이터 등록
        chart.invalidate();

        // gif를 사용하기 위해서 Glide라는 오픈 소스를 사용한다.
        // 추가적으로 Glide 는 OOM(Out Of Memory)를 방지해준다.
        ImageView running = (ImageView)view.findViewById(R.id.running);
        GlideDrawableImageViewTarget imageViewTarget = new GlideDrawableImageViewTarget(running);
        Glide.with(getActivity()).load(R.raw.running).into(imageViewTarget);

        ProgressBar progress = (ProgressBar)view.findViewById(R.id.progress);
        progress.setProgress(25);

        // Inflate the layout for this fragment
        return view;
    }
}