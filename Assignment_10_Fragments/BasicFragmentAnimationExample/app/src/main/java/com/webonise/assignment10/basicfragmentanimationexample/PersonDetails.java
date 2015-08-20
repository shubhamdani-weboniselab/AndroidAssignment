package com.webonise.assignment10.basicfragmentanimationexample;

/**
 * Created by webonise on 14/8/15.
 */
public class PersonDetails {
    private int id;
    private String name;
    private int age;
    private double weight;
    private double height;

    public PersonDetails(int id, String name, int age, double weight, double height) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.height = height;
    }


    public PersonDetails() {
        this.id = Constants.ZERO;
        this.name = Constants.NO_DATA;
        this.age = Constants.ZERO;
        this.weight = Constants.FLOAT_ZERO;
        this.height = Constants.FLOAT_ZERO;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }


}
