package com.edu.agh.fis.RentARoom.security.user.DTOs;

/**
 * ChangePasswordResponse
 *
 *
 * @author  Jakub Kowalski
 * @version 1.0
 * @since   2020-05-30
 */
public class ChangePasswordResponse {
    private boolean isProper;
    private String errorMessage;

    public ChangePasswordResponse(boolean isProper, String errorMessage){
        this.isProper = isProper;
        this.errorMessage = errorMessage;
    }

    public boolean isProper() {
        return isProper;
    }

    public void setProper(boolean proper) {
        this.isProper = proper;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
