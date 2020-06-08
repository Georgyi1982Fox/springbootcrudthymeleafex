package com.georgyi.springbootcrud.service;

import com.georgyi.springbootcrud.model.Role;
import com.georgyi.springbootcrud.model.User;
import java.sql.SQLException;
import java.util.List;

public interface UserService {
    List<User> listAllUsers() throws SQLException;
    void addUser(User user) throws Exception;
    void updateUser(User user) throws SQLException;
    void deleteUser(Long id) throws SQLException;
    User getUserById(Long id) throws SQLException;
    boolean validate(String name) throws SQLException;
    Role getRoleById(long id) throws SQLException;
    User findUserByUserName(String userName) throws SQLException;
}

