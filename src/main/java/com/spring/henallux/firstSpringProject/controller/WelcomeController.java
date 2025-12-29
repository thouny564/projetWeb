package com.spring.henallux.firstSpringProject.controller;


import com.spring.henallux.firstSpringProject.model.Constants;
import com.spring.henallux.firstSpringProject.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping(value="/welcome")
@SessionAttributes({Constants.CURRENT_USER})
public class WelcomeController {



    @ModelAttribute(Constants.CURRENT_USER)
    public User signup(){
        return new User();
    }


    @GetMapping
    public String welcome(Model model, @ModelAttribute(value = Constants.CURRENT_USER) User user){
        return "integrated:welcome";
    }
}
