package com.example.kwon.dietmanager;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class StartActivity extends AppCompatActivity {
    String name;
    int age;
    int weight;
    int height;
    short gender; // 남자=0, 여자=1
    short activiteMass; // 매우 약간=0, 약간=1, 중간=2, 많이=3
    SQLiteDatabase myDB;

    EditText editName, editAge, editWeigth, editHeight;
    RadioGroup rdgGender, rdgActiviteMass;
    Button btnRegist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        editName = (EditText)findViewById(R.id.editName);
        editAge = (EditText)findViewById(R.id.editAge);
        editWeigth = (EditText)findViewById(R.id.editWeight);
        editHeight = (EditText)findViewById(R.id.editHeight);
        rdgGender = (RadioGroup)findViewById(R.id.rdgGender);
        rdgGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if(checkedId==R.id.rdMan){
                    gender = 0;
                }
                else {
                    gender = 1;
                }
            }
        });

        rdgActiviteMass = (RadioGroup)findViewById(R.id.rdgActiviteMass);
        rdgActiviteMass.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if(checkedId == R.id.rdVeryLittle)
                    activiteMass = 0;
                else if(checkedId == R.id.rdLittle)
                    activiteMass = 1;
                else if(checkedId == R.id.rdMiddle)
                    activiteMass = 2;
                else if(checkedId == R.id.rdALotOf)
                    activiteMass = 3;
            }
        });

        // 데이터베이스 열기
        // Info 데이터베이스에는 userInfo테이블, goalInfo 테이블, dailyInfo 테이블이 있다.
        // myDB = openOrCreateDatabase("Info", MODE_PRIVATE, null);

        this.setTitle("정보입력");
        btnRegist = (Button)findViewById(R.id.btnRegist);
        btnRegist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 사용자가 정보입력에 데이터를 입력하지 않은 경우 경고문구를 출력
                if(editName.getText().toString().length() == 0 ||
                        editAge.getText().toString().length() == 0 ||
                        editWeigth.getText().toString().length() == 0 ||
                        editHeight.getText().toString().length() == 0) {
                    Toast.makeText(StartActivity.this, "정보를 모두 입력해야합니다.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // GoalSetting 액티비티로 값을 전달하기 위해서 사용자가 입력한 값을 가져온다.
                name = editName.getText().toString();
                age = Integer.parseInt(editAge.getText().toString());
                weight = Integer.parseInt(editWeigth.getText().toString());
                height = Integer.parseInt(editHeight.getText().toString());

                // 사용자가 입력한 정보를 GoalSetting 액티비티로 전달한다.
                Intent goalSettingIntent = new Intent(StartActivity.this, GoalSettingActivity.class);
                goalSettingIntent.putExtra("name", name);
                goalSettingIntent.putExtra("age", age);
                goalSettingIntent.putExtra("weight", weight);
                goalSettingIntent.putExtra("height", height);
                startActivity(goalSettingIntent);
            }
        });
    }
}
