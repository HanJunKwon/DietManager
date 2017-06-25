package com.example.kwon.dietmanager;

import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.kwon.dietmanager.Item.DietFoodItem;
import com.example.kwon.dietmanager.SQLiteOpenHelper.DietFoodSQLiteOpenHelper;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class DietFood2Activity extends AppCompatActivity {
    DietFoodSQLiteOpenHelper myDB;
    LinearLayout parent;
    TextView txtFoodName, txtFoodKcal, txtMeterial, txtMaking;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_food2);
        Intent intent = getIntent();
        int id = intent.getExtras().getInt("id");
        myDB = new DietFoodSQLiteOpenHelper(this, "DietFood", null, 12);


        DietFoodItem dietFoodItem = myDB.getDietFood(id);
        imageView = (ImageView)findViewById(R.id.imageView);
        imageView.setImageResource(dietFoodItem.getImage_path());
        txtFoodName = (TextView)findViewById(R.id.txtFoodName);
        txtFoodName.setText(dietFoodItem.getFoodName());
        txtFoodKcal = (TextView)findViewById(R.id.txtFoodKcal);
        txtFoodKcal.setText(dietFoodItem.getFoodKcal());
        txtMeterial = (TextView)findViewById(R.id.txtMeterial);
        txtMeterial.setText(dietFoodItem.getMeterial());

        parent = (LinearLayout)findViewById(R.id.making);
        ArrayList<String> makingList = myDB.getFoodMaking(id);
        for(int i=0; i<makingList.size(); ++i){
            String str = makingList.get(i);
            TextView stepView = new TextView(this);
            stepView.setText("Step "+(i+1)+"!!\n");
            stepView.setTextSize(20);
            stepView.setTextColor(Color.parseColor("#AA1EAA"));
            parent.addView(stepView);
            TextView view = new TextView(this);
            view.setText(str);
            view.setTextSize(15);
            parent.addView(view);
        }
    }
}
