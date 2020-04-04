package com.edu.agh.fis.RentARoom.security.user.repository;

import com.edu.agh.fis.RentARoom.security.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    List<User> findByRole(String role);
}