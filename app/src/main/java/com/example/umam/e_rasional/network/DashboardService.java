package com.example.umam.e_rasional.network;


import android.content.Context;

import com.example.umam.e_rasional.network.config.RetrofitBuilder;
import com.example.umam.e_rasional.network.interfaces.DashboardInterface;
import com.example.umam.e_rasional.network.interfaces.LoginInterface;

import retrofit2.Callback;

public class DashboardService {
    private DashboardInterface loginInterface;

    public DashboardService(Context context) {
        loginInterface = RetrofitBuilder.builder(context)
                .create(DashboardInterface.class);
    }

    public void doDashboard(String idkegiatan, String idperjadin, String iddelegasi, Callback callback) {
        loginInterface.dashboard(idkegiatan, idperjadin,iddelegasi).enqueue(callback);
    }
}
