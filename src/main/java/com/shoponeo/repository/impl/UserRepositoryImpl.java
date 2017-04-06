package com.shoponeo.repository.impl;

import com.shoponeo.model.User;
import com.shoponeo.repository.UserRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public User add(User user) {
        entityManager.persist(user);
        return user;
    }

    @Override
    public User get(String username) {
        return entityManager.find(User.class , username);
    }

    @SuppressWarnings("JpaQlInspection")
    @Override
    public List<User> getAll() {
        List<User> list = entityManager.createQuery("select u from User u").getResultList();
        return list;
    }

    @Override
    public User update(User userModified) {
        return entityManager.merge(userModified);
    }

    @Override
    public User getUserOnly(String username) {
        Query query = entityManager.createQuery("select new com.shoponeo.model.User(user.username, user.password) from User user where user.username = :username");
        query.setParameter("username", username);
        return (User) query.getResultList().get(0);
    }
}
