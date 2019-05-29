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
}
