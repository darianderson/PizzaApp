package com.pizza.app.web;

import com.pizza.app.dao.impl.UserDAOImpl;
import com.pizza.app.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Controller
@RequestMapping("user/")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private static final String USER_VIEW = "user";
    private static final String REDIRECT_INDEX = "redirect:/user/";

    @Autowired
    private UserDAOImpl userDAO;



    @GetMapping({"/", "/{username}"})
    public String getUser( Model model) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails)principal).getUsername();
        boolean isAdmin = ((UserDetails)principal).getAuthorities().stream().
                anyMatch((Predicate<GrantedAuthority>) grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"));
        List<User> users = userDAO.get();
        if (!isAdmin) {
            users = users.stream().filter(user -> user.getUsername().equals(username))
                    .collect(Collectors.toList());
        }
        model.addAttribute("users", users);
        model.addAttribute("user", username != null ? userDAO.get(username) : new User());
        return USER_VIEW;
    }

    @PostMapping("/")
    public ModelAndView saveUser(User user) {
        if (StringUtils.isNotBlank(user.getUsername()) && userDAO.get(user.getUsername()) != null)
            userDAO.add(user);
        return new ModelAndView(REDIRECT_INDEX);
    }

    @PostMapping("update/")
    public ModelAndView updateUser(User user) {
        userDAO.updateUser(user);
        return new ModelAndView(REDIRECT_INDEX);
    }


    @GetMapping("delete/{username}")
    public ModelAndView deleteUser(@PathVariable String username) {
        LOGGER.info("Deleting pizza: {}", username);
        userDAO.delete(username);
        return new ModelAndView(REDIRECT_INDEX);
    }



}
