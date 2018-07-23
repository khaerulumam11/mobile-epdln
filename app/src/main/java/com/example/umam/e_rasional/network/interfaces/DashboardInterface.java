package com.example.umam.e_rasional.network.interfaces;

import com.example.umam.e_rasional.model.Dashboard;
import com.example.umam.e_rasional.model.User;
import com.example.umam.e_rasional.network.config.Config;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface DashboardInterface {
    @FormUrlEncoded
    @POST(Config.API_DASHBOARD)
    Call<Dashboard> dashboard(
            @Field("idkegiatan") String idkegiatan,
            @Field("idperjadin") String idperjadin,
            @Field("iddelegasi") String iddelegasi);
}
