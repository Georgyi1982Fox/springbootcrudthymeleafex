package com.georgyi.springbootcrud.dao;

import com.georgyi.springbootcrud.model.Role;
import com.georgyi.springbootcrud.model.User;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class RoleHibernateDAO {

    @Autowired
    private EntityManager entityManager;

    public List<User> findAllRoles(){
        return entityManager.createQuery("SELECT r FROM Role r").getResultList();
    }

    public Role getByName(String name) {
        Session currentSession = entityManager.unwrap(Session.class);
        Role role = currentSession.get(Role.class, name);
        return role;
    }
}
