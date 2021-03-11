package org.geektimes.projects.user.service;

import org.geektimes.projects.user.domain.User;
import org.geektimes.projects.user.sql.LocalTransactional;
import org.geektimes.projects.user.validator.bean.validation.UserValid;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

public class UserServiceImpl implements UserService {

    @Resource(name = "bean/EntityManager")
    private EntityManager entityManager;

    @Resource(name = "bean/Validator")
    private Validator validator;

    @Override
    // 默认需要事务
    @LocalTransactional
    public boolean register(User user) throws Throwable{
        // before process
        EntityTransaction transaction = entityManager.getTransaction();
        Set<ConstraintViolation<User>> set = validator.validate(user);

        transaction.begin();
        // 主调用
        entityManager.persist(user);

        transaction.commit();

        return false;
    }

    @Override
    public boolean deregister(User user) {
        return false;
    }

    @Override
    @LocalTransactional
    public boolean update(User user) {
        return false;
    }

    @Override
    public User queryUserById(Long id) {
        return null;
    }

    @Override
    public User queryUserByNameAndPassword(String name, String password) {
        return null;
    }
}
