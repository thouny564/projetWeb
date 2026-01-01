package com.spring.henallux.firstSpringProject.controller;




import com.spring.henallux.firstSpringProject.model.Constants;
import com.spring.henallux.firstSpringProject.model.User;
import com.spring.henallux.firstSpringProject.model.UserUpdateForm;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();


        if (auth != null && auth.isAuthenticated() && !"anonymousUser".equals(auth.getPrincipal())) {
            return "redirect:/authenticated";
        }


        return "integrated:login";
    }

    @GetMapping("/authenticated")
    public String authenticatedPage(Model model ,@AuthenticationPrincipal User currentUser,  RedirectAttributes redirectAttributes) {
        UserUpdateForm form = new UserUpdateForm();
        form.setFirstName(currentUser.getFirstName());
        form.setFamilyName(currentUser.getFamilyName());
        form.setMailAddress(currentUser.getMailAddress());
        form.setPhoneNumber(currentUser.getPhoneNumber());
        form.setStreet(currentUser.getStreet());
        form.setStreetNumber(currentUser.getStreetNumber());
        form.setPostalCode(currentUser.getPostalCode());
        form.setCity(currentUser.getCity());
        form.setBirthdate(currentUser.getBirthdate());
        model.addAttribute(Constants.CURRENT_USER, form);
        redirectAttributes.addFlashAttribute(
                "successMessage",
                "userCreated"
        );
        return "integrated:authenticated";
    }
}
