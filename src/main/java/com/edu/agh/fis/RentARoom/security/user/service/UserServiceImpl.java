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

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setPasswordConfirm(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRegistrationDate(new Date());
        user.setLastLoginDate(new Date());
        userRepository.save(user);
    }

    @Override
    public void updateUserRole(Long id, Role role) {
        User user = userRepository.getOne(id);
        user.setRole(role.getAuthority());
        userRepository.save(user);
    }

    @Override
    public void updateUserPassword(User user, String password) {
        user.setPassword(bCryptPasswordEncoder.encode(password));
        user.setPasswordConfirm(bCryptPasswordEncoder.encode(password));
        userRepository.save(user);
    }

    @Override
    public void deleteUserById(Long id) {
        User user = userRepository.getOne(id);
        userRepository.delete(user);
    }

    @Override
    public List<User> findByRole(String role) {
        return userRepository.findByRole(role);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

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