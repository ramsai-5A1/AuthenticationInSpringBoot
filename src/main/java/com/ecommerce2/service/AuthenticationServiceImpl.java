package com.ecommerce2.service;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.ecommerce2.dto.UserRequest;
import com.ecommerce2.dto.UserResponse;
import com.ecommerce2.entity.User;
import com.ecommerce2.exception.InvalidCredentialsException;
import com.ecommerce2.exception.UserNotFoundException;
import com.ecommerce2.repository.UserRepositoryDao;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private UserRepositoryDao userRepositoryDao;

    public AuthenticationServiceImpl(UserRepositoryDao userRepositoryDao) {
        this.userRepositoryDao = userRepositoryDao;
    }

    @Override
    public UserResponse handleSignup(UserRequest request) {
        request.setPassword(hashPassword(request.getPassword()));
        User user = convertFromUserRequestToUser(request);
        User savedUser = userRepositoryDao.save(user);
        return convertFromUserToUserResponse(savedUser);
    }

    @Override
    public UserResponse handleLogin(UserRequest request) {
        Optional<User> userObj = userRepositoryDao.findByUserName(request.getUserName());
        if (!userObj.isPresent()) {
            throw new UserNotFoundException("Please signup first before logging in");
        }

        User user = userObj.get();
        if (checkPassword(user.getHashedPassword(), request.getPassword())) {
            return convertFromUserToUserResponse(user);
        } else {
            throw new InvalidCredentialsException("Please enter correct details");
        }
    }

    private User convertFromUserRequestToUser(UserRequest request) {
        return User.builder()
                .userName(request.getUserName())
                .hashedPassword(request.getPassword())
                .build();
    }

    private UserResponse convertFromUserToUserResponse(User user) {
        return UserResponse.builder()
            .userId(user.getId())
            .userName(user.getUserName())
            .build();
    }

    private String hashPassword(String plainPassword) {
        int workFactor = 12;
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt(workFactor));
    }

    private boolean checkPassword(String hashedPassword, String plainPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
}
