package com.georgyi.springbootcrud.service;

import com.georgyi.springbootcrud.dao.RoleHibernateDAO;
import com.georgyi.springbootcrud.dao.UserDAO;
import com.georgyi.springbootcrud.dao.UserHibernateDAO;
import com.georgyi.springbootcrud.model.Role;
import com.georgyi.springbootcrud.model.User;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.sql.SQLException;
import java.util.*;

@Service
public class UserServiceImp implements UserDetailsService, UserService {
    @Autowired
    private UserDAO userDAO;

    @Autowired(required = true)
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional(readOnly = true)
    public List<User> listAllUsers() throws SQLException {
        return userDAO.listAllUsers();
    }

    @Transactional
    public void updateUser(User user) throws SQLException {
        userDAO.updateUser(user);
    }

    @Transactional
    public boolean addUser(User user) throws Exception {
        Set<Role> roles = new HashSet<>();
        roles.add(new Role(1L, "ADMIN"));
        roles.add(new Role(2L, "USER"));
        if (user.getUsername().equals("") && user.getPassword().equals("")) {
             return false;
        }else

        if (user.getUsername().startsWith("admin") && user.getPassword().startsWith("admin")) {
            user.setRoles(roles);
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            userDAO.addUser(user);
        } else {
            user.setRoles(Collections.singleton(new Role(2L, "USER")));
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            userDAO.addUser(user);
        }
           return true;
        }

    @Transactional
    public void deleteUser(Long id) throws SQLException {
        userDAO.deleteUser(id);
    }

    @Transactional
    public User getUserById(Long id) throws SQLException {
        return userDAO.getUserById(id);
    }

    @Transactional
    public User findUserByUserName(String userName) throws SQLException {
        return userDAO.findByUsername(userName);
    }

    public Role getRoleById(long id) throws SQLException {
        return userDAO.getRoleById(id);
    }

    @Transactional
    public boolean validate(String name) throws SQLException {
        return userDAO.validate(name);
    }

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = null;
        try {
            user = userDAO.findByUsername(username);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }
}
















