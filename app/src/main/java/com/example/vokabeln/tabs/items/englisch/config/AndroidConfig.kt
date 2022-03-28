package com.example.vokabeln.tabs.items.englisch.config

import androidx.compose.runtime.mutableStateListOf
import com.example.vokabeln.MainActivity
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

class AndroidConfig private constructor() {
    companion object {
        val instance = AndroidConfig()
    }

    val vocabs = mutableStateListOf<Vocab>()
    private lateinit var file: File
    private val json = Json { prettyPrint = true }

    init {
        try {
            val path = MainActivity.instance.applicationContext.filesDir
            val letDirectory = File(path, "LET")
            if (!letDirectory.exists()) {
                letDirectory.mkdirs()
            }
            file = File(letDirectory, "vocabs.json")
            if (!file.exists()) {
                file.createNewFile()
            }
            vocabs.clear()
            vocabs += readVocabs()
        } catch(e: Exception) { println("error: $e\n occurred during the creation on config") }
    }

    private fun readVocabs(): MutableList<Vocab> = try {
        json.decodeFromString(file.readText())
    } catch (e: Exception) {
        println("$e, happened")
        mutableListOf()
    }

    fun saveVocabs(datas: MutableList<Vocab> = vocabs) {
        file.writeText(json.encodeToString(datas))
    }

    fun deleted() {
        vocabs.clear()
        saveVocabs()
    }
}