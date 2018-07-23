package com.example.umam.e_rasional.network.interfaces;

import com.example.umam.e_rasional.model.ApprovalModel;
import com.example.umam.e_rasional.model.ResponsModel;
import com.example.umam.e_rasional.model.User;
import com.example.umam.e_rasional.network.config.Config;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ReadData {

    @GET(Config.API_READ)
    Call<ResponsModel> getTampil();
}
