package com.shoponeo.service.impl;

import com.shoponeo.repository.UserRepository;
import com.shoponeo.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by Tomasz on 20.10.2016.
 */
@Service
public class CustomUserDetailsServiceImpl implements CustomUserDetailsService{


    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.get(s);
    }
}
