package com.android.rocknroll;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PublicacionesAPI {


    @GET("eventos")
    Call<List<Publicaciones>> getPosts();
}
