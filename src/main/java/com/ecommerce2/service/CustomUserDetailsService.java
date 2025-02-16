package com.ecommerce2.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ecommerce2.entity.User;
import com.ecommerce2.exception.UserNotFoundException;
import com.ecommerce2.repository.UserRepositoryDao;
import com.ecommerce2.utils.CustomUserDetails;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepositoryDao userRepositoryDao;

    public CustomUserDetailsService(UserRepositoryDao userRepositoryDao) {
        this.userRepositoryDao = userRepositoryDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepositoryDao.findByUserName(username)  
                                        .orElseThrow(() -> new UserNotFoundException("User not found " + username));

        return new CustomUserDetails(user);
    }

}
