package com.spring.henallux.firstSpringProject.service;

import com.spring.henallux.firstSpringProject.dataAccess.dao.UserDataAccess;
import com.spring.henallux.firstSpringProject.model.Authority;
import com.spring.henallux.firstSpringProject.model.User;
import com.spring.henallux.firstSpringProject.model.UserUpdateForm;
import org.owasp.html.PolicyFactory;
import org.owasp.html.Sanitizers;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
public class UserService {

    private final UserDataAccess userDataAccess;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserDataAccess userDataAccess,
                       PasswordEncoder passwordEncoder) {
        this.userDataAccess = userDataAccess;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(User userForm, BindingResult errors) {

        PolicyFactory policy = Sanitizers.FORMATTING.and(Sanitizers.LINKS);

        sanitize(userForm.getUsername(), "username", errors, policy, "Nom d'utilisateur invalide");
        sanitize(userForm.getFirstName(), "firstName", errors, policy, "Prénom invalide");
        sanitize(userForm.getFamilyName(), "familyName", errors, policy, "Nom invalide");
        sanitize(userForm.getStreet(), "street", errors, policy, "Rue invalide");
        sanitize(userForm.getCity(), "city", errors, policy, "Ville invalide");
        sanitize(userForm.getPhoneNumber(), "phoneNumber", errors, policy, "Téléphone invalide");

        if (errors.hasErrors()) return;

        if (userDataAccess.getByUsername(userForm.getUsername()) != null) {
            errors.rejectValue("username", "username.exists", "Ce nom d'utilisateur existe déjà");
            return;
        }

        if (userDataAccess.getByMailAddress(userForm.getMailAddress()) != null) {
            errors.rejectValue("mailAddress", "mailAddress.exists", "Cette adresse email existe déjà");
            return;
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
    }


    public void update(User currentUser, UserUpdateForm userForm, BindingResult errors) {
        PolicyFactory policy = Sanitizers.FORMATTING.and(Sanitizers.LINKS);

        sanitize(userForm.getFirstName(), "firstName", errors, policy, "Prénom invalide");
        sanitize(userForm.getFamilyName(), "familyName", errors, policy, "Nom de famille invalide");
        sanitize(userForm.getStreet(), "street", errors, policy, "Rue invalide");
        sanitize(userForm.getCity(), "city", errors, policy, "Ville invalide");
        sanitize(userForm.getPhoneNumber(), "phoneNumber", errors, policy, "Numéro de téléphone invalide");

        if (userForm.getMailAddress() == null || userForm.getMailAddress().isBlank()) {
            errors.rejectValue("mailAddress", "mailAddress.invalid", "Adresse email invalide");
        }

        if (errors.hasErrors()) return;

        User userByEmail = userDataAccess.getByMailAddress(userForm.getMailAddress());
        if (userByEmail != null && !userByEmail.getId().equals(currentUser.getId())) {
            errors.rejectValue("mailAddress", "mailAddress.exists", "Cette adresse email existe déjà");
            return;
        }


        currentUser.setFirstName(userForm.getFirstName());
        currentUser.setFamilyName(userForm.getFamilyName());
        currentUser.setStreet(userForm.getStreet());
        currentUser.setStreetNumber(userForm.getStreetNumber());
        currentUser.setPostalCode(userForm.getPostalCode());
        currentUser.setCity(userForm.getCity());
        currentUser.setPhoneNumber(userForm.getPhoneNumber());
        currentUser.setMailAddress(userForm.getMailAddress());

        userDataAccess.update(currentUser);
    }

    private void sanitize(String value, String field, BindingResult errors,
                          PolicyFactory policy, String message) {
        if (!policy.sanitize(value).equals(value)) {
            errors.rejectValue(field, field + ".invalid", message);
        }
    }


}