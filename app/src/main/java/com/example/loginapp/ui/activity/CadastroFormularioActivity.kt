package com.example.loginapp.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.loginapp.R
import com.example.loginapp.http.HttpHelper
import com.example.loginapp.model.Usuario
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import android.util.Log
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException

class CadastroFormularioActivity : AppCompatActivity() {
    private lateinit var editEmailFormulario: EditText
    private lateinit var editSenhaFormulario: EditText
    private lateinit var btRegistrar: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_formulario)
        setTitle("Cadastro")

        initViews()
        buttonRegistrar()
    }

    private fun buttonRegistrar() {
        btRegistrar.setOnClickListener {
            // Criar objeto de usuário com base nos campos de entrada
            val usuario = Usuario()
            usuario.senha = editSenhaFormulario.text.toString()
            usuario.email = editEmailFormulario.text.toString()

            // Validação básica dos campos
            if (usuario.email.isNullOrEmpty() || usuario.senha.isNullOrEmpty()) {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val gson = Gson()
            val usuarioJson = gson.toJson(usuario)
            Log.d("API_REQUEST", "JSON enviado para a API: $usuarioJson")

            // Executar a operação de rede em uma coroutine utilizando Dispatchers.IO
            CoroutineScope(Dispatchers.IO).launch {
                // Instanciar o helper HTTP
                val http = HttpHelper()
                try {
                    // Enviar o JSON do usuário para o servidor e receber a resposta
                    val response = http.post(usuarioJson)
                    // Manipular a resposta dentro do contexto da thread principal (Dispatchers.Main)
                    handleResponse(response, usuario)
                } catch (e: Exception) {
                    // Tratar exceções, como falha na conexão
                    handleException(e)
                }
            }
            finish()
        }
    }

    private suspend fun handleException(e: Exception) {
        withContext(Dispatchers.Main) {
            Toast.makeText(
                applicationContext,
                "Erro ao enviar dados: ${e.message}",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private suspend fun handleResponse(
        response: String,
        usuario: Usuario
    ) {
        withContext(Dispatchers.Main) {
            // Mostrar um Toast com a resposta recebida do servidor
            Toast.makeText(
                applicationContext, "Resposta recebida//: $response", Toast.LENGTH_LONG
            ).show()
            navegarParaHome(usuario)
        }
    }

    private fun navegarParaHome(usuario: Usuario) {
        val intent = Intent(applicationContext, HomeActivity::class.java)
        intent.putExtra(
            "email",
            usuario.email
        ) // Se precisar enviar a resposta para a próxima Activity
        startActivity(intent)
    }

    private fun initViews() {
        // Referências aos elementos de UI
        btRegistrar = findViewById<Button>(R.id.btRegistrar)
        editEmailFormulario = findViewById<EditText>(R.id.editEmailFormulario)
        editSenhaFormulario = findViewById<EditText>(R.id.editSenhaFormulario)
    }
}