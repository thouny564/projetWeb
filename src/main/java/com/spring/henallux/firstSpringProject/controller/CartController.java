package com.spring.henallux.firstSpringProject.controller;

import com.spring.henallux.firstSpringProject.dataAccess.dao.ProductDataAccess;
import com.spring.henallux.firstSpringProject.model.Cart;
import com.spring.henallux.firstSpringProject.model.Constants;
import com.spring.henallux.firstSpringProject.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Map;

@Controller
public class CartController {

    private final ProductDataAccess productDataAccess;

    @Autowired
    public CartController(ProductDataAccess productDataAccess) {
        this.productDataAccess = productDataAccess;
    }


    private Cart getCart(HttpSession session) {
        Cart cart = (Cart) session.getAttribute(Constants.CART);
        if (cart == null) {
            cart = new Cart();
            session.setAttribute(Constants.CART, cart);
        }
        return cart;
    }


    @GetMapping("/cart")
    public String viewCart(HttpSession session, Model model) {
        Cart cart = getCart(session);
        Map<Integer, Integer> items = cart.getItems();



        ArrayList<Product> products = new ArrayList<>();
        for (Integer productId : items.keySet()) {
            Product p = productDataAccess.get(productId);
            if (p != null) {
                products.add(p);
            }
        }

        model.addAttribute("products", products);
        model.addAttribute("quantities", items);

        return "integrated:cart";
    }


    @PostMapping("/cart/add/{productId}")
    public String addToCart(@PathVariable Integer productId, HttpSession session) {
        Cart cart = getCart(session);
        Map<Integer, Integer> items = cart.getItems();
        items.put(productId, items.getOrDefault(productId, 0) + 1);

        session.setAttribute(Constants.CART, cart);
        return "redirect:/cart";
    }


    @PostMapping("/cart/remove/{productId}")
    public String removeFromCart(@PathVariable Integer productId, HttpSession session) {
        Cart cart = getCart(session);
        Map<Integer, Integer> items = cart.getItems();

        if (items.containsKey(productId)) {
            int quantity = items.get(productId);
            if (quantity > 1) {
                items.put(productId, quantity - 1);
            } else {
                items.remove(productId);
            }
        }

        session.setAttribute(Constants.CART, cart);
        return "redirect:/cart";
    }




    @PostMapping("/cart/update/{productId}")
    public String updateCart(
            @PathVariable Integer productId,
            @RequestParam Integer quantity,
            HttpSession session
    ) {
        Cart cart = getCart(session);
        Map<Integer, Integer> items = cart.getItems();

        if (quantity > 0) {
            items.put(productId, quantity);
        } else {
            items.remove(productId);
        }

        session.setAttribute(Constants.CART, cart);
        return "redirect:/cart";
    }


    @PostMapping("/cart/delete/{productId}")
    public String deleteFromCart(@PathVariable Integer productId, HttpSession session) {
        Cart cart = getCart(session);
        Map<Integer, Integer> items = cart.getItems();

        items.remove(productId);

        session.setAttribute(Constants.CART, cart);
        return "redirect:/cart";
    }




}
