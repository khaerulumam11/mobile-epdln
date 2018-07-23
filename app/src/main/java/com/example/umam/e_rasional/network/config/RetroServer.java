package com.example.umam.e_rasional.network.config;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.umam.e_rasional.network.config.Config.BASE_URL;

public class RetroServer {

    private static Retrofit retrofit;


    public static Retrofit getClient()
    {
        if(retrofit == null)
        {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return  retrofit;
    }
}
