package com.georgyi.springbootcrud.controller;

import com.georgyi.springbootcrud.model.Role;
import com.georgyi.springbootcrud.model.User;
import com.georgyi.springbootcrud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.Set;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/userPage")
    public String showUsersPage(@ModelAttribute("user") User user, ModelMap model,
                                HttpServletRequest request) throws SQLException {
        User authUser = (User) request.getSession().getAttribute("user");
        String userEmail = authUser.getEmail();
        Set<Role> roles = authUser.getRoles();
        String forRole = "With roles: ";
        model.addAttribute("username", userEmail);
        model.addAttribute("string", forRole);
        model.addAttribute("roles", roles);
        model.addAttribute("user", authUser);
        return "user";
    }
}


