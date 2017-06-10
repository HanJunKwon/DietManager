package com.example.kwon.dietmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnStart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart = (Button)findViewById(R.id.btnStart);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 데이터베이스에 데이터가 있다면 DietActivity를 보여준다

                // 데이터베이스에 데이터가 없다면 StartActivity를 보여준다.
                Intent startIntent = new Intent(MainActivity.this, StartActivity.class);
                startActivity(startIntent);
            }
        });
    }
}
