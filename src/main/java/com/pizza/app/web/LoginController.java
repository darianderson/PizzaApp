package com.pizza.app.web;


import com.pizza.app.dao.impl.UserDAOImpl;
import com.pizza.app.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @Autowired
    private UserDAOImpl userDAO;

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("user", new User());

        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("registration") User userForm) {
        userDAO.add(userForm);
        return "redirect:/pizza/";
    }
    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
