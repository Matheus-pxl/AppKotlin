package com.example.datasource.sqlDatabase

class DatabaseDefinitions {
    object Usuario{
        const val TABLE_NAME="tbl_usuario"

        object Columns{
            const val ID= "id"
            const val EMAIL ="email"
            const val SENHA ="senha"

        }
    }
}