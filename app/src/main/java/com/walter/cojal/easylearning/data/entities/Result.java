package com.walter.cojal.easylearning.data.entities;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Result {

    private boolean success = false;
    private String message = "";
    private User user;
    private int code;
    private ArrayList<Assessor> assessors;
    @SerializedName("api_token")
    private String apiToken;

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

    public ArrayList<Assessor> getAssessors() {
        return assessors;
    }

    public String getApiToken() {
        return apiToken;
    }
}
