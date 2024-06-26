package com.example.loginapp.json

import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody


class HttpHelper {
    fun post(json: String): String {
        //definir URL do servidor

        val URL = "http://192.168.1.185:8080"
        //definir o cabecalho
        val headerHttp = "application/json; charset=utf-8".toMediaTypeOrNull()

        //criar um cliente que vai disparar a requisicao
        val client = OkHttpClient()

        //criar o body da requisição
        val body = RequestBody.create(headerHttp, json)

        //construir a requisicao POST http para o servidor
        val request = Request.Builder().url(URL).post(body).build()

        //utilizar o cliente para fazer a requisiçao e receber  a resposta
        val response = client.newCall(request).execute()

        if (!response.isSuccessful) {
            throw RuntimeException("Failed to execute POST request: ${response.code}")
        }
//        return response.body?.string() ?: "Resposta vazia"
        //return response.body().toString()
        return response.body?.string() ?: "resposta vazia"
    }
}