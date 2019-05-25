package com.pizza.app.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@ToString
@EqualsAndHashCode
public class Pizza {
    private int idPizza;
    private String info;
    private int size;
    private int price;
}
