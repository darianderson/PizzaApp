package com.pizza.app.entity;


import lombok.*;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter@Setter
@EqualsAndHashCode
public class Drink {
    private int id;
    private int price;
    private String name;
}
