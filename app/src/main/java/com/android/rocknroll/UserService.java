package com.android.rocknroll;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserService {

    @POST("usuarios/")
    Call<ResponseLogin> userLoginRegister (@Body RequestLogin loginRequest);


}
