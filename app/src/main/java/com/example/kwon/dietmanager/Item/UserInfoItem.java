package com.example.kwon.dietmanager.Item;

import java.util.Date;

/**
 * Created by god on 2017-06-26.
 */

public class UserInfoItem {
    String name;
    int age;
    String weight;
    int height;
    int gender;
    int activityMass;
    int goalWeight;
    String startDate;
    String endDate;
    String promise;

    public UserInfoItem(){
        name =null;
        age =0; weight=null; height=0; gender=0; activityMass=0; goalWeight=0;
        startDate=null; endDate=null;
        promise =null;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getWeight() {
        return weight;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getHeight() {
        return height;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getGender() {
        return gender;
    }

    public void setActivityMass(int activityMass) {
        this.activityMass = activityMass;
    }

    public int getActivityMass() {
        return activityMass;
    }

    public void setGoalWeight(int goalWeight) {
        this.goalWeight = goalWeight;
    }

    public int getGoalWeight() {
        return goalWeight;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setPromise(String promise) {
        this.promise = promise;
    }

    public String getPromise() {
        return promise;
    }
}
