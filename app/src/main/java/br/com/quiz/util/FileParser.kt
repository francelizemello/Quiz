package br.com.quiz.util

import org.json.JSONArray
import java.io.IOException
import java.io.InputStream

class FileParser {
    public fun lerArquivo(inputStream: InputStream) : String {
        var arr = arrayListOf<String>()
        var json:String? = null
        try {
            json = inputStream.bufferedReader().use { it.readText() }
            return json
        } catch (exception: IOException) {
            exception.printStackTrace()
        }
        return ""
    }
}