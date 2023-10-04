package ru.javamentor.springmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.javamentor.springmvc.model.User;
import ru.javamentor.springmvc.service.RoleService;
import ru.javamentor.springmvc.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/")
    public String adminPrintAllUsersList(@AuthenticationPrincipal User user, ModelMap modelMap) {
        modelMap.addAttribute("users", userService.getAllUsers());
        modelMap.addAttribute("user", user);
        modelMap.addAttribute("roles", roleService.getAllRoles());
        return "admin/admin_page";
    }

    @GetMapping("/add")
    public String create(@AuthenticationPrincipal User user, ModelMap modelMap) {
        modelMap.addAttribute("user", user);
        modelMap.addAttribute("roles", roleService.getAllRoles());
        return "admin/new_user";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("user") User user, ModelMap modelMap) {
            modelMap.addAttribute("user", user);
            modelMap.addAttribute("roles", roleService.getAllRoles());
            userService.saveUser(user);
            return "redirect:/admin/";
    }
    @PatchMapping("/edit/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") Long id) {
            userService.updateUser(user);
            return "redirect:/admin/";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin/";
    }
}
