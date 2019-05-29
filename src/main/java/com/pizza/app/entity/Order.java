package com.pizza.app.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Order {

    private int id;
    private int status;

    private Pizza pizza;
    private User user;

//    private int idCar;
//    private int idCourier;
//    private Timestamp datetime;
//    private int idSender;
}
