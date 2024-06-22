package com.example.loginapp.database.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.loginapp.model.Usuario

/**
 * Interface DAO (Data Access Object) para acesso aos dados da entidade Usuario no banco de dados.
 */
@androidx.room.Dao
interface UsuarioDAO {

    /**
     * Retorna todos os objetos Usuario presentes no banco de dados.
     * @return Lista de todos os objetos Usuario.
     */
    @Query("select * from Usuario")
    fun getAll(): List<Usuario>

    /**
     * Retorna objetos Usuario cujos IDs estão contidos no array userIds.
     * @param userIds Array de IDs dos usuários a serem recuperados.
     * @return Lista de objetos Usuario correspondentes aos IDs fornecidos.
     */
    @Query("select * from Usuario where id in (:userIds)")
    fun LoadAllByIds(userIds: IntArray): List<Usuario>

    /**
     * Retorna um objeto Usuario cujo nome corresponde ao valor fornecido pelo parâmetro nome.
     * @param nome Nome do Usuario a ser encontrado.
     * @return Objeto Usuario com o nome especificado.
     */
    @Query("select * from Usuario where nome like :nome")
    fun findByName(nome: String): Usuario

    /**
     * Insere um ou mais objetos Usuario no banco de dados.
     * @param users Lista de objetos Usuario a serem inseridos.
     */
    @Insert
    fun insertAll(vararg users: Usuario)

    /**
     * Remove um objeto Usuario do banco de dados.
     * @param user Objeto Usuario a ser removido.
     */
    @Delete
    fun delete(user: Usuario)

    /**
     * Atualiza um objeto Usuario no banco de dados.
     * @param user Objeto Usuario a ser atualizado.
     */
    @Update
    fun update(user: Usuario)
}