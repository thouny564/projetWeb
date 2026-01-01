package com.spring.henallux.firstSpringProject.controller;

import com.spring.henallux.firstSpringProject.dataAccess.dao.*;
import com.spring.henallux.firstSpringProject.model.*;
import com.spring.henallux.firstSpringProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;


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

}
