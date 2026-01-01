package com.spring.henallux.firstSpringProject.controller;

import com.spring.henallux.firstSpringProject.dataAccess.dao.*;
import com.spring.henallux.firstSpringProject.model.*;
import com.spring.henallux.firstSpringProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.owasp.html.PolicyFactory;
import org.owasp.html.Sanitizers;



@Controller
@RequestMapping("/register")
@SessionAttributes({Constants.CURRENT_USER})
public class SignupController {

    private final UserDataAccess userDataAccess;
    private final AuthorityDataAccess authorityDataAccess;
    private final CategoryDataAccess categoryDataAccess;
    private final ProductDataAccess productDataAccess;
    private final CustomerOrderDataAccess customerOrderDataAccess;
    private final OrderLineDataAccess orderLineDataAccess;
    private final UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;



    @ModelAttribute(Constants.CURRENT_USER)
    public User signup(){
        return new User();
    }

    @Autowired
    public SignupController(UserService userService, UserDataAccess userDataAccess, AuthorityDataAccess authorityDataAccess, CategoryDataAccess categoryDataAccess, ProductDataAccess productDataAccess, CustomerOrderDataAccess customerOrderDataAccess, OrderLineDataAccess orderLineDataAccess) {
        this.userService = userService;
        this.userDataAccess = userDataAccess;
        this.authorityDataAccess = authorityDataAccess;
        this.categoryDataAccess = categoryDataAccess;
        this.productDataAccess = productDataAccess;
        this.customerOrderDataAccess = customerOrderDataAccess;
        this.orderLineDataAccess = orderLineDataAccess;
    }



    @GetMapping
    public String home(Model model) {
        model.addAttribute("signup", new User());
        model.addAttribute("title", "Signup");
        return "integrated:signup";
    }



    @PostMapping("/submitSignup")
    public String submitSignup(
            @Valid @ModelAttribute(Constants.CURRENT_USER) User userForm,
            BindingResult errors) {

        userService.register(userForm, errors);

        if (errors.hasErrors()) {
            return "integrated:signup";
        }

        return "redirect:/welcome";
    }


    private void sanitizeField(String fieldValue, String fieldName, BindingResult errors, PolicyFactory policy, String message) {
        if (!policy.sanitize(fieldValue).equals(fieldValue)) {
            errors.rejectValue(fieldName, fieldName + ".invalid", message);
        }
    }



}
