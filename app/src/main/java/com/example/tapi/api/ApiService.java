package com.example.tapi.api;

import com.example.tapi.model.Produks;
import com.example.tapi.model.Users;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiService {
    @POST("user/register")
    Call<Users> registerUser(@Body Users user);

    @POST("user/login")
    Call<Users> userLogin(@Body Users user);

    @GET("user/")
    Call<Users> getName(@Header("Authorization") String token);

    @GET("produk/productlist")
    Call<List<Produks>> getProduks();
}
