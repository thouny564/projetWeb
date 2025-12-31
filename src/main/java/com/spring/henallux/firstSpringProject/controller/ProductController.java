package com.spring.henallux.firstSpringProject.controller;

import com.spring.henallux.firstSpringProject.dataAccess.dao.CategoryDataAccess;
import com.spring.henallux.firstSpringProject.dataAccess.dao.ProductDataAccess;
import com.spring.henallux.firstSpringProject.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/product/")
public class ProductController {
    private final ProductDataAccess productDataAccess;
    private  final CategoryDataAccess categoryDataAccess;


    @Autowired
    public ProductController(ProductDataAccess productDataAccess, CategoryDataAccess categoryDataAccess) {
        this.productDataAccess = productDataAccess;
        this.categoryDataAccess = categoryDataAccess;
    }



    @PostMapping("/category")
    public String filterByCategory(@RequestParam("categoryId") Integer categoryId, Model model, Locale locale) {
        List<Product> filteredProducts = productDataAccess.getProductsByCategoryId(categoryId);

        List<Product> availableProducts = new ArrayList<>();
        for (Product p : filteredProducts) {
            if (p.getStock() > 0) availableProducts.add(p);
        }

        model.addAttribute("products", availableProducts);
        model.addAttribute("categories", categoryDataAccess.getCategories());
        model.addAttribute("lang", locale.getLanguage());
        model.addAttribute("selectedCategoryId", categoryId);

        return "integrated:catalog";
    }

    @GetMapping("/{productId}")
    public String viewProduct(@PathVariable Integer productId, Model model){

        Product product = productDataAccess.get(productId);
        model.addAttribute("product", product);

        return "integrated:descriptionProduct";

    }






}
