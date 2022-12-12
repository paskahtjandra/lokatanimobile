package com.example.tapi.model;

import com.google.gson.annotations.SerializedName;

import java.util.StringTokenizer;

public class Users {
    @SerializedName("id")
    private Integer id;

    @SerializedName("nickname")
    private String nickname;

    @SerializedName("email")
    private String email;

    @SerializedName("region")
    private String region;

    @SerializedName("username")
    private String username;

    @SerializedName("password")
    private String password;

    @SerializedName("confirmPassword")
    private String confirmPassword;

    @SerializedName("status")
    private String status;

    @SerializedName("error")
    private String error;

    @SerializedName("token")
    private String token;

    public String geterror() {
        return error;
    }

    public Users(){};

    public Users(String nickname, String email, String region, String username, String password, String confirmPassword, String status) {
        this.nickname = nickname;
        this.email = email;
        this.region = region;
        this.username = username;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.status = status;
    }

    public Users(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Integer getId() {
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

    public String getToken() { return token; }

    public void setToken(String token) {this.token = token; }
}
