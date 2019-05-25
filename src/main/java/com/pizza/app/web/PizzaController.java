package com.pizza.app.web;


import com.pizza.app.dao.PizzaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller("/pizza")
public class PizzaController {

    @Autowired
    private PizzaDAO pizzaDAO;

    @GetMapping("/")
    public ModelAndView getPizza() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails) principal).getUsername();
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("pizzas", pizzaDAO.get());
        modelAndView.addObject("username", username);
        return modelAndView;
    }

}
