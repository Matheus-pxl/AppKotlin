package com.example.loginapp.http

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import java.io.IOException


class HttpHelper {
    fun post(json: String): String {
        // Definir URL do servidor
        val URL = "http://192.168.1.185:8080"

        // Definir o cabeçalho
        val headerHttp = "application/json; charset=utf-8".toMediaTypeOrNull()

        // Criar um cliente que vai disparar a requisição
        val client = OkHttpClient()

        // Criar o body da requisição
        val body = RequestBody.create(headerHttp, json)

        // Construir a requisição POST HTTP para o servidor
        val request = Request.Builder()
            .url(URL)
            .post(body)
            .build()

        try {
            // Utilizar o cliente para fazer a requisição e receber a resposta
            val response = client.newCall(request).execute()

            // Verificar se a requisição foi bem-sucedida
            if (!response.isSuccessful) {
                throw RuntimeException("Failed to execute POST request: ${response.code}")
            }

            // Ler e retornar o corpo da resposta como String
            return response.body?.string() ?: "Resposta vazia"
        } catch (e: Exception) {
            // Tratar exceções de rede ou outras exceções
            e.printStackTrace()
            return "Erro ao fazer a requisição: ${e.message}"
        }
    }
    fun get(): String {

        //definir url do servidor
        val URL = ""

        // Criar um cliente que vai disparar a requisição
        val client = OkHttpClient()

        // Construir a requisição GET HTTP
        val request = Request.Builder()
            .url(URL)
            .get()
            .build()

        try {
            // Utilizar o cliente para fazer a requisição e receber a resposta
            val response = client.newCall(request).execute()

            // Verificar se a requisição foi bem-sucedida
            if (!response.isSuccessful) {
                throw RuntimeException("Failed to execute GET request: ${response.code}")
            }

            // Ler e retornar o corpo da resposta como String
            return response.body?.string() ?: "Resposta vazia"
        } catch (e: IOException) {
            // Tratar exceções de IO, como falha na conexão
            e.printStackTrace()
            return "Erro de conexão: ${e.message}"
        } catch (e: Exception) {
            // Tratar outras exceções
            e.printStackTrace()
            return "Erro ao fazer a requisição: ${e.message}"
        }
    }
}