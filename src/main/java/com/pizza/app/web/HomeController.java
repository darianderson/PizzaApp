package com.pizza.app.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller("/")
public class HomeController {

    @GetMapping("/index")
    public ModelAndView getHomePage(){
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("username", "hahahahahahahhahahaa");
        return modelAndView;
    }

}
