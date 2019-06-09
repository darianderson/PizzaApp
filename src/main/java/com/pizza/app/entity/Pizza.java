package com.pizza.app.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Pizza {

    public static final String TYPE = "pizza";

    private int id;
    private String info;
    private int size;
    private int price;

    public static String getTYPE() {
        return TYPE;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
