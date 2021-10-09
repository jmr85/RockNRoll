package com.android.rocknroll;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserServiceLogin {


    @POST("login/")
    Call<Login> userLoginRegister (@Body RequestLogin loginRequest);

    @POST("login/")
    Call<ResponseLogin> login(@Body RequestLogin requestLogin);
}

