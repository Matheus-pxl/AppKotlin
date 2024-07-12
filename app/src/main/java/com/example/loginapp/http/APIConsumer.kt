package com.example.loginapp.http

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface APIConsumer {
    @POST("/ws/teste.php")
    suspend fun register(
        @Body registerRequest: UserData
    ): Response<UserData>


    @POST("/ws/teste.php")
    @FormUrlEncoded
    suspend fun login(
        @Field("email") email: String,
        @Field("senha") senha: String
    ): Response<UserData>}