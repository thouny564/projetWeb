package com.spring.henallux.firstSpringProject.controller;

import com.spring.henallux.firstSpringProject.dataAccess.dao.UserDataAccess;
import com.spring.henallux.firstSpringProject.model.Constants;
import com.spring.henallux.firstSpringProject.model.User;
import com.spring.henallux.firstSpringProject.model.UserUpdateForm;
import com.spring.henallux.firstSpringProject.service.UserService;
import org.owasp.html.PolicyFactory;
import org.owasp.html.Sanitizers;
import org.springframework.beans.factory.annotation.Autowired;
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

    private final UserDataAccess userDataAccess;
    private final UserService userService;

    @Autowired
    public UserController(UserDataAccess userDataAccess, UserService userService) {
        this.userService = userService;
        this.userDataAccess = userDataAccess;
    }


    @PostMapping("/update")
    public String updateUser(@AuthenticationPrincipal User currentUser,
                             @Valid @ModelAttribute(Constants.CURRENT_USER) UserUpdateForm userForm,
                             BindingResult errors,
                             Model model) {

        userService.update(currentUser, userForm, errors);

        if (errors.hasErrors()) {
            return "integrated:authenticated";
        }

        model.addAttribute("successMessage", "Profil mis à jour avec succès !");
        return "integrated:authenticated";
    }




}
