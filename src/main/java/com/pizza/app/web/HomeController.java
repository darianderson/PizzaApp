package com.pizza.app.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller("/")
public class HomeController {

    @GetMapping
    public ModelAndView home() {
        return new ModelAndView("redirect:/pizza");
    }


    @GetMapping("/login")
    public ModelAndView login(){
        return new ModelAndView("login-view");
    }


}
