package com.android.rocknroll;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UserServiceEditarPerfil {

    @PUT("usuarios/{idusuario}")
    Call<EditarResponse> userEdit(@Body EditarRequest editarRequest, @Path("idusuario") String idusuario);

}