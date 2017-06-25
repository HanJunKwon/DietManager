package com.example.kwon.dietmanager.SQLiteOpenHelper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.kwon.dietmanager.Item.DietFoodItem;

import java.util.ArrayList;

/**
 * Created by god on 2017-06-24.
 */

public class DietFoodSQLiteOpenHelper extends SQLiteOpenHelper{
    public DietFoodSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.v("알림", "DietFood onCreate()");

        // 다이어트 음식 리스트 저장
        db.execSQL("Create table diet_food_TB(id INTEGER PRIMARY KEY AUTOINCREMENT, food_NM text not null, food_kcal text not null, meterial text not null, image_path integer not null, efficacy text not null);");
        db.execSQL("Insert into diet_food_TB(food_NM, food_kcal, meterial, image_path, efficacy) values('닭가슴살 샐러드', '556kcal(1인분)', '닭가슴살(1쪽), 겨자잎(5장), 양상추(1/4개)', 0, '운동 중에 손실되는 단백질을 보충해주는 음식으로 탄수화물과 지방이 적어서 국민 다이어트 음식이다.');");
        db.execSQL("Insert into diet_food_TB(food_NM, food_kcal, meterial, image_path, efficacy) values('녹차', '2.8kcal(1인분)', '녹차 티백', 0, '지방을 분해해 준다');");
        db.execSQL("Insert into diet_food_TB(food_NM, food_kcal, meterial, image_path, efficacy) values('두부', '2.8kcal(1인분)', '두부 1모', 0, '단백질을 많이 포함하고 있어 손실되 근육의 회복을 빠르게 해주며 포만감을 높여주기 때문에 좋다');");
        db.execSQL("Insert into diet_food_TB(food_NM, food_kcal, meterial, image_path, efficacy) values('삶은 계란', '2.8kcal(1인분)', '녹차 티백', 0, '쉽게 구할 수 있는 다이어트 음식으로 운동 후 단백질 보충용으로 많이 먹는다.');");
        db.execSQL("Insert into diet_food_TB(food_NM, food_kcal, meterial, image_path, efficacy) values('사과', '2.8kcal(1인분)', '녹차 티백', 0, '포만감을 높여주지만 당을 많이 포함하고 있어 많이 먹게되면 지방이 축적이 된다.');");
        db.execSQL("Insert into diet_food_TB(food_NM, food_kcal, meterial, image_path, efficacy) values('아보카도', '2.8kcal(1인분)', '녹차 티백', 0, '아보카동ㅇ');");
        db.execSQL("Insert into diet_food_TB(food_NM, food_kcal, meterial, image_path, efficacy) values('바나나', '2.8kcal(1인분)', '녹차 티백', 0, '포만감을 높여주고 칼로리가 적은 과일로 아침에 두부와 같이 먹으면 효과를 볼 수 있다.');");
        db.execSQL("Insert into diet_food_TB(food_NM, food_kcal, meterial, image_path, efficacy) values('다크초콜릿', '2.8kcal(1인분)', '녹차 티백', 0, '초콜릿이 살을 찌운다고 생각하는 사람들이 많지만 초콜릿의 당은 밥을 먹어서 나오는 포도당과는 다르게" +
                "지방으로 축적이 잘 안 되기 때문에 운동을 하고 난 후 당이 떨어질때 먹어주면 좋다.');");
        db.execSQL("Insert into diet_food_TB(food_NM, food_kcal, meterial, image_path, efficacy) values('콩나물 비빔국수', '2.8kcal(1인분)', '녹차 티백', 0, '면 종류의 음식이 먹고 싶은데 칼로리 걱정을 한다면 콩나물 비빔국수를 먹어보는 것을 추천한다.');");
        db.execSQL("Insert into diet_food_TB(food_NM, food_kcal, meterial, image_path, efficacy) values('단호박 샐러드', '2.8kcal(1인분)', '녹차 티백', 0, '호박도 대표적인 다이어트 음식 중 하나이고 낮은 칼로리와 포만감을 주어 특히 여성들에게 인기가 많다.');");

        // 음식별 제조 과정 저장
        db.execSQL("Create table food_making_TB(id INTEGER not null, step INTEGER not null, making text not null, FOREIGN KEY(id) REFERENCES diet_food_TB(id));");
        db.execSQL("Insert into food_making_TB(id, step, making) values(1, 1, '물(3컵)에 닭 삶는 재료를 넣고 끓기 시작하면 닭가슴살을 넣고 완전히 익힌 뒤 건져 식히고,');");
        db.execSQL("Insert into food_making_TB(id, step, making) values(1, 2, '겨자잎과 양상추는 한입 크기로 뜯어 물에 헹궈 물기를 빼고, 오렌지는 과육만 발라두고,');");
        db.execSQL("Insert into food_making_TB(id, step, making) values(1, 3, '과일드레싱 재료를 믹서에 갈고,');");
        db.execSQL("Insert into food_making_TB(id, step, making) values(1, 4, '닭가슴살을 잘게 찢어 소금(약간), 후춧가루(약간)로 간하고,');");
        db.execSQL("Insert into food_making_TB(id, step, making) values(1, 5, '채소와 오렌지에 닭가슴살을 얹고 과일드레싱을 뿌려 마무리.');");
        db.execSQL("Insert into food_making_TB(id, step, making) values(2, 1, '물을 끓이고 70~80°C의 물에서 티백을 2~3분 정도 우려준다.');");
   }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion < newVersion){
            db.execSQL("DROP TABLE IF EXISTS diet_food_TB");
            db.execSQL("DROP TABLE IF EXISTS food_making_TB");
            onCreate(db);
        }
    }

    public int getCount(){
        int count=0;

        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("select count(*) from diet_food_TB", null);

        if(cursor!=null){
            cursor.moveToFirst();
            count = cursor.getInt(0);
        }
        return count;
    }

    public DietFoodItem getDietFood(int id){
        DietFoodItem item = new DietFoodItem();

        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("select food_NM, food_kcal, meterial, image_path, efficacy from diet_food_TB where id="+id+";",null);

        if(cursor!=null){
            cursor.moveToFirst();
            item.setFoodName(cursor.getString(0));
            item.setFoodKcal(cursor.getString(1));
            item.setMeterial(cursor.getString(2));
            item.setImage_path(cursor.getInt(3));
            item.setEfficacy(cursor.getString(4));
        }

        return item;
    }

    public ArrayList<String> getFoodMaking(int id){
        ArrayList<String> foodMaking = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("select making from food_making_TB where id="+id+";", null);

        if(cursor!=null){
            if(cursor.moveToFirst()){
                do{
                    foodMaking.add(cursor.getString(0));
                }while(cursor.moveToNext());
            }
        }

        return foodMaking;
    }

    public ArrayList<DietFoodItem> getDietFoods(){
        ArrayList<DietFoodItem> dietFoodItems = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("select id,food_NM,food_kcal,image_path from diet_food_TB", null);

        if(cursor!=null){
            if(cursor.moveToFirst()){
                do{
                    DietFoodItem item = new DietFoodItem(); // DietFoodItem 객체 생성

                    item.setId(cursor.getInt(0));
                    item.setFoodName(cursor.getString(1));
                    item.setFoodKcal(cursor.getString(2));
                    item.setImage_path(cursor.getInt(3));

                    dietFoodItems.add(item); // DietFoodItem객체를 ArrayList에 추가
                }while(cursor.moveToNext());
            }
        }
        return dietFoodItems;
    }

    public void updateDietFoodImage(ArrayList<Integer> arrayList){
        SQLiteDatabase db = getWritableDatabase();

        for(int i=0; i<arrayList.size(); ++i){
            db.execSQL("update diet_food_TB set image_path="+arrayList.get(i)+" where id="+(i+1)+";");
        }
    }
}
