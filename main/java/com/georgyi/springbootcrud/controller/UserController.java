package com.georgyi.springbootcrud.controller;

import com.georgyi.springbootcrud.model.User;
import com.georgyi.springbootcrud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.sql.SQLException;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/userPage")
    public String showUsersPage(@ModelAttribute("user") User user, ModelMap modelMap) throws SQLException {
        modelMap.addAttribute("user",user);
        return "user";
    }
}


