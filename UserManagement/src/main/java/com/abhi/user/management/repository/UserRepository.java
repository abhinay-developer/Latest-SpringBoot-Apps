package com.abhi.user.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abhi.user.management.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

public User findByEmailAndPassword(String email, String password);

public User findByEmail(String email);

}
