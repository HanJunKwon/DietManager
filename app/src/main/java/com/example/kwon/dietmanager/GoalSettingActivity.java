package com.example.kwon.dietmanager;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.icu.util.GregorianCalendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

public class GoalSettingActivity extends AppCompatActivity {
    Button btnGoalSettingComplete;
    EditText editStartDate;

    int year, month, day;
    int startYear, startMonth, startDay;
    int endYear, endMonth, endDay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal_setting);
        setTitle("목표설정");

        btnGoalSettingComplete = (Button) findViewById(R.id.btnGoalSettingComplete);
        btnGoalSettingComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent DietIntent = new Intent(GoalSettingActivity.this, NavigationActivity.class);
                startActivity(DietIntent);
            }
        });

        // 기본 년월일을 지정해주기 위해 시스템에서 년월일을 받아온다.
        GregorianCalendar calendar = new GregorianCalendar();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        // 데이트피커 객체 생성 및 기본 년월일 설정
        final DatePickerDialog dialog = new DatePickerDialog(GoalSettingActivity.this, dateSetListener, year, month, day);

        editStartDate = (EditText) findViewById(R.id.editStartDate);
        // 시작일 EditText 터치 시 데이트피커 화면에 보여주는 이벤트
        editStartDate.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                dialog.show();
                return false;
            }
        });
    }

    // 데이트피커에서 날짜를 선택했을 경우 발생하는 함수
    private DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            startYear = year;
            startMonth = month+1;
            startDay = dayOfMonth;
            editStartDate.setText(startYear+"년 "+startMonth+"월 "+startDay+"일 ");
        }
    };
}
