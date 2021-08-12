package com.internship.controller;

import com.internship.utils.AuthenticationCheckingUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class AccountLoginController {

    @GetMapping
    public String showLoginForm() {
        if (AuthenticationCheckingUtils.isAuthenticated()) {
            return "redirect:/";
        }

        return "loginPage";
    }

    @PostMapping
    public String showLoginFormPost() {
        return "loginPage";
    }
}
