package com.polchaev.springsecurity.controller;

import com.polchaev.springsecurity.model.Role;
import com.polchaev.springsecurity.model.User;
import com.polchaev.springsecurity.service.RoleService;
import com.polchaev.springsecurity.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;


import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/admin/")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping(value = {"users", "/"})
    public String userList(ModelMap model, Principal principal) {
        User user = userService.getUserByName(principal.getName());
        model.addAttribute("user", user);
        List<User> userList = userService.getAll();
        List<Role> roles = roleService.allRoles();
        model.addAttribute("roles", roles);
        model.addAttribute("userList", userList);
        return "users";
    }

    @GetMapping(value = "users/add")
    public String addUser(User newUser, ModelMap model, Principal principal) {
        User user = userService.getUserByName(principal.getName());
        model.addAttribute("user", user);
        List<Role> roles = roleService.allRoles();
        model.addAttribute("roles", roles);
        model.addAttribute("newUser", newUser);
        return "addUser";
    }

    @PostMapping(value = "users/add")
    public String addUser(@RequestParam("roles") Long[] roleId, @ModelAttribute("newUser") User newUser) {
        Set<Role> roles = new HashSet<>();
        for (Long rId : roleId) {
            roles.add(roleService.getById(rId));
        }
        newUser.setRoles(roles);
        userService.add(newUser);
        return "redirect:/admin/users";
    }

    @GetMapping(value = "users/{userId}/edit")
    public String editUser(@PathVariable("userId") Long id, ModelMap model) {
        User user = userService.getById(id);
        model.addAttribute("user", user);
        List<Role> roles = roleService.allRoles();
        model.addAttribute("roles", roles);
        return "user-edit";
    }

    @PostMapping(value = "users/{userId}/edit")
    public String editUser(@ModelAttribute("editUser") User user, @PathVariable("userId") Long id, @RequestParam("roles") Long[] roleId) {
        Set<Role> roles = new HashSet<>();
        for (Long rId : roleId) {
            roles.add(roleService.getById(rId));
        }
        user.setId(id);
        user.setRoles(roles);
        userService.update(user);
        return "redirect:/admin/users";
    }

    @PostMapping(value = {"/users/{userId}/delete"})
    public String deleteUser(@PathVariable("userId") Long id) {
        userService.deleteById(id);
        return "redirect:/admin/users";
    }
}
