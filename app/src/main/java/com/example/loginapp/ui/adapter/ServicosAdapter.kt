package com.example.loginapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.loginapp.R
import com.example.loginapp.databinding.ServicosItemBinding
import com.example.loginapp.model.Servicos

class ServicosAdapter(
    private val context: Context,
    private val listaServicos: MutableList<Servicos>
) :
    RecyclerView.Adapter<ServicosAdapter.ServicosViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServicosViewHolder {
        val itemLista = ServicosItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return ServicosViewHolder(itemLista)
    }

    override fun getItemCount() = listaServicos.size

    override fun onBindViewHolder(holder: ServicosViewHolder, position: Int) {
        val servico = listaServicos[position]
        holder.bind(servico)
    }

    inner class ServicosViewHolder(private val binding: ServicosItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(servico: Servicos) {
            binding.apply {
                imgServico.setImageResource(servico.img ?: R.drawable.ic_bell_icon)
                textNomeServico.text = servico.nome
                // Aqui você pode definir outros bindings conforme necessário
            }
        }
    }
}
