package com.georgyi.springbootcrud.controller;

import com.georgyi.springbootcrud.model.User;
import com.georgyi.springbootcrud.service.UserService;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/")
public class LoginController {
    @Autowired
    UserService userService;

    @GetMapping("/registrationPage")
    public String homePage(User user, ModelMap modelMap) {
        modelMap.addAttribute("newUser", user);
        return "registrationPage";
    }

    @PostMapping("/registrationPage")
    public String newUserSubmit(@ModelAttribute("userForm") User userForm) throws Exception {
        userService.addUser(userForm);
        return "redirect:/login";
    }






    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(Model model, User user) {
        model.addAttribute("user", user);
        return "login";
    }
}




