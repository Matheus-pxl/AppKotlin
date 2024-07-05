package com.example.loginapp.database.sqliteDatabase

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.datasource.sqlDatabase.DatabaseDefinitions

class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_TABLE_USUARIO)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }
    companion object {
        private const val DATABASE_NAME = "usuario.db"
        private const val DATABASE_VERSION = 1
        private const val CREATE_TABLE_USUARIO =
            "CREATE TABLE ${DatabaseDefinitions.Usuario.TABLE_NAME} (" +
                    "${DatabaseDefinitions.Usuario.Columns.ID} INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "${DatabaseDefinitions.Usuario.Columns.EMAIL} TEXT, " +
                    "${DatabaseDefinitions.Usuario.Columns.SENHA} TEXT);"
    }
}