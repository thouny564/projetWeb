package com.spring.henallux.firstSpringProject.controller;


import com.spring.henallux.firstSpringProject.dataAccess.dao.*;
import com.spring.henallux.firstSpringProject.model.Category;
import com.spring.henallux.firstSpringProject.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/catalog")
public class CatalogController {
    private final ProductDataAccess productDataAccess;
    private final CategoryDataAccess categoryDataAccess;

    @Autowired
    public CatalogController(CategoryDataAccess categoryDataAccess, ProductDataAccess productDataAccess) {
        this.categoryDataAccess = categoryDataAccess;
        this.productDataAccess = productDataAccess;
    }


    @GetMapping
    public String catalog(Model model, Locale locale){

        /*Category fps = new Category("Jeux de tirs", "FPS Games", "Jeux de tir a la premiere personne", "First-person shooter games");
        Category rpg = new Category("Jeux de role", "RPG Games", "Jeux de role immersifs", "Immersive role-playing games");
        Category racing = new Category("Jeux de course", "Racing Games", "Jeux de course rapides", "Fast racing games");

        categoryDataAccess.add(fps);
        categoryDataAccess.add(rpg);
        categoryDataAccess.add(racing);

        // Creation de produits
        Product p1 = new Product("Elden Ring", "Elden Ring", "Jeu difficile et immersif", "Challenging and immersive game",
                59.99, 15, "eldenring.png", true, rpg);

        Product p2 = new Product("Call of Duty", "Call of Duty", "FPS populaire", "Popular FPS game",
                49.99, 20, "cod.png", true, fps);

        Product p3 = new Product("Forza Horizon", "Forza Horizon", "Course automobile realiste", "Realistic car racing",
                39.99, 10, "forza.png", true, racing);


        productDataAccess.add(p1);
        productDataAccess.add(p2);
        productDataAccess.add(p3);
quand tu ajoute un product tu mets le nom de la photo
                */
        ArrayList<Product> products = productDataAccess.getProducts();
        List<Product> availableProducts = new ArrayList<>();
        for (Product p : products) {
            if (p.getStock() > 0) {
                availableProducts.add(p);
            }
        }

        model.addAttribute("products", availableProducts);


        model.addAttribute("categories", categoryDataAccess.getCategories());
        model.addAttribute("lang", locale.getLanguage());
        return "integrated:catalog";
    }


}
