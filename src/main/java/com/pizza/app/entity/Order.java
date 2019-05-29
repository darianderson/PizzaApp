package com.pizza.app.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Order {
    private int id;
    //    private int idSender;
    private String idClient;
//        private Timestamp datetime;
//    private int idCourier;
//    private int idCar;

    private int idPizza;

}
