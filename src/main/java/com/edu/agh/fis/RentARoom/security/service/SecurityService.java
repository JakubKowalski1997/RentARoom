package com.edu.agh.fis.RentARoom.security.service;

public interface SecurityService {

    String findLoggedInUsername();

    void autologin(String username, String password);
}