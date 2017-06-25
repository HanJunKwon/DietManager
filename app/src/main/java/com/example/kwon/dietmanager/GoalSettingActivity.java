package com.example.kwon.dietmanager;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.icu.util.GregorianCalendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

public class GoalSettingActivity extends AppCompatActivity {
    Button btnGoalSettingComplete;
    EditText editStartDate, editEndDate, editGoalWeight, editPromise;
    SQLiteDatabase myDB;

    // Start 액티비티에서 넘겨준 데이터를 저장하는 변수들
    String name,weight; // 사용자 이름, 몸무게
    int age, height; // 나이, 신장
    short gender, activiteMass; // 성별, 활동량

    // GoalSetting액티비티에서 발생하는 데이터를 저장하는 변수들
    int goalWeight;
    int year, month, day; // 현재 날짜 저장
    int startYear, startMonth, startDay; // 시작 날짜 저장
    int endYear, endMonth, endDay; // 목표 날짜 저장
    String startDate, endDate, promise; // DB에 실질적으로 들어가는 값
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal_setting);
        setTitle("목표설정");

        // start액티비티에서 전달받은 데이터들
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        age = intent.getExtras().getInt("age"); // int형 전달 받을 때
        weight = intent.getExtras().getString("weight");
        height = intent.getExtras().getInt("height");

        editGoalWeight = (EditText)findViewById(R.id.editGoalWeight);
        editPromise = (EditText)findViewById(R.id.editPromise);

        btnGoalSettingComplete = (Button) findViewById(R.id.btnGoalSettingComplete);
        btnGoalSettingComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 목표 설정 데이터를 모두 입력하지 않은 경우
                if(editStartDate.getText().toString().length() == 0 ||
                        editGoalWeight.getText().toString().length() == 0 ||
                        editEndDate.getText().toString().length() == 0 ||
                        editPromise.getText().toString().length() == 0){
                    Toast.makeText(GoalSettingActivity.this, "목표 설정을 모두 입력해야 합니다.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // 시작일과 종료일 비교
                try{
                    Date start = dateFormat.parse(startDate);
                    Date end = dateFormat.parse(endDate);
                    if(start.after(end)) // 시작일이 종료일보다 이후 일때 true, 아니면 false
                    {
                        Toast.makeText(GoalSettingActivity.this, "시작일이 종료일 이후로 설정되어 있습니다.", Toast.LENGTH_LONG).show();
                        return;
                    }
                }catch(Exception e){
                    Toast.makeText(GoalSettingActivity.this, ""+e, Toast.LENGTH_SHORT).show();
                    return;
                }

                goalWeight = Integer.parseInt(editGoalWeight.getText().toString());
                promise = editPromise.getText().toString();


                myDB = openOrCreateDatabase("DietManager", MODE_PRIVATE, null);

                // 앱에서 사용자가 이용할 테이블들을 생성
                myDB.execSQL("Create table userInfo ("+
                        "name text primary key, "+"age integer, "+"weight text, "+
                        "height integer, "+"gender integer, "+"activiteMass integer, "+
                        "goalWeight integer, "+"startDate text, "+"endDate text, "+"promise text);");

                myDB.execSQL("Insert into userInfo (name, age, weight, height, gender, activiteMass, goalWeight, startDate, endDate, promise) values ('"+
                        name + "', '" + age + "', '" + weight+"', '" + height +"', '"+gender+"', '"+activiteMass+"', '"+goalWeight+"', '"+startDate+"', '"+endDate+"', '"+promise+"');");

                myDB.execSQL("Create table dailyRecord(id integer primary key autoincrement, weight text not null, date text not null);");
                myDB.execSQL("Insert into dailyRecord(weight, date) values('"+
                        weight+"', '"+startDate+"');");

                myDB.execSQL("Create table dailyExerciseRecord ("+
                        "id integer, exerciseName text, sets integer, number integer, " +
                        "FOREIGN KEY(id) REFERENCES dailyRecord(id));");

                Intent NavigationIntent = new Intent(GoalSettingActivity.this, NavigationActivity.class);
                startActivity(NavigationIntent);

                if(myDB != null) myDB.close();
            }
        });

        // 기본 년월일을 지정해주기 위해 시스템에서 년월일을 받아온다.
        GregorianCalendar calendar = new GregorianCalendar();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        // 데이트피커 객체 생성 및 기본 년월일 설정
        final DatePickerDialog startDialog = new DatePickerDialog(GoalSettingActivity.this, startDateSetListener, year, month, day);
        final DatePickerDialog endDialog = new DatePickerDialog(GoalSettingActivity.this, endDateSetListener, year, month, day);

        editStartDate = (EditText) findViewById(R.id.editStartDate);
        // 시작일 EditText 터치 시 데이트피커 화면에 보여주는 이벤트
        editStartDate.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                startDialog.show();
                return false;
            }
        });

        // 종료일 EditText 터치 시 데이트피커 화면에 보여주는 이벤트
        editEndDate = (EditText)findViewById(R.id.editEndDate);
        editEndDate.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                endDialog.show();
                return false;
            }
        });

    }

    // 시작일 날짜 선택 시 발생하는 함수
    private DatePickerDialog.OnDateSetListener startDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            startYear = year;
            startMonth = month+1;
            startDay = dayOfMonth;
            startDate = startYear+"-"+startMonth+"-"+startDay;
            editStartDate.setText(startYear+"년 "+startMonth+"월 "+startDay+"일 ");
        }
    };

    // 종료일 날짜 선택 시 발생하는 함수
    private DatePickerDialog.OnDateSetListener endDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            endYear = year;
            endMonth = month + 1;
            endDay = dayOfMonth;
            endDate = endYear+"-"+endMonth+"-"+endDay;
            editEndDate.setText(endYear+"년 "+endMonth+"월 "+endDay+"일");
        }
    };
}
