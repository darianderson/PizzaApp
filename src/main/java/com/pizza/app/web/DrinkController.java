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

    @Autowired
    DrinkDAO drinkDao;

    @GetMapping("/")
    public ModelAndView getDrinkIndex() {
        ModelAndView modelAndView = new ModelAndView("index-drink");
        modelAndView.addObject("drinks", drinkDao.get());
        return modelAndView;
    }

    @GetMapping("add")
    public ModelAndView getAddPage() {
        ModelAndView modelAndView = new ModelAndView("add-drink");
        modelAndView.addObject("drink", new Drink());
        return modelAndView;
    }

    @PostMapping("add")
    public ModelAndView addDrink(Drink drink) {
        drinkDao.add(drink);
        return new ModelAndView("redirect:/drink/");
    }

    @GetMapping("/edit/{id}")
    public String getEditDrinkPage(@PathVariable("id") int id, Model model) {
        Drink drink = drinkDao.get(id);

        model.addAttribute("drink", drink);
        return "add-drink";
    }

    @PostMapping("/edit/{id}")
    public String updateDrink(Drink drink, Model model) {
        drinkDao.add(drink);
        return "redirect:/drink/";
    }

    @GetMapping("/delete/{id}")
    public String deleteDrink(@PathVariable("id") int id) {
        drinkDao.delete(id);
        return "redirect:/drink/";
    }
}
