package com.example.loginapp.model

import androidx.room.Entity
import kotlinx.serialization.Serializable

@Serializable
@Entity
data class Usuario(
    var email: String = "",
    var senha: String = ""
)