package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entityes.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;
import ru.kata.spring.boot_security.demo.services.UserServiceImpl;

import java.util.ArrayList;


@Controller

public class AdminController {
    private UserService userServiceImpl;
    private RoleService roleServiceImpl;

    @Autowired
    public void setServices(UserService userServiceImpl, RoleService roleServiceImpl) {

        this.userServiceImpl = userServiceImpl;
        this.roleServiceImpl = roleServiceImpl;
    }

    @GetMapping("/admin")
    public String getUsersList(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("users", userServiceImpl.getAllUsers());
        model.addAttribute("user", user);
        model.addAttribute("roles", roleServiceImpl.getAllRoles());
        return "admin";
    }

    @PostMapping("/admin/new")
    public String addNewUser(@ModelAttribute("user") User userForm, @RequestParam("listRoles3") ArrayList<Long> roles, Model model) {
        userServiceImpl.saveUser(userForm, roleServiceImpl.findRoles(roles));
        return "redirect:/admin";
    }

    @PutMapping("/admin/edit")
    public String updateUser(@ModelAttribute("user") User user,
                             @RequestParam("listRoles") ArrayList<Long> roles, Model model) {
        model.addAttribute("roles", roleServiceImpl.getAllRoles());
        System.out.println(user.getId());
        System.out.println(user.getName());
        userServiceImpl.updateUser(user, roleServiceImpl.findRoles(roles));
        return "redirect:/admin";
    }

    @DeleteMapping("/admin/delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userServiceImpl.deleteUser(id);
        return "redirect:/admin";
    }

}
//  @GetMapping()
//  public String getUsersList(Model model) {
//      model.addAttribute("usersList", userService.allUsers());
//       return "admin";
//   }

//   @GetMapping("/new")
//   public String newUser(Model model) {
//       model.addAttribute("userForm", new User());
//       return "new";
//   }
//@GetMapping("/admin-page")
// public String pageForUser(Model model, Principal principal) {
//     model.addAttribute("user", userService.loadUserByUsername(principal.getName()));
//    return "user";
// }
// @GetMapping("/edit/{id}")
//  public String edit(@PathVariable("id") long id, Model model) {
//      model.addAttribute("user", userService.findUserById(id));
//       model.addAttribute("listRoles", userService.getAllRoles());
//       return "edit";
//   }
