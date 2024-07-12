package com.example.loginapp.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.loginapp.databinding.ActivityCadastroFormularioBinding
import com.example.loginapp.http.HttpHelper
import com.example.loginapp.http.RetrofitInstance
import com.example.loginapp.http.UserData
import com.example.loginapp.model.Usuario
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpException
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException

class CadastroFormularioActivity : AppCompatActivity() {

    private lateinit var binding:ActivityCadastroFormularioBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastroFormularioBinding.inflate(layoutInflater)
        setContentView(binding.root)
        buttonRegistrar()
    }

    private fun buttonRegistrar2() {
        binding.btRegistrar.setOnClickListener {
            // Criar objeto de usuário com base nos campos de entrada
            val usuario = Usuario()
            val email = binding.editEmailFormulario.text.toString()
            val senha = binding.editSenhaFormulario.text.toString()
            if (email.isNotEmpty() && senha.isNotEmpty()) {
                val postRequest = UserData(email, senha)
                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        val response = RetrofitInstance.api.register(postRequest)
                        if (response.isSuccessful && response.body() != null) {
                            val createdPost = response.body()!!
                            Log.d("api", "Post criado com sucesso: $createdPost")
                            // Lógica adicional após o post ser criado com sucesso
                        } else {
                            Log.e("api", "Erro ao criar o post: ${response.code()}")
                        }
                    } catch (e: IOException) {
                        Log.e(
                            "api",
                            "IOException, você pode não ter conexão com a internet: ${e.message}"
                        )
                    } catch (e: HttpException) {
                        Log.e("api", "HttpException, resposta inesperada: ${e.message}")
                    }
                }
            }
        }
    }

    private fun buttonRegistrar() {
        binding.btRegistrar.setOnClickListener {
            // Criar objeto de usuário com base nos campos de entrada
            val usuario = Usuario()
            val senha = binding.editSenhaFormulario.text.toString()
            val email = binding.editEmailFormulario.text.toString()
            // Validação básica dos campos
            if (email.isEmpty() || senha.isEmpty()) {
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
                    finish()
                } catch (e: Exception) {
                    // Tratar exceções, como falha na conexão
                    handleException(e)
                }
            }
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
                applicationContext, "Resposta recebida//usuario cadastrado?: $response", Toast.LENGTH_LONG
            ).show()
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
}