package org.iskandarov.SpringApp.controller;

import org.iskandarov.SpringApp.entities.Role;
import org.iskandarov.SpringApp.entities.User;
import org.iskandarov.SpringApp.repositories.RoleRepository;
import org.iskandarov.SpringApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Controller
@Transactional
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String listUsers(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("listUsers", this.userRepository.findAll());
        model.addAttribute("listRole", this.roleRepository.findAll());
        return "admin";
    }

    @RequestMapping(value = "/admin/add", method = RequestMethod.POST)
    public String addUsers(@ModelAttribute("admin") User user, @RequestParam(value = "select_role", required = false) String role) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Set<Role> set = new HashSet<>();
        if (role == null) {
            set.add(this.roleRepository.findByRole("ROLE_USER"));
        } else {
            set.add(this.roleRepository.findByRole(role));
        }
        user.setRoles(set);
        this.userRepository.save(user);
        return "redirect:/admin";
    }

    @RequestMapping("remove/{id}")
    public String removeUser(@PathVariable("id") Long id, @ModelAttribute("user") User user) {
        this.userRepository.deleteById(id);
        return "redirect:/admin";
    }

    @RequestMapping("edit/{id}")
    public String editUser(@PathVariable("id") Long id, Model model) {
        model.addAttribute("editappel", this.userRepository.findById(id));
//        model.addAttribute("listUsers", this.userRepository.findAll());
//        model.addAttribute("listRole", this.roleRepository.findAll());

        return "admin";

    }

    @RequestMapping(value="/admin/update",method=RequestMethod.POST)
    public String updateUser(@ModelAttribute("editappel") User user) {
        this.userRepository.save(user);
        return "redirect:/admin";
    }

    @RequestMapping("userdata/{id}")
    public String userData(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", this.userRepository.findById(id));

        return "userdata";

    }

}

