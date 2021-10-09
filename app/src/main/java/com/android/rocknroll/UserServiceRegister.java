package com.android.rocknroll;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserServiceRegister {

    @POST("usuarios/")
    Call<RegistrarseResponse> userLoginRegister (@Body RegistrarseRequest registrarseRequest);
}
