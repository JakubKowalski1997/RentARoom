package com.edu.agh.fis.RentARoom.security.user.service;

import com.edu.agh.fis.RentARoom.security.user.model.Role;
import com.edu.agh.fis.RentARoom.security.user.model.User;
import com.edu.agh.fis.RentARoom.security.user.repository.UserRepository;
import com.edu.agh.fis.RentARoom.security.user.utils.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
/**
 * User service
 *
 *
 * @author  Jakub Kowalski
 * @version 1.0
 * @since   2020-05-30
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * This method is used to save user in db
     * @param user user that will be sent to db
     */
    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setPasswordConfirm(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRegistrationDate(new Date());
        user.setLastLoginDate(new Date());
        userRepository.save(user);
    }

    /**
     * This method is used to update user role in db
     * @param id id of user
     * @param role new role to update
     */
    @Override
    public void updateUserRole(Long id, Role role) {
        User user = userRepository.getOne(id);
        user.setRole(role.getAuthority());
        userRepository.save(user);
    }

    /**
     * This method is used to update user password in db
     * @param user to change password
     * @param password new password
     */
    @Override
    public void updateUserPassword(User user, String password) {
        user.setPassword(bCryptPasswordEncoder.encode(password));
        user.setPasswordConfirm(bCryptPasswordEncoder.encode(password));
        userRepository.save(user);
    }

    /**
     * This method is used to delete user in db
     * @param id id of user
     */
    @Override
    public void deleteUserById(Long id) {
        User user = userRepository.getOne(id);
        userRepository.delete(user);
    }

    /**
     * This method is used to find user by role in db
     * @param role role of searched users
     */
    @Override
    public List<User> findByRole(String role) {
        return userRepository.findByRole(role);
    }

    /**
     * This method is used to find user by username in db
     * @param username username
     */
    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    /**
     * This method is used to find all users in db
     */
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public String setUserRandomPassword(String username) {
        User user = findByUsername(username);

        String password = PasswordUtils.generateTemporaryPassword();

        user.setPassword(bCryptPasswordEncoder.encode(password));
        user.setRegistrationDate(new Date());
        user.setLastLoginDate(new Date());
        userRepository.save(user);
        return password;
    }
}