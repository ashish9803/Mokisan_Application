package com.farmhike.mokisan.Api;


import com.farmhike.mokisan.Models.User.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface UserApi {

    // Creating User
    @POST("/user")
    Call<User> cUser(
            @Query("api_key") String apiKey,
            @Body User user
    );

    // Getting User Details
    @GET("/user")
    Call<User> gUser(
            @Query("api_key") String apiKey
    );

    // Updating User Details
    @PUT("/user")
    Call<User> uUser(
            @Query("api_key") String apiKey
    );

}
