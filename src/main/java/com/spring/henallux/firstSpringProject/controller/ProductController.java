package com.spring.henallux.firstSpringProject.controller;

import com.spring.henallux.firstSpringProject.dataAccess.dao.CategoryDataAccess;
import com.spring.henallux.firstSpringProject.dataAccess.dao.ProductDataAccess;
import com.spring.henallux.firstSpringProject.dataAccess.dao.PromotionDataAccess;
import com.spring.henallux.firstSpringProject.model.Product;
import com.spring.henallux.firstSpringProject.model.Promotion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/product")
public class ProductController {

    private final ProductDataAccess productDataAccess;
    private final CategoryDataAccess categoryDataAccess;
    private final PromotionDataAccess promotionDataAccess;

    @Autowired
    public ProductController(ProductDataAccess productDataAccess,
                             CategoryDataAccess categoryDataAccess,
                             PromotionDataAccess promotionDataAccess) {
        this.productDataAccess = productDataAccess;
        this.categoryDataAccess = categoryDataAccess;
        this.promotionDataAccess = promotionDataAccess;
    }

    @PostMapping("/category")
    public String filterByCategory(@RequestParam("categoryId") Integer categoryId,
                                   Model model,
                                   Locale locale) {

        List<Product> filteredProducts =
                productDataAccess.getProductsByCategoryId(categoryId);

        List<Product> availableProducts = new ArrayList<>();
        Map<Integer, Double> promoPrices = new HashMap<>();

        for (Product p : filteredProducts) {
            if (p.getStock() > 0) {
                availableProducts.add(p);

                List<Promotion> promotions =
                        promotionDataAccess.getPromotionsByProductId(p.getId());

                double discountedPrice = p.getPrice();
                int maxDiscount = 0;

                for (Promotion promo : promotions) {
                    if (promo.getDiscountPercentage() > maxDiscount) {
                        maxDiscount = promo.getDiscountPercentage();
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
        model.addAttribute("selectedCategoryId", categoryId);

        return "integrated:catalog";
    }

    @GetMapping("/{productId}")
    public String viewProduct(@PathVariable Integer productId,
                              Model model,
                              Locale locale) {

        Product product = productDataAccess.get(productId);

        if (product == null) {
            return "redirect:/catalog";
        }


        List<Promotion> promotions =
                promotionDataAccess.getPromotionsByProductId(productId);

        double promoPrice = product.getPrice();
        int maxDiscount = 0;

        for (Promotion promo : promotions) {
            if (promo.getDiscountPercentage() > maxDiscount) {
                maxDiscount = promo.getDiscountPercentage();
            }
        }

        if (maxDiscount > 0) {
            promoPrice = product.getPrice() * (1 - maxDiscount / 100.0);
        }

        model.addAttribute("product", product);
        model.addAttribute("promoPrice", promoPrice);
        model.addAttribute("lang", locale.getLanguage());

        return "integrated:descriptionProduct";
    }

}
