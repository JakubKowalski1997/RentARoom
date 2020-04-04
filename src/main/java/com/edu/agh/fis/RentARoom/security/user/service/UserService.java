package com.edu.agh.fis.RentARoom.security.user.service;


import com.edu.agh.fis.RentARoom.security.user.model.Role;
import com.edu.agh.fis.RentARoom.security.user.model.User;

import java.util.List;

public interface UserService {

    void save(User user);

    User findByUsername(String username);

    List<User> findByRole(String role);

    List<User> findAll();

    void updateUserRole(Long id, Role role);

    void deleteUserById(Long id);

    String setUserPassword(String username, String password);

}