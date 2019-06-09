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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Pizza getPizza() {
        return pizza;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }

    public Drink getDrink() {
        return drink;
    }

    public void setDrink(Drink drink) {
        this.drink = drink;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
