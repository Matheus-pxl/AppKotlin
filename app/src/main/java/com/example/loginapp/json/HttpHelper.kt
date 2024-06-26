package com.example.loginapp.json

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody


class HttpHelper {
    fun post(json: String) :String{
        //definir URL do servidor
        val URL = "endereço aqui"
        //definir o cabecalho
        val headerHttp = "application/json; charset-utf-8".toMediaTypeOrNull()

        //criar um cliente que vai disparar a requisicao
        val client = OkHttpClient()

        //criar o body da requisição
        val body = RequestBody.create(headerHttp, json)

        //construir a requisicao POST http para o servidor
        val request = Request.Builder().url(URL).post(body).build()

        //utilizar o cliente para fazer a requisiçao e receber  a resposta
        val response = client.newCall(request).execute()

//        return response.body?.string() ?: "Resposta vazia"
        //return response.body().toString()
        return response.body.toString()
    }
}