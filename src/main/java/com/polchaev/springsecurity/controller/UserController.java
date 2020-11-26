package com.polchaev.springsecurity.controller;

import com.polchaev.springsecurity.model.Role;
import com.polchaev.springsecurity.model.User;
import com.polchaev.springsecurity.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;


@Controller
@RequestMapping("/user/")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "info")
    public String printUserInfo(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getUserByName(auth.getName());
        model.addAttribute("user", user);

        Set<Role> roles = user.getRoles();
        boolean isMath = roles.stream().anyMatch(u -> u.getRole().equals("ROLE_ADMIN"));

        if (isMath) {
            model.addAttribute("add", true);
        }
        return "info";
    }
}