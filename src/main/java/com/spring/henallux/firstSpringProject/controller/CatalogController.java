package com.spring.henallux.firstSpringProject.controller;


import com.spring.henallux.firstSpringProject.dataAccess.dao.*;
import com.spring.henallux.firstSpringProject.model.Category;
import com.spring.henallux.firstSpringProject.model.Product;
import com.spring.henallux.firstSpringProject.model.Promotion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

@Controller
@RequestMapping("/catalog")
public class CatalogController {
    private final ProductDataAccess productDataAccess;
    private final CategoryDataAccess categoryDataAccess;
    private final PromotionDataAccess promotionDataAccess;

    @Autowired
    public CatalogController(CategoryDataAccess categoryDataAccess, ProductDataAccess productDataAccess, PromotionDataAccess promotionDataAccess) {
        this.categoryDataAccess = categoryDataAccess;
        this.productDataAccess = productDataAccess;
        this.promotionDataAccess = promotionDataAccess;
    }


    @GetMapping
    public String catalog(Model model, Locale locale) {

        ArrayList<Product> products = productDataAccess.getProducts();
        List<Product> availableProducts = new ArrayList<>();
        Map<Integer, Double> promoPrices = new HashMap<>();

        for (Product p : products) {
            if (p.getStock() > 0) {

                availableProducts.add(p);

                List<Promotion> promotions =
                        promotionDataAccess.getPromotionsByProductId(p.getId());

                double discountedPrice = p.getPrice();
                int maxDiscount = 0;

                for (Promotion promotion : promotions) {
                    if (promotion.getDiscountPercentage() > maxDiscount) {
                        maxDiscount = promotion.getDiscountPercentage();
                    }
                }

                if (maxDiscount > 0) {
                    discountedPrice = p.getPrice() * (1 - maxDiscount / 100.0);
                }

                promoPrices.put(p.getId(), discountedPrice);
            }
        }

        model.addAttribute("products", availableProducts);
        model.addAttribute("promoPrices", promoPrices);
        model.addAttribute("categories", categoryDataAccess.getCategories());
        model.addAttribute("lang", locale.getLanguage());

        return "integrated:catalog";
    }



}
