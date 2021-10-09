package com.android.rocknroll;

public class ApiUtilsLogin {
    public static final String BASE_URL = "https://salty-bayou-21277.herokuapp.com/";


    public static UserServiceLogin getUserServiceLogin1(){
        return RetrofitClient.getClient(BASE_URL).create(UserServiceLogin.class);
    }

}