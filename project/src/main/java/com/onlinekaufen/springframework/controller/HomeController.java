package com.onlinekaufen.springframework.controller;

import com.onlinekaufen.springframework.dto.UserDTO;
import com.onlinekaufen.springframework.service.CategoryService;
import com.onlinekaufen.springframework.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Optional;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    private final
    ProductService productService;

    private final CategoryService categoryService;

    @Autowired
    public HomeController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    /**
     * Simply selects the home view to render by returning its name.
     */
    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public String home(Model model, HttpServletRequest request) {

        model.addAttribute("products", productService.getProducts());
        return "home";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "welcome";
    }

    @RequestMapping(value = "/purchaseHistory", method = RequestMethod.GET)
    public String purchaseHistory(HttpServletRequest request, Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDTO) {
            model.addAttribute("sales", productService.getPurchasesByBuyerId(((UserDTO) principal).getId()));
        } else {
            model.addAttribute("sales", new ArrayList<>());
        }
        return "purchaseSalesHistory";
    }

    @RequestMapping(value = "/salesHistory", method = RequestMethod.GET)
    public String salesHistory(HttpServletRequest request, Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDTO) {
            model.addAttribute("sales", productService.getPurchasesBySellerId(((UserDTO) principal).getId()));
        } else {
            model.addAttribute("sales", new ArrayList<>());
        }
        return "purchaseSalesHistory";
    }

    @RequestMapping(value = "/accessDenied", method = RequestMethod.GET)
    public String accessDeniedPage(ModelMap model) {
        return "accessDenied";
    }

    @RequestMapping(value = "/shop", method = RequestMethod.GET)
    public String shop(ModelMap model, @RequestParam("sortBy") Optional<String> sortBy, @RequestParam("orderBy") Optional<String> orderBy, @RequestParam("category") Optional<Integer> category) {
        model.addAttribute("products", productService.getFilteredProducts(sortBy, orderBy, category));
        model.addAttribute("categoryCount", categoryService.getCategoryCounts());
        return "shop";
    }

    private String getPrincipal() {
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }

}
