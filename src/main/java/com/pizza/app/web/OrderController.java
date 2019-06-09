package com.pizza.app.web;

import com.pizza.app.dao.OrderDAO;
import com.pizza.app.entity.Drink;
import com.pizza.app.entity.Order;
import com.pizza.app.entity.Pizza;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Controller
@RequestMapping("order/")
public class OrderController {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

    private static final String ORDER = "order";
    private static final String REDIRECT_ORDER = "redirect:/order/"; //  redirect:/pizza/

    @Autowired
    private OrderDAO orderDAO;

    @GetMapping("/")
    public String getOrder(@PathVariable(required = false) Integer id, Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails)principal).getUsername();
        boolean isAdmin = ((UserDetails)principal).getAuthorities().stream().
                anyMatch((Predicate<GrantedAuthority>) grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"));
        List<Order> orders = orderDAO.get();
        if (!isAdmin) {
            orders = orders.stream().filter(order -> username.equals(order.getUser().getUsername()))
                    .collect(Collectors.toList());
        }
        model.addAttribute("orders", orders);
        model.addAttribute("order", id != null ? orderDAO.get(id) : new Order());
        return ORDER;
    }

    @GetMapping("/{id}")
    public ModelAndView approve(@PathVariable Integer id) {
        Order order = orderDAO.get(id);
        order.setStatus(1);
        orderDAO.add(order);
        return new ModelAndView(REDIRECT_ORDER);
    }

    @GetMapping("/create")
    public ModelAndView createOrder(String type, int productId) {
        Order order = new Order();
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        order.setUsername(user.getUsername());
        if (Drink.TYPE.equals(type)) {
            Drink drink = new Drink();
            drink.setId(productId);
            order.setDrink(drink);
        } else if (Pizza.TYPE.equals(type)) {
            Pizza pizza = new Pizza();
            pizza.setId(productId);
            order.setPizza(pizza);
        }
        orderDAO.add(order);

        return new ModelAndView(REDIRECT_ORDER);
    }

    @GetMapping("delete/{id}")
    public ModelAndView deleteOrder(@PathVariable int id) {
        LOGGER.info("Deleting order: {}", id);
        orderDAO.delete(id);
        return new ModelAndView(REDIRECT_ORDER);
    }
}
