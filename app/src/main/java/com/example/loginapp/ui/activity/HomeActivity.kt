package com.example.loginapp.ui.activity

import android.os.Bundle
import android.util.Log
import android.widget.GridLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.loginapp.R
import com.example.loginapp.databinding.ActivityHomeBinding
import com.example.loginapp.model.Servicos
import com.example.loginapp.model.Usuario
import com.example.loginapp.ui.adapter.ServicosAdapter
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var servicosAdapter: ServicosAdapter
    private val listaServicos:MutableList<Servicos> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        iniciarRecyclerView()
    }

    override fun onResume() {
        super.onResume()
    }
    private fun iniciarRecyclerView() {
        binding.recyclerViewServicos.layoutManager = GridLayoutManager(this,2)
        servicosAdapter = ServicosAdapter(this,listaServicos)
        binding.recyclerViewServicos.setHasFixedSize(true)
        binding.recyclerViewServicos.adapter = servicosAdapter
    }
    fun getServicos(){
        val servico1 = Servicos(R.drawable.ic_bell_icon,"nome do servico")
        listaServicos.add(servico1)
    }
}
