package com.spring.henallux.firstSpringProject.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/company")
public class CompanyController {

    @GetMapping
    public String companyPage(){
        return "integrated:companyDescription";

    }
}
