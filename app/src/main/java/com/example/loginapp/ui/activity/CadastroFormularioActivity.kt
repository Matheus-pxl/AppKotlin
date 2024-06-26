package com.example.loginapp.ui.activity

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.loginapp.R
import com.example.loginapp.json.HttpHelper
import com.example.loginapp.model.Usuario
import com.google.gson.Gson
import org.jetbrains.anko.doAsync

class CadastroFormularioActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_formulario)

        val buttonRegistro = findViewById<Button>(R.id.btRegistrar)
        val editTextNomeLogin = findViewById<EditText>(R.id.editNomeLoginFormulario)
        val editTextSenha = findViewById<EditText>(R.id.editSenhaFormulario)
        val editTextEmail = findViewById<EditText>(R.id.editEmailFormulario)
//        val editTextNascimento = findViewById<EditText>(R.id.editNascimentoFormulario)

        buttonRegistro.setOnClickListener{
            //criar objeto usuario
            val usuario = Usuario()
            usuario.nome = editTextNomeLogin.text.toString()
            usuario.senha = editTextSenha.text.toString()
            usuario.email = editTextEmail.text.toString()
//            usuario.nascimento = editTextNascimento.text.toString()

            //converter usuario em Json
            val gson= Gson()
            val usuarioJson = gson.toJson(usuario)

            doAsync {
                val http = HttpHelper()
                http.post(usuarioJson)
            }
        }


    }
}