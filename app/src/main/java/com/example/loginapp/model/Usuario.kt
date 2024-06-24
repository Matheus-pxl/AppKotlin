package com.example.loginapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import java.util.Date

/**
 * Entidade que representa um usuário no banco de dados.
 * @property id Identificador único do usuário (chave primária).
 * @property nome Nome do usuário.
 * @property email Email do usuário.
 * @property nascimento Data de nascimento do usuário, convertida utilizando DateConverter.
 * @property senha Senha do usuário.
 */
@Entity
class Usuario(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    @ColumnInfo(name = "nome")
    var nome: String?,

    @ColumnInfo(name = "email")
    var email: String?,

    @ColumnInfo(name = "senha")
    var senha: String?,

    @TypeConverters(DateConverter::class)
    @ColumnInfo(name = "nascimento")
    var nascimento: Date?
)