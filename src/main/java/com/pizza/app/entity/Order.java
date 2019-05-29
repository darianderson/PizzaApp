package com.pizza.app.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Order {

    private int id;
    private int status;

    private Pizza pizza;
    private Drink drink;
    private User user;

    public String getProductName() {
        return pizza == null ? drink.getName() : pizza.getInfo();
    }

    public void setUsername(String username) {
        this.user = new User();
        this.user.setUsername(username);
    }

//    private int idCar;
//    private int idCourier;
//    private Timestamp datetime;
//    private int idSender;
}
