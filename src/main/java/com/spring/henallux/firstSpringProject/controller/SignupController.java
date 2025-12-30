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
    public String getSignUpData(Model model,
                                @Valid @ModelAttribute(value = Constants.CURRENT_USER) User userForm,
                                final BindingResult errors) {

        PolicyFactory policy = Sanitizers.FORMATTING.and(Sanitizers.LINKS);

        sanitizeField(userForm.getUsername(), "username", errors, policy, "Nom d'utilisateur invalide");
        sanitizeField(userForm.getFirstName(), "firstName", errors, policy, "Prénom invalide");
        sanitizeField(userForm.getFamilyName(), "familyName", errors, policy, "Nom de famille invalide");
        sanitizeField(userForm.getStreet(), "street", errors, policy, "Rue invalide");
        sanitizeField(userForm.getCity(), "city", errors, policy, "Ville invalide");
        sanitizeField(userForm.getPhoneNumber(), "phoneNumber", errors, policy, "Numéro de téléphone invalide");

        if (errors.hasErrors()) {
            return "integrated:signup";
        }


        if (userDataAccess.getByUsername(userForm.getUsername()) != null) {
            errors.rejectValue("username", "username.exists", "Ce nom d'utilisateur existe déjà");
            return "integrated:signup";
        }


        if (userDataAccess.getByMailAddress(userForm.getMailAddress()) != null) {
            errors.rejectValue("mailAddress", "mailAddress.exists", "Cette adresse email existe déjà");
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
                true,
                userForm.getPhoneNumber(),
                userForm.getMailAddress()
        );


        Authority roleUser = new Authority("ROLE_USER", user);
        user.addAuthority(roleUser);


        userDataAccess.add(user);

        return "redirect:/welcome";
    }

    private void sanitizeField(String fieldValue, String fieldName, BindingResult errors, PolicyFactory policy, String message) {
        if (!policy.sanitize(fieldValue).equals(fieldValue)) {
            errors.rejectValue(fieldName, fieldName + ".invalid", message);
        }
    }



}
