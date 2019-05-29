package com.pizza.app.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class User {
    private String username;
    private String email;
    private String firstName;
    private String secondName;
    private String phoneNumber;
    private String address;

    private Role[] roles;
}
