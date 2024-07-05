package com.example.loginapp.database.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.loginapp.model.Usuario

/**
 * Classe abstrata que serve como ponto de acesso principal para o banco de dados da aplicação.
 * Esta classe é anotada com @Database para indicar que é uma classe Room Database.
 * @property entities Define as entidades que compõem o banco de dados (no caso, apenas a entidade Usuario).
 * @property version Versão do banco de dados. Aumente o número da versão quando houver mudanças no esquema do banco de dados.
 * @property exportSchema Indica se o esquema do banco de dados deve ser exportado. Por padrão, é false.
 * @property typeConverters Lista de classes de conversores que serão usadas para converter tipos não primitivos em tipos primitivos que o Room pode persistir.
 */
@Database(entities = [Usuario::class], version = 1, exportSchema = false)
abstract class database : RoomDatabase() {

    /**
     * Método abstrato que retorna uma instância do DAO (Data Access Object) para a entidade Usuario.
     * Este método será implementado pelo Room Database.
     * @return Instância do UsuarioDAO para realizar operações de acesso a dados relacionadas à entidade Usuario.
     */
    abstract fun usuarioDAO(): UsuarioDAO
}
