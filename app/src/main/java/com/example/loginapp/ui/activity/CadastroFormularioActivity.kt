package com.example.loginapp.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.loginapp.databinding.ActivityCadastroFormularioBinding
import com.example.loginapp.api.UserRepository
import com.example.loginapp.model.Usuario
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CadastroFormularioActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCadastroFormularioBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastroFormularioBinding.inflate(layoutInflater)
        setContentView(binding.root)
        buttonRegistrar()
    }

    private fun buttonRegistrar() {
        binding.btRegistrar.setOnClickListener {
            // Criar objeto de usuário com base nos campos de entrada
            val usuario = Usuario(
                email = binding.editEmailFormulario.text.toString(),
                senha = binding.editSenhaFormulario.text.toString()
            )
            // Validação básica dos campos
            if (usuario.email.isEmpty() || usuario.senha.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val response = UserRepository.addUser(usuario)
                    handleResponse(response.toString(), usuario)
                } catch (e: Exception) {
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
                applicationContext,
                "Resposta recebida//usuario cadastrado?: $response",
                Toast.LENGTH_LONG
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