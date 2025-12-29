package com.spring.henallux.firstSpringProject.controller;

import com.spring.henallux.firstSpringProject.dataAccess.dao.*;
import com.spring.henallux.firstSpringProject.model.*;
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

    @Autowired
    private PasswordEncoder passwordEncoder;



    @ModelAttribute(Constants.CURRENT_USER)
    public User signup(){
        return new User();
    }

    @Autowired
    public SignupController(UserDataAccess userDataAccess, AuthorityDataAccess authorityDataAccess, CategoryDataAccess categoryDataAccess, ProductDataAccess productDataAccess, CustomerOrderDataAccess customerOrderDataAccess, OrderLineDataAccess orderLineDataAccess) {
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



    @RequestMapping(value = "/submitSignup", method = RequestMethod.POST)
    public String getSignUpData(Model model, @Valid @ModelAttribute(value = Constants.CURRENT_USER) User userForm, final BindingResult errors){
        if (!errors.hasErrors()){

            User existingUser = userDataAccess.getByUsername(userForm.getUsername());

            if (existingUser != null){
                errors.rejectValue(
                        "username",
                        "username.exists",
                        "Ce nom d'utilisateur existe déjà"
                );

                return "integrated:signup";
            }

            String hashedPassword = passwordEncoder.encode(userForm.getPassword());


            User user = new User(
                    userForm.getUsername(),
                    hashedPassword,
                    userForm.getFirstName(),
                    userForm.getFamilyName(),
                    userForm.getStreet(),
                    userForm.getStreetNumber(),
                    userForm.getPostalCode(),
                    userForm.getCity(),
                    true
            );


            Authority roleUser = new Authority("ROLE_USER", user);
            user.addAuthority(roleUser);


            userDataAccess.add(user);
            /*User newUser = userDataAccess.getByUsername(user.getUsername());



            Category categoryFPS = new Category("Jeux tirs", "Fps games", "Jeu de tirs", "Shooting games");
            categoryDataAccess.add(categoryFPS);

            Product product = new Product("Elden Ring", "Elden Ring", "Jeu difficile", "Difficult game", 59.99, 15, "", true, categoryFPS);
            productDataAccess.add(product);
            System.out.println(categoryDataAccess.getCategories());
            System.out.println(productDataAccess.getProducts());
            for (Product p : productDataAccess.getProducts()){
                System.out.println(p.getDescriptionEn());
            }

            CustomerOrder customerOrder = new CustomerOrder(LocalDateTime.now(), true, "PAID", 120, newUser);
            customerOrderDataAccess.add(customerOrder);
            System.out.println(customerOrderDataAccess.getAllOrders());


            orderLineDataAccess.add(new OrderLine(2, 60, customerOrder, product));
            System.out.println(orderLineDataAccess.getOrdersLines());
            User testUser = userDataAccess.getByUsername(user.getUsername());
            System.out.print(testUser.getAuthorities());*/
            return "redirect:/welcome";
        }


        return "integrated:signup";
    }
}
