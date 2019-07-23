package com.walter.cojal.easylearning.data.Entities;

public class Result {

    private boolean success = false;
    private String message = "";
    private User user;
    private int code;

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public User getUser() {
        return user;
    }

    public int getCode() {
        return code;
    }

}
