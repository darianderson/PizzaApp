package com.pizza.app.web;


import com.pizza.app.dao.PizzaDAO;
import com.pizza.app.entity.Pizza;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("pizza/")
public class PizzaController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PizzaController.class);

    private static final String INDEX = "index";
    private static final String REDIRECT_INDEX = "redirect:/";


    @Autowired
    private PizzaDAO pizzaDAO;

    @GetMapping
    public ModelAndView getPizza() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails) principal).getUsername();

        ModelAndView modelAndView = new ModelAndView(INDEX);
        modelAndView.addObject("pizzas", pizzaDAO.get());

        modelAndView.addObject("username", username);
        return modelAndView;
    }

    @GetMapping("add")
    public ModelAndView getAddPizza() {
        LOGGER.info("Returning page for getting pizza.");
        ModelAndView modelAndView = new ModelAndView("add-pizza");
        modelAndView.addObject("pizza", new Pizza());
        return modelAndView;
    }

    @PostMapping("add")
    public ModelAndView addPizza(Pizza pizza) {
        LOGGER.info("Adding pizza: [{}]", pizza);
        pizzaDAO.add(pizza);
        return new ModelAndView(REDIRECT_INDEX);
    }

    @GetMapping("delete/{id}")
    public ModelAndView deletePizza(@PathVariable int id) {
        LOGGER.info("Deleting pizza: {}", id);
        pizzaDAO.delete(id);
        return new ModelAndView(REDIRECT_INDEX);
    }

    @GetMapping("/edit/{id}")
    public String getEditDrinkPage(@PathVariable("id") int id, Model model) {
        Pizza pizza = pizzaDAO.get(id);

        model.addAttribute("pizza", pizza);
        return "add-pizza";
    }

    @PostMapping("/edit/{id}")
    public String editPizza(Pizza drink, Model model) {
        pizzaDAO.add(drink);
        return REDIRECT_INDEX;
    }
}
