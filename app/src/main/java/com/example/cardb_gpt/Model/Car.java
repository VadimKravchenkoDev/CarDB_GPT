package com.example.cardb_gpt.Model;

public class Car {
    private int id;
    private String name;
    private String price;

    public Car(){
    }

    public Car(int id, String name, String price){
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Car(String name, String price){
        this.name = name;
        this.price = price;
    }

    public String getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
