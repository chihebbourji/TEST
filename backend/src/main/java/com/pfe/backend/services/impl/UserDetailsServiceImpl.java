package com.pfe.backend.services.impl;


import com.pfe.backend.dao.UserDao;
import com.pfe.backend.dao.entity.User;
import com.pfe.backend.dao.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;
    private final UserDao userDao;
    @Override
    public UserDetails loadUserByUsername(String email){
        User user =userDao.findByEmail(email);
        if(user==null){
            throw new UsernameNotFoundException(email);
        }
        return new org.springframework.security.core.userdetails.User(email, user.getPassword(), Collections.singleton(user.getRole()));
    }
}