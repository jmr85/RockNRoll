package com.android.rocknroll;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Path;

public interface UserServiceEliminarUsuario {

    @DELETE("usuarios/{idusuario}")
    Call<EliminarResponse> userEliminar(@Body EliminarRequest eliminarRequest, @Path("idusuario") String idusuario);
}