package com.example.umam.e_rasional.API;

public class AppVar {
    //URL to our login.php file, url bisa diganti sesuai dengan alamat server kita
    public static final String LOGIN_URL = "http://10.2.7.238/e-pdln/login/login_android.php";

    //Keys for email and password as defined in our $_POST['key'] in login.php
    public static final String KEY_EMAIL = "user";
    public static final String KEY_PASSWORD = "pass";

    //If server response is equal to this that means login is successful
    public static final String LOGIN_SUCCESS = "success";
}
