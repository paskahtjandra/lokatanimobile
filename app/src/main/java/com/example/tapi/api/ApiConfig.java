package com.example.tapi.api;

import okhttp3.OkHttp;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiConfig {

    private static Retrofit retrofit = null;

    private static final String BaseUrl = "http://116.193.191.1:10201/";

    public static Retrofit getRetrofit(){
        if(retrofit == null){
            retrofit =  new Retrofit.Builder()
                    .baseUrl(BaseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

//    public static ApiService getApiService() {
//        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor()
//                .setLevel(HttpLoggingInterceptor.Level.BODY);
//        OkHttpClient client = new OkHttpClient().newBuilder()
//                .addInterceptor(loggingInterceptor).build();
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://116.193.191.1:10201/")
//                .addConverterFactory(GsonConverterFactory.create()).client(client).build();
//        return retrofit.create(ApiService.class);
//    }
}
