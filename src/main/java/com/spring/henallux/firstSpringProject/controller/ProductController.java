package com.spring.henallux.firstSpringProject.controller;

import com.spring.henallux.firstSpringProject.dataAccess.dao.ProductDataAccess;
import com.spring.henallux.firstSpringProject.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product/{productId}")
public class ProductController {
    private final ProductDataAccess productDataAccess;


    @Autowired
    public ProductController(ProductDataAccess productDataAccess) {
        this.productDataAccess = productDataAccess;
    }

    @GetMapping
    public String viewProduct(@PathVariable Integer productId, Model model){

        Product product = productDataAccess.get(productId);
        model.addAttribute("product", product);

        return "integrated:descriptionProduct";

    }




}
