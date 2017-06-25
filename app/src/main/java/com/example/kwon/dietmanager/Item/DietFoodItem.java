package com.example.kwon.dietmanager.Item;

/**
 * Created by god on 2017-06-24.
 */

public class DietFoodItem {
    int id;
    String foodName;
    String foodKcal;
    String meterial;
    int image_path;
    String efficacy;

    public DietFoodItem(){
        id = 0;
        foodName = null;
        foodKcal = null;
        meterial = null;
        image_path = 0;
        efficacy = null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFoodKcal() {
        return foodKcal;
    }

    public void setFoodKcal(String foodKcal) {
        this.foodKcal = foodKcal;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public int getImage_path() {
        return image_path;
    }

    public void setImage_path(int image_path) {
        this.image_path = image_path;
    }

    public String getMeterial() {
        return meterial;
    }

    public void setMeterial(String meterial) {
        this.meterial = meterial;
    }

    public String getEfficacy() {
        return efficacy;
    }

    public void setEfficacy(String efficacy) {
        this.efficacy = efficacy;
    }
}
