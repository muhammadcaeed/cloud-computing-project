package com.onlinekaufen.springframework.controller;

import com.onlinekaufen.springframework.dto.BuyingProductsDTO;
import com.onlinekaufen.springframework.dto.CartSessionDTO;
import com.onlinekaufen.springframework.dto.Response;
import com.onlinekaufen.springframework.dto.UserDTO;
import com.onlinekaufen.springframework.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.annotation.SessionScope;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/*
Handle requests regarding the cart functionality.
 */
@SessionScope
@Controller
@RequestMapping(value = "/cart")
public class CartController {

    public static final Logger logger = LoggerFactory.getLogger(CartController.class);

    private final
    ProductService productService;

    @Autowired
    public CartController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public String cart(HttpServletRequest request, Model model) {
        HashMap<Integer, CartSessionDTO> sessionCart = (HashMap<Integer, CartSessionDTO>) request.getSession().getAttribute("cart");
        if (sessionCart.isEmpty()) {
            logger.info("The session cart is empty");
            request.getSession().setAttribute("cartItems", 0);
            model.addAttribute("cartItems", new ArrayList<>());
        } else {
            logger.info("The session cart is not empty.");
            request.getSession().setAttribute("cartItems", sessionCart.size());
            model.addAttribute("cartItems", productService.getBuyingProducts(sessionCart));
        }
        return "cart";
    }

    @RequestMapping(value = "/removeItem/{itemId}", method = RequestMethod.GET)
    public @ResponseBody
    Response<String> removeFromCart(HttpServletRequest request, Model model, @PathVariable("itemId") Integer itemId) {
        HashMap<Integer, CartSessionDTO> sessionCart = (HashMap<Integer, CartSessionDTO>) request.getSession().getAttribute("cart");
        sessionCart.remove(itemId);
        return Response.ok("Removed item successful", HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase());
    }

    @RequestMapping(value = "/add/{itemId}/{itemQty}", method = RequestMethod.GET)
    public @ResponseBody
    Response<String>
    addToCart(HttpServletRequest request, @PathVariable("itemId") int itemId, @PathVariable("itemQty") Integer itemQty) {
        HashMap<Integer, CartSessionDTO> sessionCart;
        if (request.getSession().getAttribute("cart") == null) {
            sessionCart = new LinkedHashMap<>();
            CartSessionDTO cartSessionDTO = new CartSessionDTO(itemId, itemQty);
            sessionCart.put(itemId, cartSessionDTO);
        } else {
            sessionCart = (HashMap<Integer, CartSessionDTO>) request.getSession().getAttribute("cart");
            if (sessionCart.containsKey(itemId)) {
                CartSessionDTO cartSessionDTO = sessionCart.get(itemId);
                itemQty += cartSessionDTO.getItemQty();
                cartSessionDTO.setItemQty(itemQty);
                sessionCart.put(cartSessionDTO.getItemId(), cartSessionDTO);
            } else {
                CartSessionDTO cartSessionDTO = new CartSessionDTO(itemId, itemQty);
                sessionCart.put(itemId, cartSessionDTO);
            }
        }
        List<BuyingProductsDTO> buyingProductDTOS = productService.getBuyingProducts(sessionCart);
        for (BuyingProductsDTO buyingProduct : buyingProductDTOS) {
            if (buyingProduct.getQty() > buyingProduct.getStock()) {
                return Response.fail(HttpStatus.BAD_REQUEST.value(), "Requested quantity exceeded stock");
            }
        }
        request.getSession().setAttribute("cart", sessionCart);
        request.getSession().setAttribute("cartItems", sessionCart.size());
        return Response.ok("Added to the cart", HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase());
    }

    @RequestMapping(value = "/checkout", method = RequestMethod.GET)
    public String checkCheckout(HttpServletRequest request, Model model) {
        Float totalVal = 0F;
        if (request.getSession().getAttribute("cart") == null) {
            model.addAttribute("items", new ArrayList<>());
            model.addAttribute("total", totalVal);
        } else {
            HashMap<Integer, CartSessionDTO> cartSession = (HashMap<Integer, CartSessionDTO>) request.getSession().getAttribute("cart");
            List<BuyingProductsDTO> buyingProductDTOS = productService.getBuyingProducts(cartSession);
            for (BuyingProductsDTO buyingProduct : buyingProductDTOS) {
                totalVal += (buyingProduct.getPrice() * buyingProduct.getQty());
            }
            model.addAttribute("items", buyingProductDTOS);
            model.addAttribute("total", totalVal);
        }
        return "checkout";
    }

    @RequestMapping(value = "/buy", method = RequestMethod.GET)
    public
    @ResponseBody
    Response confirmCheckout(HttpServletRequest request) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDTO) {
            if (request.getSession().getAttribute("cart") == null) {
                Response.fail(HttpStatus.BAD_REQUEST.value(), "No items in the cart to buy");
            }
            HashMap<Integer, CartSessionDTO> cartSession = (HashMap<Integer, CartSessionDTO>) request.getSession().getAttribute("cart");
            List<BuyingProductsDTO> buyingProductDTOS = productService.getBuyingProducts(cartSession);
            Response response = productService.buyProducts(buyingProductDTOS);
            if (response.getStatus() == 200) {
                request.getSession().removeAttribute("cart");
                request.getSession().removeAttribute("cartItems");
                return response;
            }
            return response;

        } else {
            return Response.fail(HttpStatus.UNAUTHORIZED.value(), "Please login before trying to checkout.");
        }
    }
}