package com.example.kwon.dietmanager.Item;

/**
 * Created by god on 2017-06-26.
 */

public class DailyReccordItem {
    int id;
    String weight;
    String Date;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getWeight() {
        return weight;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getDate() {
        return Date;
    }
}
