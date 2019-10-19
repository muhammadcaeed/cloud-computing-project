package com.onlinekaufen.springframework.controller;

import com.onlinekaufen.springframework.dto.UserDTO;
import com.onlinekaufen.springframework.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Handles requests for the application Admin page.
 */
@Controller
public class AdminController {

    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    private final
    UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = {"/admin/users"}, method = RequestMethod.GET)
    public String home(Model model, HttpServletRequest request) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDTO) {
            UserDTO user = (UserDTO) principal;
            if (user.getRole().equals("ROLE_ADMIN")) {
                model.addAttribute("users", userService.getAllUsers());
                return "userList";
            }
        }
        model.addAttribute("users", userService.getAllUsers());
        return "userList";
    }

    @RequestMapping(value = {"/admin/users/ban"}, method = RequestMethod.GET)
    public ModelAndView ban(Model model, HttpServletRequest request) {
        String userId = request.getParameter("id");
        userService.banUser(userId);
        return new ModelAndView("redirect:/admin/users");
    }

    @RequestMapping(value = {"/admin/users/unban"}, method = RequestMethod.GET)
    public ModelAndView unban(Model model, HttpServletRequest request) {
        String userId = request.getParameter("id");
        userService.unbanUser(userId);
        return new ModelAndView("redirect:/admin/users");
    }


}
