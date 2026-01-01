package com.spring.henallux.firstSpringProject.controller;


import com.spring.henallux.firstSpringProject.model.Constants;
import com.spring.henallux.firstSpringProject.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/company")
public class CompanyController {

    @GetMapping
    public String companyPage(Model model){
        return "integrated:companyDescription";

    }
}
