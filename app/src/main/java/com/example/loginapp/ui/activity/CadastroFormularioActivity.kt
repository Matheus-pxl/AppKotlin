package com.example.loginapp.ui.activity

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

        // Referências aos elementos de UI
        btRegistrar = findViewById<Button>(R.id.btRegistrar)
        val editTextNomeLogin = findViewById<EditText>(R.id.editNomeLoginFormulario)
        editEmailFormulario = findViewById<EditText>(R.id.editEmailFormulario)
        editSenhaFormulario = findViewById<EditText>(R.id.editSenhaFormulario)
        // val editTextNascimento = findViewById<EditText>(R.id.editNascimentoFormulario)

        // Configuração do listener para o botão de registro
        btRegistrar.setOnClickListener {
            // Criar objeto de usuário com base nos campos de entrada
//          val usuario = Usuario()
            //val nome = editTextNomeLogin.text.toString()
            val email = editSenhaFormulario.text.toString()
            val senha = editEmailFormulario.text.toString()
            // usuario.nascimento = editTextNascimento.text.toString()

            if (validarCampos(email, senha)) {
                sendRegisterData(email, senha)
//              PostTry()
            }
//            finish()
        }
    }

    private fun sendRegisterData(email: String, senha: String) {
        val client = OkHttpClient()
        val URL = "http://gestao.econsoft.com.br/ws/teste.php"
        val userData = Usuario(email, senha)
        val json = Json.encodeToString(userData)
        val mediaType = "application/json; charset=utf-8".toMediaTypeOrNull()
        val body = json.toRequestBody(mediaType)
        val request =
            Request
                .Builder()
                .url(URL)
                .post(body)
                .build()
        client.newCall(request).enqueue(object : okhttp3.Callback {
            override fun onFailure(call: okhttp3.Call, e: IOException) {
                runOnUiThread {
                    Toast.makeText(
                        this@CadastroFormularioActivity,
                        "erro ao enviar dados",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                runOnUiThread {
                    if (response.isSuccessful) {
                        Toast.makeText(
                            this@CadastroFormularioActivity,
                            "cadastro realizado com sucesso: $response",
                            Toast.LENGTH_LONG
                        ).show()
                    } else {
                        Toast.makeText(
                            this@CadastroFormularioActivity,
                            "erro no cadastro: $response",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        })
    }

    private fun validarCampos(email: String, senha: String): Boolean {
        return when {
            email.isEmpty() -> {
                editEmailFormulario.error = "porfavor digite um email"
                editEmailFormulario.requestFocus()
                false
            }

            senha.isEmpty() -> {
                editSenhaFormulario.error = "porfavor digite uma senha"
                editSenhaFormulario.requestFocus()
                false
            }

            senha.length < 6 -> {
                editSenhaFormulario.error = "a senha precisa ser maior que 6 digitos"
                editSenhaFormulario.requestFocus()
                false
            }

            else -> true
        }
    }

//    private fun PostTry() {
//        val usuario = Usuario()
//
//        val gson = Gson()
//        val usuarioJson = gson.toJson(usuario)
//
//        // Executar a operação de rede em uma coroutine utilizando Dispatchers.IO
//        CoroutineScope(Dispatchers.IO).launch {
//            // Instanciar o helper HTTP
//            val http = HttpHelper()
//            try {
//                // Enviar o JSON do usuário para o servidor e receber a resposta
//                val response = http.post(usuarioJson)
//
//                // Manipular a resposta dentro do contexto da thread principal (Dispatchers.Main)
//                withContext(Dispatchers.Main) {
//                    // Mostrar um Toast com a resposta recebida do servidor
//                    Toast.makeText(
//                        applicationContext,
//                        "Resposta recebida//: $response",
//                        Toast.LENGTH_LONG
//                    ).show()
//                }
//            } catch (e: Exception) {
//                // Tratar exceções, como falha na conexão
//                withContext(Dispatchers.Main) {
//                    Toast.makeText(
//                        applicationContext,
//                        "Erro ao enviar dados: ${e.message}",
//                        Toast.LENGTH_LONG
//                    ).show()
//                }
//            }
//        }
//        finish()
//    }
}

