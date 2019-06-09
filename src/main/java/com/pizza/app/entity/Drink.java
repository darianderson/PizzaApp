package com.pizza.app.entity;


import lombok.*;

@NoArgsConstructor
@Data
public class Drink {

    public static final String TYPE = "drink";

    private int id;
    private int price;
    private String name;

    public static String getTYPE() {
        return TYPE;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
