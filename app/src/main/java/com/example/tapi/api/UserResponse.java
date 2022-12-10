package com.example.tapi.api;

import com.example.tapi.model.Users;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserResponse {
    @SerializedName("users")
    private List<Users> users;

    @SerializedName("status")
    private boolean status;

    public List<Users> getData() {
        return users;
    }

    public boolean isStatus() {
        return status;
    }
}
