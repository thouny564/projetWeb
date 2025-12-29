package com.spring.henallux.firstSpringProject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/home")
public class HomepageController {

    @GetMapping
    public String home(Model model) {
        model.addAttribute("products", List.of(1, 2, 3));
        model.addAttribute("title", "Homepage");
        return "integrated:home";
    }
}
