package com.webonise.assignment8.basicsqliteexample;

/**
 * Created by webonise on 13/8/15.
 */
public class FormDB  {

    public int id;
    public String Name;
    public int Age;
    public Double Weight;
    public Double Height;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public Double getWeight() {
        return Weight;
    }

    public void setWeight(Double weight) {
        Weight = weight;
    }

    public Double getHeight() {
        return Height;
    }

    public void setHeight(Double height) {
        Height = height;
    }
}
