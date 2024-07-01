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

class CadastroFormularioActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_formulario)
        setTitle("Cadastro")

        // Referências aos elementos de UI
        val buttonRegistro = findViewById<Button>(R.id.btRegistrar)
        val editTextNomeLogin = findViewById<EditText>(R.id.editNomeLoginFormulario)
        val editTextSenha = findViewById<EditText>(R.id.editSenhaFormulario)
        val editTextEmail = findViewById<EditText>(R.id.editEmailFormulario)
        // val editTextNascimento = findViewById<EditText>(R.id.editNascimentoFormulario)

        // Configuração do listener para o botão de registro
        buttonRegistro.setOnClickListener {
            // Criar objeto de usuário com base nos campos de entrada
            val usuario = Usuario()
                usuario.nome = editTextNomeLogin.text.toString()
                usuario.senha = editTextSenha.text.toString()
                usuario.email = editTextEmail.text.toString()
                // nascimento = editTextNascimento.text.toString()

            // Converter o objeto usuário para JSON usando Gson
            val gson = Gson()
            val usuarioJson = gson.toJson(usuario)

            // Executar a operação de rede em uma coroutine utilizando Dispatchers.IO
            CoroutineScope(Dispatchers.IO).launch {
                // Instanciar o helper HTTP
                val http = HttpHelper()
                try {
                    // Enviar o JSON do usuário para o servidor e receber a resposta
                    val response = http.post(usuarioJson)

                    // Manipular a resposta dentro do contexto da thread principal (Dispatchers.Main)
                    withContext(Dispatchers.Main) {
                        // Mostrar um Toast com a resposta recebida do servidor
                        Toast.makeText(applicationContext, "Resposta recebida//: $response", Toast.LENGTH_LONG).show()
                    }
                } catch (e: Exception) {
                    // Tratar exceções, como falha na conexão
                    withContext(Dispatchers.Main) {
                        Toast.makeText(applicationContext, "Erro ao enviar dados: ${e.message}", Toast.LENGTH_LONG).show()
                    }
                }
            }
                    // Finalizar a activity após o registro (opcional)
            finish()
        }
    }
}
