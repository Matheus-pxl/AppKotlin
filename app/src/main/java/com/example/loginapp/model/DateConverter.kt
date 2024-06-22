package com.example.loginapp.model

import androidx.room.TypeConverter
import java.util.Date

/**
 * Classe auxiliar para conversão de objetos Date em tipos primitivos que podem ser armazenados no banco de dados.
 */
class DateConverter {

    /**
     * Converte um valor de timestamp (Long) para um objeto Date.
     * @param value Valor de timestamp a ser convertido.
     * @return Objeto Date correspondente ao timestamp fornecido, ou null se o valor for nulo.
     */
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    /**
     * Converte um objeto Date para um valor de timestamp (Long).
     * @param date Objeto Date a ser convertido.
     * @return Valor de timestamp correspondente ao objeto Date fornecido, ou null se o objeto for nulo.
     */
    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}
