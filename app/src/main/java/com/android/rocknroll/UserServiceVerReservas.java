package com.android.rocknroll;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserServiceVerReservas {
    @GET("eventos/")
    Call<List<Publicaciones>> verReservas();

    @POST("reserva/")
    Call<InsertarPublicacionesResponse> userServiceInsertarPublicaciones(@Body InsertarPublicacionRequest insertarpublicacionRequest);


    Call<InsertarPublicacionesResponse> userServiceInsertarPublicaciones(InsertarPublicacionRequest insertarPublicacionRequest, String id);
}
