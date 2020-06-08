package com.georgyi.springbootcrud.controller;

import com.georgyi.springbootcrud.dao.UserHibernateDAO;
import com.georgyi.springbootcrud.model.Role;
import com.georgyi.springbootcrud.model.User;
import com.georgyi.springbootcrud.service.RoleService;
import com.georgyi.springbootcrud.service.UserService;
import com.georgyi.springbootcrud.service.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    UserService userService;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String showUserList(ModelMap model, HttpServletRequest request) throws SQLException {
        List<User> allUsers = userService.listAllUsers();
        HttpSession session = request.getSession();
        User authUser = (User) session.getAttribute("user");
        String userEmail = authUser.getEmail();
        String forRole = "with roles: ";
        Set<Role> roles = authUser.getRoles();
        model.addAttribute("username", userEmail);
        model.addAttribute("string", forRole);
        model.addAttribute("roles", roles);
        model.addAttribute("allUsers", allUsers);
        return "admin";
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute("user") User user, HttpServletRequest req, String[] role) throws Exception {
        if (role == null) {
            return "redirect:/admin/list";
        }
        if (user.getPassword().equals("")) {
            return "redirect:/admin/list";
        }
        Set<Role> roles = new HashSet<>();
        try {
            if (req.getParameter("role").equals("admin")) {
                roles.add(userService.getRoleById(1L));
                roles.add(userService.getRoleById(2L));
            } else if (req.getParameter("role").equals("user")) {
                roles.add(userService.getRoleById(2l));
            }
        } catch (Exception e) {
            System.out.println(user.getEmail() + " not make it admin");
        }
        user.setRoles(roles);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userService.updateUser(user);
        return "redirect:/admin/list";

    }

    @PostMapping("/delete")
    public String deleteProduct(@ModelAttribute("user") User user, HttpServletRequest req,
                                Model model) throws SQLException {
        userService.deleteUser(user.getId());
        return "redirect:/admin/list";
    }

    @PostMapping("/addUser")
    public String newUserSubmit(@ModelAttribute("userForm") User user, HttpServletRequest req) throws Exception {
        Set<Role> roles = new HashSet<>();
        if (user.getPassword().startsWith("admin") && req.getParameter("role").equals("admin")) {
            roles.add(userService.getRoleById(1L));
            roles.add(userService.getRoleById(2L));
            user.setRoles(roles);
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            userService.addUser(user);
            return "redirect:/admin/list";
        } else {
            roles.add(userService.getRoleById(2L));
            user.setRoles(roles);
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            userService.addUser(user);
            return "redirect:/admin/list";
        }
    }
}






















