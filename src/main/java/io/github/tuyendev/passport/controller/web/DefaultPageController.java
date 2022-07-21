package io.github.tuyendev.passport.controller.web;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DefaultPageController {

    @GetMapping("/")
    @PreAuthorize("hasAuthority('A')")
    public String index() {
        return "home";
    }

    @GetMapping("/login")
    public String login() {
        return "views/access/signin";
    }

    @GetMapping("/register")
    public String register() {
        return "views/access/signup";
    }

    @GetMapping("/forgot-password")
    public String forgotPassword() {
        return "views/access/forgot-password";
    }
}
