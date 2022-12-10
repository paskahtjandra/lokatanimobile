package com.example.tapi.api;

import com.example.tapi.model.Users;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    @POST("users/register")
    Call<Users> registerUser(@Body Users user);
}
