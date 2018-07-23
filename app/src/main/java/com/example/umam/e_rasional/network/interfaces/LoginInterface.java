package com.example.umam.e_rasional.network.interfaces;

import com.example.umam.e_rasional.model.User;
import com.example.umam.e_rasional.network.config.Config;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface LoginInterface {
    @FormUrlEncoded
    @POST(Config.API_LOGIN)
    Call<User> login(
            @Field("user") String username,
            @Field("pass") String password);
}
