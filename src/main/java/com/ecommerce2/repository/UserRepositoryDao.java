package com.ecommerce2.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce2.entity.User;

@Repository
public interface UserRepositoryDao extends JpaRepository<User, Long> {

    Optional<User> findByUserName(String userName);
    
}
