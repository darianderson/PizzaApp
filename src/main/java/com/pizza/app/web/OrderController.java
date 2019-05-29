package com.pizza.app.web;

import com.pizza.app.dao.OrderDAO;
import com.pizza.app.entity.Order;
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
@RequestMapping("order/")
public class OrderController {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

    private static final String INDEX = "index";
    private static final String REDIRECT_INDEX = "redirect:/order/"; //  redirect:/pizza/

    @Autowired
    private OrderDAO orderDAO;

    @GetMapping({"/", "/{id}"})
    public String getPizza(@PathVariable(required = false) Integer id, Model model) {
        model.addAttribute("orders", orderDAO.get());
        model.addAttribute("order", id != null ? orderDAO.get(id) : new Order());
        return INDEX;
    }

    @PostMapping("/")
    public ModelAndView savePizza(Order order) {
        orderDAO.add(order);
        return new ModelAndView(REDIRECT_INDEX);
    }

    @GetMapping("delete/{id}")
    public ModelAndView deletePizza(@PathVariable int id) {
        LOGGER.info("Deleting order: {}", id);
        orderDAO.delete(id);
        return new ModelAndView(REDIRECT_INDEX);
    }
}
