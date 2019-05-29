package com.pizza.app.web;


import com.pizza.app.dao.PizzaDAO;
import com.pizza.app.entity.Pizza;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    private static final String REDIRECT_INDEX = "redirect:/pizza/";

    @Autowired
    private PizzaDAO pizzaDAO;

    @GetMapping({"/", "/{id}"})
    public String getPizza(@PathVariable(required = false) Integer id, Model model) {
        model.addAttribute("pizzas", pizzaDAO.get());
        model.addAttribute("pizza", id != null ? pizzaDAO.get(id) : new Pizza());
        return INDEX;
    }

    @PostMapping("/")
    public ModelAndView savePizza(Pizza pizza) {
        pizzaDAO.add(pizza);
        return new ModelAndView(REDIRECT_INDEX);
    }

    @GetMapping("delete/{id}")
    public ModelAndView deletePizza(@PathVariable int id) {
        LOGGER.info("Deleting pizza: {}", id);
        pizzaDAO.delete(id);
        return new ModelAndView(REDIRECT_INDEX);
    }

}
