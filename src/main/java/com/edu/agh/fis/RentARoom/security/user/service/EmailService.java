package com.edu.agh.fis.RentARoom.security.user.service;

public interface EmailService {

    void sendTempPasswdMessage(String to, String user, String newTempPasswd);

    void sendMessageToRoomOwner(String to, String messageToRoomOwner);
}
