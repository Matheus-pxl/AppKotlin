package com.example.loginapp.ui.activity

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.loginapp.databinding.ActivityLoginBinding
import com.google.android.material.snackbar.Snackbar

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Infla o layout usando View Binding para a LoginActivity
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        // Configura o OnClickListener para o botão de login
        binding.btLogin.setOnClickListener {
            val nome = binding.editNome.text.toString()
            val senha = binding.editSenha.text.toString()

            // Verifica se os campos estão preenchidos corretamente
            when {
                nome.isEmpty() -> {
                    mensagem(it, "Digite um nome de usuário!")
                }
                senha.isEmpty() -> {
                    mensagem(it, "Digite uma senha!")
                }
                senha.length <= 5 -> {
                    mensagem(it, "A senha precisa ter pelo menos 6 caracteres!")
                }
                else -> {
                    // Se os campos estão corretos, navega para a tela Home
                    navegarParaHome(nome)
                }
            }
        }

        // Configura o OnClickListener para o botão de configurações
        binding.btConfig.setOnClickListener {
            navegarParaConfigs()
        }
        binding.btCriarConta.setOnClickListener{
            navegarParaCriarConta()
        }

    }

    // Função para exibir uma mensagem usando Snackbar
    private fun mensagem(view: View, mensagem: String) {
        val snackbar = Snackbar.make(view, mensagem, Snackbar.LENGTH_SHORT)
        snackbar.setBackgroundTint(Color.parseColor("#FF0000")) // Cor de fundo vermelha
        snackbar.setTextColor(Color.parseColor("#FFFFFF"))     // Texto branco
        snackbar.show()
    }

    // Função para navegar para a tela HomeActivity e passar o nome de usuário como extra
    private fun navegarParaHome(nome: String) {
        val intent = Intent(this, HomeActivity::class.java)
        intent.putExtra("nome", nome)
        startActivity(intent)
    }

    // Função para navegar para a tela ConfigActivity
    private fun navegarParaConfigs() {
        val intent = Intent(this, ConfigActivity::class.java)
        startActivity(intent)
    }
    private fun navegarParaCriarConta(){
        val intent = Intent(this, CadastroFormularioActivity::class.java)
        startActivity(intent)
    }
}
