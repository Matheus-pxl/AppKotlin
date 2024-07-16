package com.example.loginapp.model

import androidx.room.Entity
import kotlinx.serialization.Serializable

@Serializable
@Entity
data class Usuario(
    val id:Int,
    val email: String,
    val senha: String
)