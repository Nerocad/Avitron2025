package com.nerocad.spring.dao;

import com.nerocad.spring.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager em;

    public void save(User user) {
        em.persist(user);
    }
}
