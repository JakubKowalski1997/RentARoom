package com.edu.agh.fis.RentARoom.security.user.service;

public interface EmailService {

    void sendConfirmMessage(String to, String newUser, String newUserEmail);

    void sendTempPasswdMessage(String to, String user, String newTempPasswd);
}
