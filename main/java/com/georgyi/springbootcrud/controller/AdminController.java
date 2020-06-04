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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String showUserList(Model modelMap, HttpServletRequest request) throws SQLException {
        List<User> allUsers = userService.listAllUsers();
        modelMap.addAttribute("allUsers", allUsers);
        return "admin";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEditProductPage(@PathVariable(name = "id") long id) throws SQLException {
        ModelAndView mav = new ModelAndView("editPage");
        User user = userService.getUserById(id);
        mav.addObject("userEdit", user);
        List<User> roles = roleService.findAllRoles();
        mav.addObject("allRoles", roles);
        return mav;
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute("user") User user,
                              String[] roles ) throws Exception {
        if(roles==null){
            return "redirect:/admin/list";
        }
        Set<Role> userRoles = new HashSet<>();
        for(String s: roles) {
            userRoles.add(new Role(s));
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userService.updateUser(user);
        return "redirect:/admin/list";
    }

    @RequestMapping("/deleteUser/{id}")
    public String deleteProduct(@PathVariable(name = "id") long id) throws SQLException {
        userService.deleteUser(id);
        return "redirect:/admin/list";
    }
}






















