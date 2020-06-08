package com.georgyi.springbootcrud.controller;

import com.georgyi.springbootcrud.model.Role;
import com.georgyi.springbootcrud.model.User;
import com.georgyi.springbootcrud.service.UserService;
import org.dom4j.rule.Mode;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Controller
@RequestMapping("/")
public class LoginController {
    @Autowired
    UserService userService;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/login")
    public String loginPage(ModelMap model, @ModelAttribute("user")User user, HttpServletRequest req) {
        model.addAttribute("user", user);
        return "login";
    }
}






