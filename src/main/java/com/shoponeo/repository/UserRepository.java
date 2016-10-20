package com.shoponeo.repository;

import com.shoponeo.model.User;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Tomasz on 20.10.2016.
 */
@Repository
@Transactional
public interface UserRepository {

    User add(User user);

    User get(String username);

    List<User> getAll();

    User update(User userModified );

}