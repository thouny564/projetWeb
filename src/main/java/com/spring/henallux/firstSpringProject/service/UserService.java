package com.spring.henallux.firstSpringProject.service;

import com.spring.henallux.firstSpringProject.dataAccess.dao.UserDataAccess;
import com.spring.henallux.firstSpringProject.model.Authority;
import com.spring.henallux.firstSpringProject.model.User;
import com.spring.henallux.firstSpringProject.model.UserUpdateForm;
import org.owasp.html.PolicyFactory;
import org.owasp.html.Sanitizers;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.time.LocalDate;

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



        LocalDate today = LocalDate.now();
        LocalDate minBirthdate = today.minusYears(13);
        LocalDate maxBirthdate = today.minusYears(120);

        if (userForm.getBirthdate() != null) {
            if (userForm.getBirthdate().isAfter(minBirthdate)) {
                errors.rejectValue("birthdate", "birthdate.tooYoung", "Vous devez avoir au moins 13 ans pour vous inscrire");
            } else if (userForm.getBirthdate().isBefore(maxBirthdate)) {
                errors.rejectValue("birthdate", "birthdate.tooOld", "Date de naissance trop ancienne");
            }
        }


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
                userForm.getMailAddress(),
                userForm.getBirthdate()
        );

        Authority roleUser = new Authority("ROLE_USER", user);
        user.addAuthority(roleUser);

        userDataAccess.add(user);
    }


    public void update(User currentUser, UserUpdateForm userForm, BindingResult errors) {



        if (userForm.getBirthdate() == null) {
            errors.rejectValue("birthdate", "birthdate.invalid", "Date de naissance requise");
        } else {
            LocalDate today = LocalDate.now();
            LocalDate minBirthdate = today.minusYears(13);
            LocalDate maxBirthdate = today.minusYears(120);
            if (userForm.getBirthdate().isAfter(minBirthdate)) {
                errors.rejectValue("birthdate", "birthdate.tooYoung", "Vous devez avoir au moins 13 ans");
            } else if (userForm.getBirthdate().isBefore(maxBirthdate)) {
                errors.rejectValue("birthdate", "birthdate.tooOld", "Date de naissance trop ancienne");
            }
        }



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
        currentUser.setBirthdate(userForm.getBirthdate());

        userDataAccess.update(currentUser);

        User updatedUser = userDataAccess.getByUsername(currentUser.getUsername());

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                updatedUser,
                currentUser.getPassword(),
                currentUser.getAuthorities()
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private void sanitize(String value, String field, BindingResult errors,
                          PolicyFactory policy, String message) {
        if (!policy.sanitize(value).equals(value)) {
            errors.rejectValue(field, field + ".invalid", message);
        }
    }


}