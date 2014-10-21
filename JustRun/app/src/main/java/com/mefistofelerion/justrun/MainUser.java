package com.mefistofelerion.justrun;

import java.util.GregorianCalendar;

/**
 * Created by Ivan on 10/20/14.
 */
public class MainUser {
    private String name;
    private int age;
    private float weight;
    private GregorianCalendar totalTimePlayed;
    private GregorianCalendar startTime;

    public MainUser(String name, int age){
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public GregorianCalendar getTotalTimePlayed() {
        return totalTimePlayed;
    }

    public void setTotalTimePlayed(GregorianCalendar totalTimePlayed) {
        this.totalTimePlayed = totalTimePlayed;
    }

    public GregorianCalendar getStartTime() {
        return startTime;
    }

    public void setStartTime(GregorianCalendar startTime) {
        this.startTime = startTime;
    }
}
