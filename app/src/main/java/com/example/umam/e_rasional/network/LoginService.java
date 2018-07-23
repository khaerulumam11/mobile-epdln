package com.example.umam.e_rasional.network;


import android.content.Context;

import com.example.umam.e_rasional.network.config.RetrofitBuilder;
import com.example.umam.e_rasional.network.interfaces.LoginInterface;

import retrofit2.Callback;

public class LoginService {
    private LoginInterface loginInterface;

    public LoginService(Context context) {
        loginInterface = RetrofitBuilder.builder(context)
                .create(LoginInterface.class);
    }

    public void doLogin(String username, String password, Callback callback) {
        loginInterface.login(username, password).enqueue(callback);
    }
}
