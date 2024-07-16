package com.example.loginapp.api

import com.example.loginapp.model.Usuario
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object UserRepository {
    private const val BASE_URL = "http://gestao.econsoft.com.br/ws/"
    private val service: UserService

    init{
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service = retrofit.create(UserService::class.java)
    }
    fun addUser(usuario:Usuario):UserApiResult?{
        val call = service.addUser(usuario)
        return call.execute().body()
    }
    fun getUser(id:Int):UserApiResult?{
        val call = service.getUser(id)
        return call.execute().body()
    }
}