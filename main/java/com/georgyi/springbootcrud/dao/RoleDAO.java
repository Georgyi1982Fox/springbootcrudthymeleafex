package com.georgyi.springbootcrud.dao;

import com.georgyi.springbootcrud.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDAO extends JpaRepository<Role, Long> {
}