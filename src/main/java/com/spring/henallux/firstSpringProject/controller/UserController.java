package com.spring.henallux.firstSpringProject.controller;

import com.spring.henallux.firstSpringProject.dataAccess.dao.UserDataAccess;
import com.spring.henallux.firstSpringProject.model.Constants;
import com.spring.henallux.firstSpringProject.model.User;
import com.spring.henallux.firstSpringProject.model.UserUpdateForm;
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

    @Autowired
    public UserController(UserDataAccess userDataAccess) {
        this.userDataAccess = userDataAccess;
    }


    @PostMapping("/update")
    public String updateUser(
            @AuthenticationPrincipal User currentUser,
            @Valid @ModelAttribute(Constants.CURRENT_USER) UserUpdateForm userForm,
            BindingResult errors,
            Model model) {

        User user = userDataAccess.getByUsername(currentUser.getUsername());

        PolicyFactory policy = Sanitizers.FORMATTING.and(Sanitizers.LINKS);
        sanitizeField(userForm.getFirstName(), "firstName", errors, policy, "Prénom invalide");
        sanitizeField(userForm.getFamilyName(), "familyName", errors, policy, "Nom de famille invalide");
        sanitizeField(userForm.getStreet(), "street", errors, policy, "Rue invalide");
        sanitizeField(userForm.getCity(), "city", errors, policy, "Ville invalide");
        sanitizeField(userForm.getPhoneNumber(), "phoneNumber", errors, policy, "Numéro de téléphone invalide");

        if (userForm.getMailAddress() == null || userForm.getMailAddress().isBlank()) {
            errors.rejectValue("mailAddress", "mailAddress.invalid", "Adresse email invalide");
        }

        if (errors.hasErrors()) {
            return "integrated:authenticated";
        }

        User userByEmail = userDataAccess.getByMailAddress(userForm.getMailAddress());
        if (userByEmail != null && !userByEmail.getId().equals(currentUser.getId())) {
            errors.rejectValue("mailAddress", "mailAddress.exists", "Cette adresse email existe déjà");
            return "integrated:authenticated";
        }

        user.setFirstName(userForm.getFirstName());
        user.setFamilyName(userForm.getFamilyName());
        user.setStreet(userForm.getStreet());
        user.setStreetNumber(userForm.getStreetNumber());
        user.setPostalCode(userForm.getPostalCode());
        user.setCity(userForm.getCity());
        user.setPhoneNumber(userForm.getPhoneNumber());
        user.setMailAddress(userForm.getMailAddress());

        userDataAccess.update(user);


        model.addAttribute("successMessage", "Profil mis à jour avec succès !");

        return "integrated:authenticated";
    }


    private void sanitizeField(String fieldValue, String fieldName, BindingResult errors, PolicyFactory policy, String message) {
        if (!policy.sanitize(fieldValue).equals(fieldValue)) {
            errors.rejectValue(fieldName, fieldName + ".invalid", message);
        }
    }


}
