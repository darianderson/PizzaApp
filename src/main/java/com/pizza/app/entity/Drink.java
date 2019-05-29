package com.pizza.app.entity;


import lombok.*;

@NoArgsConstructor
@Data
public class Drink {

    public static final String TYPE = "drink";

    private int id;
    private int price;
    private String name;
}
