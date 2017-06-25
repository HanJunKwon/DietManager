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
        db.execSQL("Insert into diet_food_TB(food_NM, food_kcal, meterial, image_path, efficacy) values('닭가슴살 샐러드', '556kcal(1인분)', '닭가슴살 1쪽, 겨자잎 5장, 양상추 1/4개', 0, '운동 중에 손실되는 단백질을 보충해주는 음식으로 탄수화물과 지방이 적어서 국민 다이어트 음식이다.');");
        db.execSQL("Insert into diet_food_TB(food_NM, food_kcal, meterial, image_path, efficacy) values('녹차', '2.8kcal(1인분)', '녹차 티백 1팩', 0, '지방을 분해해 준다');");
        db.execSQL("Insert into diet_food_TB(food_NM, food_kcal, meterial, image_path, efficacy) values('두부', '79kcal(1모)', '두부 1모', 0, '단백질을 많이 포함하고 있어 손실되 근육의 회복을 빠르게 해주며 포만감을 높여주기 때문에 좋다');");
        db.execSQL("Insert into diet_food_TB(food_NM, food_kcal, meterial, image_path, efficacy) values('삶은 계란', '80kcal(1개)', '계란 3~4개, 소금 1큰술, 식초 1큰술', 0, '쉽게 구할 수 있는 다이어트 음식으로 운동 후 단백질 보충용으로 많이 먹는다.');");
        db.execSQL("Insert into diet_food_TB(food_NM, food_kcal, meterial, image_path, efficacy) values('사과', '57kcal(100g)', '사과 1개', 0, '포만감을 높여주지만 당을 많이 포함하고 있어 많이 먹게되면 지방이 축적이 된다.');");
        db.execSQL("Insert into diet_food_TB(food_NM, food_kcal, meterial, image_path, efficacy) values('아보카도', '191kcal(100g)', '아보카도 1개', 0, '아보카동ㅇ');");
        db.execSQL("Insert into diet_food_TB(food_NM, food_kcal, meterial, image_path, efficacy) values('바나나', '92kcal(100g)', '바나나 2개', 0, '포만감을 높여주고 칼로리가 적은 과일로 아침에 두부와 같이 먹으면 효과를 볼 수 있다.');");
        db.execSQL("Insert into diet_food_TB(food_NM, food_kcal, meterial, image_path, efficacy) values('다크초콜릿', '550kcal(100g)', '다크 초콜릿 40~50g', 0, '초콜릿이 살을 찌운다고 생각하는 사람들이 많지만 초콜릿의 당은 밥을 먹어서 나오는 포도당과는 다르게" +
                "지방으로 축적이 잘 안 되기 때문에 운동을 하고 난 후 당이 떨어질때 먹어주면 좋다.');");
        db.execSQL("Insert into diet_food_TB(food_NM, food_kcal, meterial, image_path, efficacy) values('콩나물 비빔국수', '104kcal(1인분)', '실곤약 200g, 콩나물 2줌(또는 숙주 100g), 참나물 1/2줌(또는 깻잎 5장, 25g), 달걀 1개(생략 가능)" +
                "맛술 1큰술, 식초 1큰술, 매실청(또는 올리고당) 1큰술, 고추장 1큰술, 고춧가루 1작은술, 다진 마늘 1/2작은술, 양조간장 1작은술, 참기록 1/2작은술', 0, '면 종류의 음식이 먹고 싶은데 칼로리 걱정을 한다면 콩나물 비빔국수를 먹어보는 것을 추천한다.');");
        db.execSQL("Insert into diet_food_TB(food_NM, food_kcal, meterial, image_path, efficacy) values('단호박 샐러드', '364kcal(1인분)', '단호박 1/2개(500g), 아몬드(슬라이스 된 것) 3큰술(15g), 브로콜리 1/7개(30g), 새싹채소 약간," +
                "사과 1/2개(100g), 마요네즈 1큰술(10g), 머스터드 1/2큰술(5g), 떠먹는 플레인요구르트 1통(80g), 꿀 1큰술(10g), 소금 1/2작은술(2g)', 0, '호박도 대표적인 다이어트 음식 중 하나이고 낮은 칼로리와 포만감을 주어 특히 여성들에게 인기가 많다.');");

        // 음식별 제조 과정 저장
        db.execSQL("Create table food_making_TB(id INTEGER not null, step INTEGER not null, making text not null, FOREIGN KEY(id) REFERENCES diet_food_TB(id));");
        db.execSQL("Insert into food_making_TB(id, step, making) values(1, 1, '물(3컵)에 닭 삶는 재료를 넣고 끓기 시작하면 닭가슴살을 넣고 완전히 익힌 뒤 건져 식히고,');"); // 닭가슴살 샐러드
        db.execSQL("Insert into food_making_TB(id, step, making) values(1, 2, '겨자잎과 양상추는 한입 크기로 뜯어 물에 헹궈 물기를 빼고, 오렌지는 과육만 발라두고,');");
        db.execSQL("Insert into food_making_TB(id, step, making) values(1, 3, '과일드레싱 재료를 믹서에 갈고,');");
        db.execSQL("Insert into food_making_TB(id, step, making) values(1, 4, '닭가슴살을 잘게 찢어 소금(약간), 후춧가루(약간)로 간하고,');");
        db.execSQL("Insert into food_making_TB(id, step, making) values(1, 5, '채소와 오렌지에 닭가슴살을 얹고 과일드레싱을 뿌려 마무리.');");
        // 녹차
        db.execSQL("Insert into food_making_TB(id, step, making) values(2, 1, '물을 끓이고 70~80°C의 물에서 티백을 2~3분 정도 우려준다.');");
        // 두부
        db.execSQL("Insert into food_making_TB(id, step, making) values(3, 1, '두부가 잠길 정도로 물을 넣고 끓여주고, 두부를 2cm 정도 두께로 썰어준다');");
        db.execSQL("Insert into food_making_TB(id, step, making) values(3, 2, '물이 끓기 시작하면 두부를 넣고 2분정도 끓여준다.');");
        // 삶은 계란
        db.execSQL("Insert into food_making_TB(id, step, making) values(4, 1, '계란이 물에 잠길 정도로 넣어서 끓여주고 기포가 올라 올때 쯤에 소금과 식초를 넣는다.');");
        db.execSQL("Insert into food_making_TB(id, step, making) values(4, 2, '물이 끓기 시작하면 깨지지 않도록 계란을 넣고 1분 정도 굴리듯이 젓는다.');");
        db.execSQL("Insert into food_making_TB(id, step, making) values(4, 3, '반숙은 계란을 넣은 후 7분, 완숙은 1~2분 추가하여 끓이면 된다.');");
        db.execSQL("Insert into food_making_TB(id, step, making) values(4, 4, '물을 잠길 정도로 넣어서 끓여주고 기포가 올라 올때 쯤에 소금과 식초를 넣는다.');");
        // 콩나물 비빔국수
        db.execSQL("Insert into food_making_TB(id, step, making) values(9, 1, '냄비에 달걀, 잠길 만큼의 물, 소금(1작은술)을 넣어 센불에서 끓어오르면 약한 불로 줄여 12분간 삶고 찬물에 담가 한 김 식힌 후 껍데기를 벗겨 2등분한다.');");
        db.execSQL("Insert into food_making_TB(id, step, making) values(9, 2, '다른 냄비에 콩나물 삶을 물(2컵) + 소금(1작은술)을 끓인다. 콩나물은 체에 밭쳐 흐르는 물에 씻은 후 물기를 뺸다.');");
        db.execSQL("Insert into food_making_TB(id, step, making) values(9, 3, '참나물은 지저분한 잎을 떼어내고 흐르는 물에 씻은 후 체에 밭쳐 물기를 빼고 2cm 길이로 썬다.');");
        db.execSQL("Insert into food_making_TB(id, step, making) values(9, 4, '②의 냄비에 콩나물을 넣고 뚜껑을 덮어 4분간 삶은 후 체에 밭쳐 물기를 뺀다. 냄비에 다시 물(4컵)을 끓인다.');");
        db.execSQL("Insert into food_making_TB(id, step, making) values(9, 5, '볼에 양념 재료를 넣고 섞는다.');");
        db.execSQL("Insert into food_making_TB(id, step, making) values(9, 6, '④의 냄비에 실곤약을 넣고 1분간 데친다.');");
        db.execSQL("Insert into food_making_TB(id, step, making) values(9, 7, '실곤약은 체에 밭쳐 찬물에 헹궈 물기를 꼭 짠다.');");
        db.execSQL("Insert into food_making_TB(id, step, making) values(9, 8, '그릇에 모든 재료를 담고 양념을 뿌린다.');");
        // 단호박 샐러드
        db.execSQL("Insert into food_making_TB(id, step, making) values(10, 1, '단호박은 껍질을 벗기고 숟가락으로 씨와 섬유질을 긁어내 적당한 크기로 썬다. 김이 오른 찜기에 넣고 익힌다.');");
        db.execSQL("Insert into food_making_TB(id, step, making) values(10, 2, '브로콜리는 깨끗이 씻은 후 끓는 소금물에 넣고 데친다. 찬물에 헹궈 물기를 제거하고 굵게 다진다. 사과는 씨와 껍질을 제거하고 굵게 다진다. 새싹채소는" +
                "체에 밭쳐 흐르는 물에 씻어 물기를 뺀다.');");
        db.execSQL("Insert into food_making_TB(id, step, making) values(10, 3, '불에 단호박을 넣고 잘 으깬다. 마요네즈, 머스터드, 떠먹는 플레인요구르트, 꿀, 소금을 넣고 골고루 섞는다.');");
        db.execSQL("Insert into food_making_TB(id, step, making) values(10, 4, '브로콜리와 사과, 아몬드를 넣고 골고루 섞는다. 그릇에 담고 새싹채소를 올린다.');");
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
