package com.example.loginapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity

/**
 * Entidade que representa um usuário no banco de dados.
 * @property id Identificador único do usuário (chave primária).
 * @property nome Nome do usuário.
 * @property email Email do usuário.
 * @property nascimento Data de nascimento do usuário, convertida utilizando DateConverter.
 * @property senha Senha do usuário.
 */
@Entity
class Usuario {
//    @PrimaryKey(autoGenerate = true)
//    var id: Int = 0
//
//    @ColumnInfo(name = "nome")
//    var nome: String? = ""

    @ColumnInfo(name = "email")
    var email: String? = ""

    @ColumnInfo(name = "senha")
    var senha: String? = ""

//    override  fun toString():String{
//        return "Usuario (nome '$nome', email='$email', senha = '$senha')"
//    }
//    @TypeConverters(DateConverter::class)
//    @ColumnInfo(name = "nascimento")
//    var nascimento: Date?
}