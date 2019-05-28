package com.pizza.app.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Pizza {
    private int id;
    private String info;
    private int size;
    private int price;
}
