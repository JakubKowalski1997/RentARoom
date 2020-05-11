package com.edu.agh.fis.RentARoom.security.DTOs;

public class ChangePasswordResponse {
    private boolean changed;
    private String errorMessage;

    public ChangePasswordResponse(){}

    public ChangePasswordResponse(boolean changed, String errorMessage){
        this.changed = changed;
        this.errorMessage = errorMessage;
    }

}
