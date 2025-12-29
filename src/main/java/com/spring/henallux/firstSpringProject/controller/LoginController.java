package com.spring.henallux.firstSpringProject.controller;




import com.spring.henallux.firstSpringProject.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


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
    public String authenticatedPage(Model model ,@AuthenticationPrincipal User user) {
        model.addAttribute("user", user);
        return "integrated:authenticated";
    }
}
