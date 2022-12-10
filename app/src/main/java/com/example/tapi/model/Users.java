package com.example.tapi.model;

import com.google.gson.annotations.SerializedName;

public class Users {
    @SerializedName("id")
    private String id;

    @SerializedName("nickname")
    private String nickname;

    @SerializedName("email")
    private String email;

    private String region;

    private String username;

    private String password;

    private String status;



    public String getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }

    public String getEmail() {
        return email;
    }

    public String getRegion() {
        return region;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getStatus() {
        return status;
    }
}
