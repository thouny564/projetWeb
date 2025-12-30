package com.spring.henallux.firstSpringProject.controller;

import com.spring.henallux.firstSpringProject.model.Cart;
import com.spring.henallux.firstSpringProject.model.Constants;
import com.spring.henallux.firstSpringProject.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.Map;

@SessionAttributes({Constants.CART})
@Controller
public class CartController {


    @ModelAttribute(Constants.CART)
    public Cart initCart() {
        return new Cart();
    }


    @PostMapping("/cart/add/{productId}")
    public String addToCart(
            @PathVariable Long productId,
            @ModelAttribute("cart") Cart cart
    ) {
        Map<Long, Integer> items = cart.getItems();
        if (items.containsKey(productId)) {
            items.put(productId, items.get(productId) + 1);
        } else {
            items.put(productId, 1);
        }


        for (Map.Entry<Long, Integer> entry : items.entrySet()) {
            Long productIdd = entry.getKey();
            Integer quantity = entry.getValue();
            System.out.println("Produit ID: " + productIdd + ", Quantité: " + quantity);
        }

        return "";
    }




}