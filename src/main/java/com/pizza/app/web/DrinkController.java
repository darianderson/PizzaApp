package com.pizza.app.web;

import com.pizza.app.dao.DrinkDAO;
import com.pizza.app.entity.Drink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("drink/")
public class DrinkController {
    private static final String DRINK_VIEW = "drink";

    private static final String REDIRECT_DRINK = "redirect:/drink/";

    @Autowired
    DrinkDAO drinkDAO;

    @GetMapping({"/", "/{id}"})
    public String getPizza(@PathVariable(required = false) Integer id, Model model) {
        model.addAttribute("drinks", drinkDAO.get());
        model.addAttribute("drink", id != null ? drinkDAO.get(id) : new Drink());
        return DRINK_VIEW;
    }

    @PostMapping("/")
    public ModelAndView savePizza(Drink drink) {
        drinkDAO.add(drink);
        return new ModelAndView(REDIRECT_DRINK);
    }

    @GetMapping("delete/{id}")
    public ModelAndView deletePizza(@PathVariable int id) {
        drinkDAO.delete(id);
        return new ModelAndView(REDIRECT_DRINK);
    }

}
