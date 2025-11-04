package com.nerocad.spring.service;

import com.nerocad.spring.dao.UserDao;
import com.nerocad.spring.dao.UserDaoImpl;
import com.nerocad.spring.entity.User;
import org.jvnet.hk2.annotations.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    private final UserDaoImpl userDaoImpl;

    public UserService(UserDaoImpl userDaoImpl) {
        this.userDaoImpl = userDaoImpl;
    }

    @Transactional
    public void save(User user) {
        userDaoImpl.save(user);
    }
}
