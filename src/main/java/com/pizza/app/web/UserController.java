package com.pizza.app.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("user/")
public class UserController {

    @GetMapping("{username}")
    public ModelAndView user(@PathVariable String username) {
        // TODO create userview
        // TODO get user
        // TODO set it into model
        // TODO return it
        return null;
    }

}
