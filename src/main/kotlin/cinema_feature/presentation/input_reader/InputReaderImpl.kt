package com.ilyanvk.cinema_feature.presentation.input_reader

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class InputReaderImpl : InputReader {
    override fun readInt(prompt: String): Int? {
        print(prompt)
        return try {
            readln().toInt()
        } catch (e: Exception) {
            println("Error: ${e.message}")
            null
        }
    }

    override fun readString(prompt: String): String {
        print(prompt)
        return readln()
    }

    override fun readDate(prompt: String): Date? {
        print(prompt)
        return try {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
            dateFormat.parse(readln())
        } catch (e: Exception) {
            println("Error parsing date: ${e.message}")
            null
        }
    }
}
