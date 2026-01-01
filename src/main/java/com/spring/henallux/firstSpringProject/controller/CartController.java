package com.spring.henallux.firstSpringProject.controller;

import com.spring.henallux.firstSpringProject.dataAccess.dao.ProductDataAccess;
import com.spring.henallux.firstSpringProject.dataAccess.dao.PromotionDataAccess;
import com.spring.henallux.firstSpringProject.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CartController {

    private final ProductDataAccess productDataAccess;
    private final PromotionDataAccess promotionDataAccess;

    @Autowired
    public CartController(ProductDataAccess productDataAccess, PromotionDataAccess promotionDataAccess) {
        this.productDataAccess = productDataAccess;
        this.promotionDataAccess = promotionDataAccess;
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

        List<Product> products = new ArrayList<>();
        Map<Integer, Double> promoPrices = new HashMap<>();
        double cartTotal = 0.0;

        for (Map.Entry<Integer, Integer> entry : items.entrySet()) {
            Integer productId = entry.getKey();
            Integer quantity = entry.getValue();

            Product product = productDataAccess.get(productId);
            if (product != null && quantity > 0) {
                products.add(product);


                List<Promotion> promotions = promotionDataAccess.getPromotionsByProductId(productId);
                double discountedPrice = product.getPrice();
                int maxDiscount = 0;
                for (Promotion promo : promotions) {
                    if (promo.getDiscountPercentage() > maxDiscount) {
                        maxDiscount = promo.getDiscountPercentage();
                    }
                }
                if (maxDiscount > 0) {
                    discountedPrice = product.getPrice() * (1 - maxDiscount / 100.0);
                }
                promoPrices.put(productId, discountedPrice);


                cartTotal += discountedPrice * quantity;
            }
        }

        model.addAttribute("products", products);
        model.addAttribute("quantities", items);
        model.addAttribute("cartTotal", cartTotal);
        model.addAttribute("promoPrices", promoPrices);

        return "integrated:cart";
    }



    @PostMapping("/cart/addCustomQuantity/{productId}")
    public String addToCart(
            @PathVariable Integer productId,
            @RequestParam Integer quantity,
            HttpSession session
    ) {
        Product product = productDataAccess.get(productId);
        if (product == null || quantity <= 0) {
            return "redirect:/product/" + productId;
        }

        Cart cart = getCart(session);
        Map<Integer, Integer> items = cart.getItems();

        int currentQty = items.getOrDefault(productId, 0);
        int newQty = currentQty + quantity;

        if (newQty > product.getStock()) {
            newQty = product.getStock();
        }

        items.put(productId, newQty);
        return "redirect:/product/" + productId;
    }



    @PostMapping("/cart/add/{productId}")
    public String addOneToCart(
            @PathVariable Integer productId,
            HttpSession session
    ) {
        Product product = productDataAccess.get(productId);
        if (product == null) {
            return "redirect:/cart";
        }

        Cart cart = getCart(session);
        Map<Integer, Integer> items = cart.getItems();

        int newQty = items.getOrDefault(productId, 0) + 1;
        if (newQty <= product.getStock()) {
            items.put(productId, newQty);
        }

        return "redirect:/cart";
    }


    @PostMapping("/cart/update/{productId}")
    public String updateCart(
            @PathVariable Integer productId,
            @RequestParam Integer quantity,
            HttpSession session
    ) {
        Product product = productDataAccess.get(productId);
        if (product == null) {
            return "redirect:/cart";
        }

        Cart cart = getCart(session);
        Map<Integer, Integer> items = cart.getItems();

        if (quantity != null && quantity > 0 && quantity <= product.getStock()) {
            items.put(productId, quantity);
        } else {
            items.remove(productId);
        }
        return "redirect:/cart";
    }



    @PostMapping("/cart/remove/{productId}")
    public String removeOneFromCart(
            @PathVariable Integer productId,
            HttpSession session
    ) {
        Cart cart = getCart(session);
        Map<Integer, Integer> items = cart.getItems();

        if (items.containsKey(productId)) {
            int qty = items.get(productId);
            if (qty > 1) {
                items.put(productId, qty - 1);
            } else {
                items.remove(productId);
            }
        }
        return "redirect:/cart";
    }



    @PostMapping("/cart/delete/{productId}")
    public String deleteFromCart(
            @PathVariable Integer productId,
            HttpSession session
    ) {
        Cart cart = getCart(session);
        cart.getItems().remove(productId);
        return "redirect:/cart";
    }
}
