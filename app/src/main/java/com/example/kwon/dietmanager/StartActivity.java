package com.example.kwon.dietmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartActivity extends AppCompatActivity {
    Button btnRegist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        this.setTitle("정보입력");
        btnRegist = (Button)findViewById(R.id.btnRegist);
        btnRegist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goalSettingIntent = new Intent(StartActivity.this, GoalSettingActivity.class);
                startActivity(goalSettingIntent);
            }
        });
    }
}
