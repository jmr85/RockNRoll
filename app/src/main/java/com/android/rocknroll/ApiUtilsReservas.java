package com.android.rocknroll;

public class ApiUtilsReservas {

    public static final String BASE_URL = " https://recitalestaller6.herokuapp.com/";


    public static UserServiceVerReservas getUserServiceVerReservas1(){
        return RetrofitClient.getClient(BASE_URL).create(UserServiceVerReservas.class);
    }
}
