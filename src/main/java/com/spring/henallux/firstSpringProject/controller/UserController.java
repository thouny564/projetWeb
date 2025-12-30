package com.spring.henallux.firstSpringProject.controller;

import com.spring.henallux.firstSpringProject.model.Constants;
import com.spring.henallux.firstSpringProject.model.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {


    @PostMapping("/update")
    public String User(@AuthenticationPrincipal User user, Model model,
                       @Valid @ModelAttribute(value = Constants.CURRENT_USER) User userForm,
                       final BindingResult errors){

        return "";
    }

}
