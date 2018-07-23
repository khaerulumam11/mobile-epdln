package com.example.umam.e_rasional.network.config;

public class Config {
    public static final String BASE_URL = "http://10.2.7.149/"; // Your Local IP Address or Localhost (http://10.0.2.2/)

    public static final String API_URL = BASE_URL + "e-pdln";

    public static final String API_LOGIN = API_URL + "/login/login_android.php";
    public static final String API_READ= "e-pdln/daftarperjadin.php";
    public static final String API_DASHBOARD = API_URL + "/dashboard_sekjen.php";
    public static final String API_REGISTER = API_URL + "/register.php";
}
