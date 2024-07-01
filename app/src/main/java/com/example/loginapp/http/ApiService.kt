package com.example.loginapp.http;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

interface ApiService {

    @POST("http://gestao.econsoft.com.br/ws/teste.php")
    abstract fun postJson(
        @Body json:Map<String, String>
    ): Call<ResponseBody>
}
