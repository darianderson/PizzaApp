package com.pizza.app.web;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller("/pizza")
public class PizzaController {

    @GetMapping("/")
    public ModelAndView getPizza() {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("username", "test");
        return modelAndView;
    }

}
