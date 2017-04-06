package com.shoponeo.repository;

import com.shoponeo.model.User;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface UserRepository {

    User add(User user);

    User get(String username);

    List<User> getAll();

    User update(User userModified);

    User getUserOnly(String username);

}