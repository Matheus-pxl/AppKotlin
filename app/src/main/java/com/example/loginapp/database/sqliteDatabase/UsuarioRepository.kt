//package com.example.loginapp.database.sqliteDatabase
//
//import android.annotation.SuppressLint
//import android.content.ContentValues
//import android.content.Context
//import com.example.datasource.sqlDatabase.DatabaseDefinitions
//import com.example.loginapp.model.Usuario
//
//class UsuarioRepository(context: Context) {
//    private val dbHelper = DatabaseHelper(context)
//
//    fun save(usuario: Usuario): Int {
//        //colocar o banco em modo ESCRITA
//        val db = dbHelper.writableDatabase
//        //criar um mapa com os valores que serao inseridos
//        val valores = ContentValues()
//        valores.put(DatabaseDefinitions.Usuario.Columns.ID, usuario.id)
//        valores.put(DatabaseDefinitions.Usuario.Columns.EMAIL, usuario.email)
//        valores.put(DatabaseDefinitions.Usuario.Columns.SENHA, usuario.senha)
//
//        //inserir os dados no banco
//        val id = db.insert(DatabaseDefinitions.Usuario.TABLE_NAME, null, valores)
//        return id.toInt()
//    }
//
//    fun update(usuario: Usuario) {
//
//    }
//
//    fun delete(id: Int) {
//
//    }
//
//    @SuppressLint("Range")
//    fun getUsuarios(): ArrayList<Usuario> {
//        val db = dbHelper.readableDatabase
//        //definir os campos que serao devolvidos na consulta
//        val projection = arrayOf(
//            DatabaseDefinitions.Usuario.Columns.ID,
//            DatabaseDefinitions.Usuario.Columns.EMAIL,
//            DatabaseDefinitions.Usuario.Columns.SENHA,
//        )
//        //definir a ordem de exibição da lista, ordenar pelo nome do jogo
//        val sortOrder = "${DatabaseDefinitions.Usuario.Columns.EMAIL} ASC"
//
//        val cursor = db.query(
//            DatabaseDefinitions.Usuario.TABLE_NAME,
//            projection,
//            null,
//            null,
//            null,
//            sortOrder
//        )
//        var usuarios = ArrayList<Usuario>()
//        if (cursor != null) {
//            while (cursor.moveToNext()) {
//                var usuario = Usuario(
//                    id = cursor.getInt(cursor.getColumnIndex(DatabaseDefinitions.Usuario.Columns.ID)),
//                    email = cursor.getString(cursor.getColumnIndex(DatabaseDefinitions.Usuario.Columns.EMAIL)),
//                    senha = cursor.getString(cursor.getColumnIndex(DatabaseDefinitions.Usuario.Columns.SENHA))
//                )
//                usuarios.add(usuario)
//
//            }
//        }
//        return usuarios
//    }
//
//    fun getUsuario(id: Int) {
//
//    }
//}